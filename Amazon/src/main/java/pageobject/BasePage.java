package pageobject;

import base.CommonAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends CommonAPI{

    public BasePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#twotabsearchtextbox")
    WebElement searchField;

    @FindBy(css = "#nav-search-submit-button")
    WebElement searchButton;

    public void searchItem(String item){
        type(searchField, item);
        click(searchButton);
    }

    public void searchItemAndClear(String item){
        type(searchField, item);
        click(searchButton);
        clear(searchField);
    }
}
