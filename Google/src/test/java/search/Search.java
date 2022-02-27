package search;

import base.CommonAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Search extends CommonAPI{

    @Test
    public void searchOnGoggle(){
        Assert.assertEquals("", "");
        //clickAndEnter("//*[@name='q']", "selenium");
        Assert.assertEquals("", "");
    }


}
