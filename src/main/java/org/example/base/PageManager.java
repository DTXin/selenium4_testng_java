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

    private PageManager() {
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

    @SuppressWarnings("unchecked")
    public static <T> T getInstanceOfPage(T instance, String className) {
        try {
            if (instance == null) {
                logger.info("=== Logger: Init object for page `{}` ===", className);
                instance = (T) Class.forName(className).getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            logger.error("Error initializing class: " + className, e);
        }

        return instance;
    }

    public LoginPage getLoginPage() {
        loginPage = (LoginPage) getInstanceOfPage(loginPage, LoginPage.class.getName());
        return loginPage;
    }

    public ProductPage getProductPage() {
        productPage = (ProductPage) getInstanceOfPage(productPage, ProductPage.class.getName());
        return productPage;
    }

    public FileHelper getFileHelper() {
        fileHelper = (FileHelper) getInstanceOfPage(fileHelper, FileHelper.class.getName());
        return fileHelper;
    }

    // Remote thread when it complete.
    public static void cleanUp() {
        logger.info("=== Logger: Cleanup page manager ===");
        page.remove();
    }
}