package org.example.base;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverManager {
    private static final Logger logger = LogManager.getLogger();
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private static final ThreadLocal<WebDriverWait> driverWait = new ThreadLocal<WebDriverWait>();

    private static final String HUBURL = "http://localhost:4444";
    private static final String FIREFOX = "firefox";
    private static final String EDGE = "edge";
    private static final long TIMEOUT = 20;

    private static ChromeOptions chromeOptions;
    private static FirefoxOptions firefoxOptions;
    private static EdgeOptions edgeOptions;

    private DriverManager() {
    }

    public static void createLocalDriver(String browser) {
        logger.info("=== Logger: Creating local `{}` driver ===", browser);

        switch (browser) {
            case FIREFOX -> setDriver(new FirefoxDriver(getFireFoxOptions()));
            case EDGE -> setDriver(new EdgeDriver(getEdgeOptions()));
            default -> setDriver(new ChromeDriver(getChromeOptions()));
        }
        setupDriverTimeouts();
    }

    public static void createRemoteDriver(String browser, String platform_name)
            throws MalformedURLException, URISyntaxException {

        logger.info("=== Logger: Creating remote `{}` driver ===", browser);
        switch (browser) {
            case FIREFOX:
                firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPlatformName(platform_name);
                setDriver(new RemoteWebDriver(new URI(HUBURL).toURL(), firefoxOptions));
                break;

            case EDGE:
                edgeOptions = new EdgeOptions();
                edgeOptions.setPlatformName(platform_name);
                setDriver(new RemoteWebDriver(new URI(HUBURL).toURL(), edgeOptions));
                break;

            default:
                chromeOptions = getChromeOptions();
                chromeOptions.setPlatformName(platform_name);
                setDriver(new RemoteWebDriver(new URI(HUBURL).toURL(), chromeOptions));
                break;
        }
        setupDriverTimeouts();
    }

    private static void setupDriverTimeouts() {
        logger.info("=== Logger: Setting driver timeouts = `{}` for all ...", TIMEOUT);

        // set timeout for WebDriver
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TIMEOUT));
        getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(TIMEOUT));

        // Set timeout for WebDriverWait
        setDriverWait(new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT)));
    }

    // Setup Chrome Options
    private static ChromeOptions getChromeOptions() {
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("safebrowsing.enabled", "true");
        chromePrefs.put("download.prompt_for_download", "false");
        chromePrefs.put("download.default_directory",
                String.valueOf(Paths.get(System.getProperty("user.dir"), "/src/test/resources/download")));

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-setuid-sandbox");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-password-manager-reauthentication");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=PasswordLeakDetection");
        options.addArguments("--suppress-message-center-popups");
        options.addArguments("--safebrowsing-disable-download-protection");

        var isHeadless = Boolean.parseBoolean(Objects.requireNonNullElse(System.getProperty("headless"), "false"));
        if (isHeadless) {
            options.addArguments("--headless");
        }

        options.setExperimentalOption("prefs", chromePrefs);
        options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" }); // Disable infor-bars
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        return options;
    }

    // Setup Firefox Options
    private static FirefoxOptions getFireFoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        return options;
    }

    // Setup Edge Options
    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        return options;
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static WebDriverWait getDriverWait() {
        return driverWait.get();
    }

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static void setDriverWait(WebDriverWait webDriverWait) {
        driverWait.set(webDriverWait);
    }

    public static void closeDriver() {
        if (getDriver() != null) {
            logger.info("=== Logger: Closing the driver ===");
            getDriver().quit();
            driver.remove();
        }
    }
}
