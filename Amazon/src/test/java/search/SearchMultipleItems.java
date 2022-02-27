package search;

import base.CommonAPI;
import org.testng.annotations.Test;
import pageobject.BasePage;
import utility.DataReader;
import utility.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SearchMultipleItems extends CommonAPI{
    DataReader dr = new DataReader();

//    String path = prop.getProperty("data.file.path");
//    String username = prop.getProperty("username");
//    String password = prop.getProperty("password");

    public List<String> items(){
        List<String> items = new ArrayList<String>();
        items.add("java book");
        items.add("selenium book");
        items.add("mouse");
        items.add("keyboard");
        items.add("display");
        return items;
    }

    @Test
    public void searchListOfItems(){
        BasePage basePage = new BasePage(getDriver());
        List<String> myItems = dr.getEntireColumnForGivenHeader("", "Sheet1", "items");
        for(String item: myItems){
           basePage.searchItemAndClear(item);
        }

    }
}
