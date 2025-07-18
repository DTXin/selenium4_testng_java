package org.example.fw_ui.base;

import org.example.fw_ui.enums.Browsers;
import org.example.fw_ui.manager.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.qameta.allure.Step;

public class BaseTest extends DriverManager {

    @Parameters({ "browser", "url" })
    @BeforeMethod(alwaysRun = true)
    public void init(@Optional("chrome") String browser, String url) {
        createDriver(Browsers.valueOf(browser.toUpperCase()));
        openURL(url);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        closeDriver();
    }

    @Step("Open website with URL")
    public void openURL(String url) {
        getDriver().navigate().to(url);
    }
}
