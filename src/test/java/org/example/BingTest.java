package org.example;

import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.base.AbstractTest;
import org.example.base.PageManager;

public class BingTest extends AbstractTest {
    protected static final Logger logger = LogManager.getLogger();

    @Test(description = "Verify if the save button is enabled")
    public void searchTextInBing_WithoutSeleniumPageFactory() {
        PageManager.getInstance().getBingMainPage().navigateTo("https://bing.com");
        PageManager.getInstance().getBingMainPage().search("Automate The Planet");
        // getBingMainPage().navigateTo("https://bing.com");
        // getBingMainPage().search("Automate The Planet");
    }

    @Test(description = "Verify if the save button is enabled - Dinh Tran Xin")
    public void searchTextInBing_WithoutSeleniumPageFactor2() {
        PageManager.getInstance().getBingMainPage().navigateTo("https://bing.com");
        PageManager.getInstance().getBingMainPage().search("Dinh Tran Xin");
        // getBingMainPage().navigateTo("https://bing.com");
        // getBingMainPage().search("Dinh Tran Xin");
    }
}
