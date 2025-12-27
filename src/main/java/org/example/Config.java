package org.example;

public class Config {
    // Specify the URL of the remote WebDriver
    public static final String REMOTE_URL = "http://selenium-hub:4444";

    // Time out for webdriver
    public static final long TIMEOUT = 30;

    // Time out for webdriver waits
    public static final long TIMEOUT_WAITS = 30;

    // Retry when testcase failed
    public static final int RETRY_LIMIT = 1;

    // List options for browser
    public static final String INCOGNITO_MODE = "--incognito";
    public static final String NO_SANDBOX = "--no-sandbox";
    public static final String DISABLE_DEV_SHM = "--disable-dev-shm-usage";
    public static final String DISABLE_NOTIFICATIONS = "--disable-notifications";
    public static final String DISABLE_POPUP_BLOCKING = "--disable-popup-blocking";
    public static final String HEADLESS = "--headless";

    // List experimental options for browser
    public static final String SAFE_BROWSING = "safebrowsing.enabled";
    public static final String PROMPT_FOR_DOWNLOAD = "download.prompt_for_download";
    public static final String DEFAULT_DIRECTORY = "download.default_directory";

    // Get the current working directory
    public static final String USER_DIR = "user.dir";
    public static final String DOWNLOAD_FOLDER= "/src/test/resources/download";

    /*********************
        Config for API
     ********************/

    public static final String API_URI = "http://restful-booker.herokuapp.com";
    public static final String API_USERNAME = "admin";
    public static final String API_PASSWORD = "password123";

    // List Endpoint
    public static final String API_AUTHEN_ENDPOINT = "/auth";
    public static final String API_BOOKING_ENDPOINT = "/booking";
    public static final String API_USER_ENDPOINT = "/user";

    // Token
    public static final String API_TOKEN_KEY = "token";
}
