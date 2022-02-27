package base;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import utility.Utilities;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CommonAPI {

    private WebDriver driver;

//    public Properties prop = Utilities.loadProperties(Utilities.projectPath()+"/config.properties");
//    String browserStackUsername = prop.getProperty("browserstack.username");
//    String browserStackPassword = prop.getProperty("browserstack.password");
//    String takeScreenshot = prop.getProperty("take.screenshot", "false");
//    String waitTime = prop.getProperty("wait.time", "10");
//    String windowMaximize = prop.getProperty("window.maximize", "false");

    public static com.relevantcodes.extentreports.ExtentReports extent;

    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName().toLowerCase();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }
    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        }
        ExtentTestManager.endTest();
        extent.flush();
//        if (takeScreenshot.equalsIgnoreCase("true")){
//            if (result.getStatus() == ITestResult.FAILURE) {
//                //takeScreenshot(result.getName());
//            }
//        }
        driver.quit();
    }
    @AfterSuite
    public void generateReport() {
        extent.close();
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    @Parameters({"useCloudEnv","envName","envUsername","envAccessKey","os","os_version","browserName","browserVersion","url"})
    @BeforeMethod
    public void setUp(@Optional("false")Boolean useCloudEnv, @Optional("browserstack")String envName,
                      @Optional String envUsername, @Optional String envAccessKey,@Optional("windows")String os,
                      @Optional("10")String osVersion, @Optional("chrome") String browserName,
                      @Optional("34") String browserVersion, @Optional("") String url) throws MalformedURLException {
        if(useCloudEnv == true){
            if(envName.equalsIgnoreCase("browserstack")){
                //getCloudDriver(envName, browserStackUsername, browserStackPassword, os, osVersion, browserName, browserVersion);
            }else if(envName.equalsIgnoreCase("saucelabs")){
                getCloudDriver(envName, "", "", os, osVersion, browserName, browserVersion);
            }
        }else {
            getDriver(os, browserName);
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        if(windowMaximize.equalsIgnoreCase("true")){
//            driver.manage().window().maximize();
//        }
        driver.get(url);
    }

    public WebDriver getDriver(String os, String browserName) {
        if(browserName.equalsIgnoreCase("chrome")){
            if(os.equalsIgnoreCase("OS X")){
                System.setProperty("webdriver.chrome.driver", "../Generic/src/driver/chromedriver");
            }else if(os.equalsIgnoreCase("windows")){
                System.setProperty("webdriver.chrome.driver", "../Generic/src/driver/chromedriver.exe");
            }
            driver = new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("firefox")){
            if(os.equalsIgnoreCase("OS X")){
                System.setProperty("webdriver.gecko.driver", "../Generic/src/driver/geckodriver");
            }else if(os.equalsIgnoreCase("windows")){
                System.setProperty("webdriver.gecko.driver", "../Generic/src/driver/geckodriver.exe");
            }
            driver = new FirefoxDriver();
        }
        return driver;
    }

    public WebDriver getCloudDriver(String envName, String EnvUsername, String envAccessKey, String os, String os_version, String browserName, String browserVersion) throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browser", browserName);
        cap.setCapability("browser_version", browserVersion);
        cap.setCapability("os", os);
        cap.setCapability("os_version", os_version);
        if(envName.equalsIgnoreCase("saucelabs")){
            driver = new RemoteWebDriver(new URL("http://"+ EnvUsername + ":" + envAccessKey + "@ondemand.saucelabs.com:80/wd/hub"), cap);
        }else if(envName.equalsIgnoreCase("browserstack")){
            cap.setCapability("resolution", "1024x768");
            driver = new RemoteWebDriver(new URL("http://"+ EnvUsername + ":" + envAccessKey + "@hub-cloud.browserstack.com:80/wd/hub"), cap);
        }
        return driver;
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getTitle(){
        return driver.getTitle();
    }
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }
    public void click(WebElement element){
        element.click();
    }
    public void clear(WebElement element){
        element.clear();
    }
    public void clickAndEnter(WebElement element, String text){
        element.sendKeys(text, Keys.ENTER);
    }
    public void type(WebElement element, String text){
        element.sendKeys(text);
    }
}
