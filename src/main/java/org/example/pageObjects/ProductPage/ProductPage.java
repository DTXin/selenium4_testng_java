package org.example.pageObjects.ProductPage;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.base.BasePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ProductPage extends BasePage {
    protected static final Logger logger = LogManager.getLogger();

    @Step("Verify product page is displayed.")
    public void verifyProductPageIsDisplayed() {
        WebElement app_logo = getElementByXpath(ProductPageLocators.LOGO_XPATH);

        logger.info("Verify product page is displayed = `{}`", app_logo.isDisplayed());
        Assert.assertTrue(app_logo.isDisplayed(), "Product page is not displayed.");
    }
}
