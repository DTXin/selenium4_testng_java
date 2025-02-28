package org.example.base;

// import java.lang.ThreadLocal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pageObjects.BingMainPage;
import org.openqa.selenium.WebDriver;

public class PageManager extends AbstractTest {
    protected static final Logger logger = LogManager.getLogger();

    private static PageManager instance = null;
    private BingMainPage bingMainPage = null;
    public WebDriver driver;

    public PageManager() {
        this.driver = DriverManager.getDriver();
    }

    public static PageManager getInstance() {
        if (instance == null) {
            synchronized (PageManager.class) {
                if (instance == null) {
                    instance = new PageManager();
                }
            }
        }
        return instance;
    }

    public BingMainPage getBingMainPage() {
        if (bingMainPage == null) {
            logger.info("=== Logger: Init object `{}` ===", BingMainPage.class.getName());
            bingMainPage = new BingMainPage(driver);
        }
        return bingMainPage;
    }

    // private static ThreadLocal<BingMainPage> bingMainPage;

    // public static synchronized BingMainPage getBingMainPage() {
    // if (DriverManager.getDriver() == null) {
    // logger.info("=== Logger: Init object `{}` ===",
    // BingMainPage.class.getName());
    // bingMainPage = ThreadLocal.withInitial(() -> new
    // BingMainPage(DriverManager.getDriver()));
    // }
    // return bingMainPage.get();
    // }

    // public static void cleanUp() {
    // bingMainPage.remove();
    // }
}
