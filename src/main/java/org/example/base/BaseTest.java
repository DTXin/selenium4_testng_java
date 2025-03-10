package org.example.base;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.qameta.allure.Step;

public abstract class BaseTest {
    protected static final Logger logger = LogManager.getLogger();

    private String baseUrl;

    @Parameters({ "browser", "url", "runmode", "platform_name" })
    @BeforeMethod(alwaysRun = true)
    public void init(@Optional("chrome") String browser, String url, @Optional("local") String runmode,
            @Optional("Window") String platform_name) throws MalformedURLException, URISyntaxException {

        switch (runmode) {
            case "grid":
                DriverManager.createRemoteDriver(browser.toLowerCase(), platform_name);
                break;

            default:
                DriverManager.createLocalDriver(browser.toLowerCase());
                break;
        }
        PageManager.getInstance();
        setBaseUrl(url);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.closeDriver();
        PageManager.cleanUp();
    }

    // Set base url
    public void setBaseUrl(String url) {
        baseUrl = url;
    }

    // Get base url
    public String getBaseUrl() {
        return baseUrl;
    }

    @Step("Navigate to url")
    public void navigateToUrl() {
        DriverManager.getDriver().navigate().to(getBaseUrl());
    }
}
