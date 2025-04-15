package org.example.base;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.qameta.allure.Step;

public abstract class BaseTest {
    private String baseUrl;

    @Parameters({ "browser", "url", "runMode", "platform_name" })
    @BeforeMethod(alwaysRun = true)
    public void init(@Optional("chrome") String browser, String url, @Optional("local") String runMode,
            @Optional("Window") String platform_name) throws MalformedURLException, URISyntaxException {

        if (runMode.equals("grid")) {
            DriverManager.createRemoteDriver(browser.toLowerCase(), platform_name);
        } else {
            DriverManager.createLocalDriver(browser.toLowerCase());
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
