package org.example.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pageObjects.LoginPage.LoginPage;
import org.example.pageObjects.ProductPage.ProductPage;
import org.example.utils.FileHelper.FileHelper;

public class PageManager {
    private static final Logger logger = LogManager.getLogger();
    private static final ThreadLocal<PageManager> page = new ThreadLocal<>();

    private LoginPage loginPage;
    private ProductPage productPage;
    private FileHelper fileHelper;

    public PageManager() {
    }

    public static PageManager getPageManager() {
        return page.get();
    }

    public static void setPageManager(PageManager pageManager) {
        page.set(pageManager);
    }

    public static void getInstance() {
        setPageManager(new PageManager());
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            logger.info("=== Logger: Init object for Login page ===");
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public ProductPage getProductPage() {
        if (productPage == null) {
            logger.info("=== Logger: Init object for Product page ===");
            productPage = new ProductPage();
        }
        return productPage;
    }

    public FileHelper getFileHelper() {
        if (fileHelper == null) {
            logger.info("=== Logger: Init object File Helper ===");
            fileHelper = new FileHelper();
        }
        return fileHelper;
    }

    public static void cleanUp() {
        logger.info("=== Logger: Cleanup page manager ===");
        page.remove();
    }
}