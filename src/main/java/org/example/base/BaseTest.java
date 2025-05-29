package org.example.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest {

    @Parameters({ "browser" })
    @BeforeMethod(alwaysRun = true)
    public void init(@Optional("chrome") String browser) {
        PageManager.getInstance();
        DriverManager.createDriver(Browsers.valueOf(browser.toUpperCase()));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.closeDriver();
        PageManager.cleanUp();
    }
}
