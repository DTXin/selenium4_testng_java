package org.example.fw;

public class Config {

    // Base URL of the app under test
    public static final String BASE_URL = "https://www.saucedemo.com";

    // Specify the URL of the remote WebDriver
    public static final String REMOTE_URL = "http://selenium-hub:4444";

    // Time out for webdriver
    public static final long TIMEOUT = 30;

    // Time out for webdriver waits
    public static final long TIMEOUT_WAITS = 30;

    // List options for browser
    public static final String NO_SANDBOX = "--no-sandbox";
    public static final String DISABLE_DEV_SHM = "--disable-dev-shm-usage";
    public static final String HEADLESS = "--headless";
}
