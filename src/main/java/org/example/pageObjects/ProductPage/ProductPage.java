package org.example.pageObjects.ProductPage;

import io.qameta.allure.Step;
import org.example.fw.base.BasePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ProductPage extends BasePage {

    @Step("Verify product page is displayed.")
    public void verifyProductPageIsDisplayed() {
        WebElement app_logo = getElementByXpath(ProductPageLocators.LOGO_XPATH);

        logger.info("Verify product page is displayed = `{}`", app_logo.isDisplayed());
        Assert.assertTrue(1 == 0, "Product page is not displayed.");
    }
}
