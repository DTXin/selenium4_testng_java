package org.example.fw_ui.base;

import org.example.Config;
import org.example.fw_ui.manager.Browsers;
import org.example.fw_ui.manager.DriverManager;
import org.testng.annotations.*;
import io.qameta.allure.Step;

public class BaseTest extends DriverManager {
    public String BROWSER;
    public String URL;
    public String USERNAME;
    public String PASSWORD;

    @Parameters({ "browser", "env"})
    @BeforeMethod(alwaysRun = true)
    @Step("init Parameter")
    public void initParameter(String browser, String env) {
        BROWSER = browser;

        if (Environments.valueOf(env.toUpperCase()) == Environments.STAGING) {
            URL = Config.STAGING_URL;
            USERNAME = Config.STAGING_USERNAME;
            PASSWORD = Config.STAGING_PASSWORD;
        } else {
            URL = Config.QA_URL;
            USERNAME = Config.QA_USERNAME;
            PASSWORD = Config.QA_PASSWORD;
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        initDriver();
        openWebsite(URL);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        closeDriver();
    }

    @Step("Init driver")
    public void initDriver() {
        createDriver(Browsers.valueOf(BROWSER.toUpperCase()));
    }

    @Step("Open website")
    public void openWebsite(String url) {
        getDriver().navigate().to(url);
    }
}
