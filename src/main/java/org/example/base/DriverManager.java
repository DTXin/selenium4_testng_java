package org.example.base;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {
    private static final Logger logger = LogManager.getLogger();
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String EDGE = "edge";

    private DriverManager() {
    }

    public static void setupDriver(String browse) {
        switch (browse) {
            case CHROME -> setupChromeDriver();
            case FIREFOX -> setupFireFoxDriver();
            case EDGE -> setupEdgeDriver();
            default -> setupChromeDriver();
        }
        setupBrowserTimeouts();
    }

    private static void setupBrowserTimeouts() {
        logger.info("=== Logger: Setting browser timeouts...");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
    }

    private static void setupChromeDriver() {
        logger.info("=== Logger: Setting up Chrome Driver....");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("safebrowsing.enabled", "true");
        chromePrefs.put("download.prompt_for_download", "false");
        chromePrefs.put("download.default_directory",
                String.valueOf(Paths.get(System.getProperty("user.home"), "Downloads")));

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

        setDriver(new ChromeDriver(options));
        logger.info("=== Logger: Chrome Driver created successfully ===");
    }

    private static void setupFireFoxDriver() {
        logger.info("=== Logger: Setting up Firefox Driver.... ===");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        setDriver(new FirefoxDriver(options));
        logger.info("=== Logger: Firefox Driver created successfully ===");
    }

    private static void setupEdgeDriver() {
        logger.info("=== Logger: Setting up Edge Driver.... ===");
        setDriver(new EdgeDriver());
        logger.info("=== Logger: Edge Driver created successfully ===");
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            logger.info("=== Logger: Closing the driver ===");
            getDriver().quit();
            // driver.remove();
        }
    }
}
