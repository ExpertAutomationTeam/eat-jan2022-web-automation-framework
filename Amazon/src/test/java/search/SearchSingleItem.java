package search;

import base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchSingleItem extends CommonAPI{


    @Test
    public void searchComputer(){
        Assert.assertEquals(getTitle(), "Amazon.com. Spend less. Smile more.");
        type("#twotabsearchtextbox", "computer");
        click("#nav-search-submit-button");
        Assert.assertEquals(getTitle(), "Amazon.com : computer");
    }

    //@Test
    public void searchPs5(){
        Assert.assertEquals(getCurrentUrl(), "https://www.amazon.com/");
        type("#twotabsearchtextbox", "ps5");
        click("#nav-search-submit-button");
        Assert.assertEquals(getCurrentUrl(), "https://www.amazon.com/s?k=ps5&crid=27S7LU7ASP146&sprefix=ps5%2Caps%2C168&ref=nb_sb_noss_1");
    }



}
