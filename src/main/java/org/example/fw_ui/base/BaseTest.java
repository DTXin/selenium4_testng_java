package org.example.fw_ui.base;

import org.example.fw_ui.enums.Browsers;
import org.example.fw_ui.manager.DriverManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import io.qameta.allure.Step;

public class BaseTest extends DriverManager {
    String static final BROWSER;
    String static final URL;

    @Parameters({ "browser", "url" })
    @BeforeMethod(alwaysRun = true)
    public void initParameter(String browser, String url) {
        BROWSER = browser;
        URL = url;
    }

    @Step("Init driver")
    public void initDriver() {
        createDriver(Browsers.valueOf(browser.toUpperCase()));
    }

    @Step("Open website with URL")
    public void openURL() {
        getDriver().navigate().to(URL);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        closeDriver();
    }
}
