package org.example.fw.base;

import org.example.fw.enums.Browsers;
import org.example.fw.manager.DriverManager;
import org.example.fw.manager.PageManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.qameta.allure.Step;

public class BaseTest extends PageManager {

    @Parameters({ "browser", "url" })
    @BeforeMethod(alwaysRun = true)
    public void init(@Optional("chrome") String browser, String url) {
        DriverManager.createDriver(Browsers.valueOf(browser.toUpperCase()));
        openURL(url);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.closeDriver();
    }

    @Step("Open a website URL: {url}")
    public void openURL(String url) {
        DriverManager.getDriver().navigate().to(url);
    }
}
