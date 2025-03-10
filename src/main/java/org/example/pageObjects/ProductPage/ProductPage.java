package org.example.pageObjects.ProductPage;

import io.qameta.allure.Step;
import org.example.base.BasePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ProductPage extends BasePage {

    @Step("Verify product page is displayed.")
    public void verifyProductPageIsDisplayed() {
        WebElement app_logo = getElementByXpath(ProductPageLocators.APPLOGO_XPATH);
        Assert.assertTrue(app_logo.isDisplayed(), "Product page is not displayed.");
    }
}
