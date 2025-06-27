package org.example.fw.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pageObjects.CartPage.CartPage;
import org.example.pageObjects.InformationPage.InformationPage;
import org.example.pageObjects.LoginPage.LoginPage;
import org.example.pageObjects.ProductPage.ProductPage;
import org.example.utils.Common;
import org.example.utils.FileHelper;

public class PageManager {
    private static final Logger logger = LogManager.getLogger();

    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private InformationPage informationPage;
    private FileHelper fileHelper;
    private Common common;

    public PageManager() {
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

    public CartPage getCartPage() {
        cartPage = (CartPage) getInstanceOfPage(cartPage, CartPage.class.getName());
        return cartPage;
    }

    public InformationPage getInformationPage() {
        informationPage = (InformationPage) getInstanceOfPage(informationPage, InformationPage.class.getName());
        return informationPage;
    }

    public FileHelper getFileHelper() {
        fileHelper = (FileHelper) getInstanceOfPage(fileHelper, FileHelper.class.getName());
        return fileHelper;
    }

    public Common getCommon() {
        common = (Common) getInstanceOfPage(common, Common.class.getName());
        return common;
    }
}