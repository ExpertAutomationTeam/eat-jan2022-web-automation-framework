package search;

import base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.BasePage;
import pageobject.HomePage;

public class SearchSingleItem extends CommonAPI {


    //HomePage homePage = new HomePage();

    @Test
    public void searchComputer(){
        BasePage basePage = new BasePage(getDriver());
        Assert.assertEquals(getTitle(), "Amazon.com. Spend less. Smile more.");
        basePage.searchItem("computer");
        Assert.assertEquals(getTitle(), "Amazon.com : computer");
    }

    //@Test
    public void searchPs5(){
        BasePage basePage = new BasePage(getDriver());
        Assert.assertEquals(getCurrentUrl(), "https://www.amazon.com/");
        basePage.searchItem("ps5");
        Assert.assertEquals(getCurrentUrl(), "https://www.amazon.com/s?k=ps5&crid=27S7LU7ASP146&sprefix=ps5%2Caps%2C168&ref=nb_sb_noss_1");
    }



}
