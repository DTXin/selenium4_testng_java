package org.example.fw.manager;

import java.net.URI;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.fw.Config;
import org.example.fw.enums.Browsers;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;

public class DriverManager {
    private static final Logger logger = LogManager.getLogger();
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static WebDriverWait driverWait;

    private DriverManager() {
    }

    @Step("Creating driver for browser: {browser}")
    public static void createDriver(final Browsers browser) {
        switch (browser) {
            case FIREFOX -> setupFireFoxDriver();
            case EDGE -> setupEdgeDriver();
            case REMOTE_CHROME -> setupRemoteChromeDriver();
            case REMOTE_FIREFOX -> setupRemoteFireFoxDriver();
            case REMOTE_EDGE -> setupRemoteEdgeDriver();
            default -> setupChromeDriver();
        }
        setupDriverTimeouts();
    }

    // Setup local driver for chrome
    private static void setupChromeDriver() {
        logger.info("=== Setup local driver for chrome ===");
        setDriver(ThreadGuard.protect(new ChromeDriver(getChromeOptions())));
    }

    // Setup local driver for firefox
    private static void setupFireFoxDriver() {
        logger.info("=== Setup local driver for firefox ===");
        setDriver(ThreadGuard.protect(new FirefoxDriver(getFireFoxOptions())));
    }

    // Setup local driver for edge
    private static void setupEdgeDriver() {
        logger.info("=== Setup local driver for edge ===");
        setDriver(ThreadGuard.protect(new EdgeDriver(getEdgeOptions())));
    }

    // Setup remote driver for chrome
    private static void setupRemoteChromeDriver() {
        try {
            logger.info("=== Creating remote driver for chrome ===");
            ChromeOptions chromeOptions = getChromeOptions();
            setDriver(ThreadGuard.protect(new RemoteWebDriver(new URI(Config.REMOTE_URL).toURL(), chromeOptions)));
        } catch (Exception e) {
            logger.error("Error setting remote driver for chrome", e);
        }
    }

    // Setup remote driver for firefox
    private static void setupRemoteFireFoxDriver() {
        try {
            logger.info("=== Creating remote driver for firefox ===");
            FirefoxOptions firefoxOptions = getFireFoxOptions();
            setDriver(ThreadGuard.protect(new RemoteWebDriver(new URI(Config.REMOTE_URL).toURL(), firefoxOptions)));
        } catch (Exception e) {
            logger.error("Error setting remote driver for firefox", e);
        }
    }

    // Setup remote driver for edge
    private static void setupRemoteEdgeDriver() {
        try {
            logger.info("=== Creating remote driver for edge ===");
            EdgeOptions edgeOptions = getEdgeOptions();
            setDriver(ThreadGuard.protect(new RemoteWebDriver(new URI(Config.REMOTE_URL).toURL(), edgeOptions)));
        } catch (Exception e) {
            logger.error("Error setting remote driver for edge", e);
        }
    }

    private static void setupDriverTimeouts() {
        logger.info("=== Logger: Setting driver timeouts = `{}` for all ...", Config.TIMEOUT);

        // set timeout for WebDriver
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.TIMEOUT));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Config.TIMEOUT));
        getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(Config.TIMEOUT));

        // Set timeout for WebDriverWait
        setDriverWait(new WebDriverWait(getDriver(), Duration.ofSeconds(Config.TIMEOUT_WAITS)));
    }

    // Setup Chrome Options
    private static ChromeOptions getChromeOptions() {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("safebrowsing.enabled", "true");
        chromePrefs.put("download.prompt_for_download", "false");
        chromePrefs.put("download.default_directory",
                String.valueOf(Paths.get(System.getProperty("user.dir"), "/src/test/resources/download")));

        ChromeOptions options = new ChromeOptions();
        options.addArguments(Config.INCOGNITO_MODE);
        options.addArguments(Config.DISABLE_DEV_SHM);
        options.addArguments(Config.NO_SANDBOX);
        options.addArguments(Config.DISABLE_NOTIFICATIONS);
        options.addArguments(Config.DISABLE_POPUP_BLOCKING);

        var isHeadless = Boolean.parseBoolean(Objects.requireNonNullElse(System.getProperty("headless"), "false"));
        if (isHeadless) {
            options.addArguments(Config.HEADLESS);
        }

        options.setExperimentalOption("prefs", chromePrefs);
        options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" }); // Disable infor-bars
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        return options;
    }

    // Setup Firefox Options
    private static FirefoxOptions getFireFoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(Config.NO_SANDBOX);
        options.addArguments(Config.DISABLE_DEV_SHM);
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
        return driverWait;
    }

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static void setDriverWait(WebDriverWait webDriverWait) {
        driverWait = webDriverWait;
    }

    public static void closeDriver() {
        if (getDriver() != null) {
            logger.info("=== Logger: Closing the driver ===");
            getDriver().quit();
            driver.remove();
        }
    }
}
