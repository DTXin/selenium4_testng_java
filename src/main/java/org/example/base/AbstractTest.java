package org.example.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public abstract class AbstractTest {
    protected static final Logger logger = LogManager.getLogger();

    @Parameters({ "browser", "url" })
    @BeforeMethod
    public void init(String browser, String url) {
        DriverManager.setupDriver(browser);
        PageManager.getInstance();
        DriverManager.getDriver().navigate().to(url);
        DriverManager.getDriver().manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.closeDriver();
        PageManager.cleanUp();
    }

}
