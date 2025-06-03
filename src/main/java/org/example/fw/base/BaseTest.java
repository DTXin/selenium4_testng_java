package org.example.fw.base;

import org.example.fw.enums.Browsers;
import org.example.fw.manager.DriverManager;
import org.example.fw.manager.PageManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest extends PageManager {

    @Parameters({ "browser" })
    @BeforeMethod(alwaysRun = true)
    public void init(@Optional("chrome") String browser) {
        DriverManager.createDriver(Browsers.valueOf(browser.toUpperCase()));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.closeDriver();
    }
}
