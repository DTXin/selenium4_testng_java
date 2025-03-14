package org.example.base;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pageObjects.LoginPage.LoginPage;
import org.example.pageObjects.ProductPage.ProductPage;
import org.example.utils.FileHelper.FileHelper;

public class PageManager {
    private static final Logger logger = LogManager.getLogger();
    private static final ThreadLocal<PageManager> page = new ThreadLocal<PageManager>();

    private LoginPage loginPage;
    private ProductPage productPage;
    private FileHelper fileHelper;

    public PageManager(WebDriver driver) {
    }

    public static PageManager getPageManager() {
        return page.get();
    }

    public static void setPageManager(PageManager pageManager) {
        page.set(pageManager);
    }

    public static void getInstance() {
        setPageManager(new PageManager(DriverManager.getDriver()));
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            logger.info("=== Logger: Init object `{}` ===", LoginPage.class.getName());
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public ProductPage getProductPage() {
        if (productPage == null) {
            logger.info("=== Logger: Init object `{}` ===", ProductPage.class.getName());
            productPage = new ProductPage();
        }
        return productPage;
    }

    public FileHelper getFileHelper() {
        if (fileHelper == null) {
            logger.info("=== Logger: Init object `{}` ===", FileHelper.class.getName());
            fileHelper = new FileHelper();
        }
        return fileHelper;
    }

    public static void cleanUp() {
        logger.info("=== Logger: Cleanup page manager ===");
        page.remove();
    }
}