package org.example.fw_ui.pageObjects.CheckoutPage;

import io.qameta.allure.Step;
import org.example.fw_ui.base.BasePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class CheckoutPage extends BasePage {

    /*****************************
     *** Action methods **********
     *****************************/

    @Step("[Overview page] Click on finish button")
    public void clickOnFinishButton() {
        WebElement element = getElementByCss(CheckoutPageLocators.BTN_FINISH_CSS);
        clickToElement_ByJavascript(element);
    }

    @Step("[Overview page] Click on cancel button")
    public void clickOnCancelButton() {
        WebElement element = getElementByCss(CheckoutPageLocators.BTN_CANCEL_CSS);
        clickToElement_ByJavascript(element);
    }

    @Step("[Checkout: Complete page] Click on back to home button")
    public void clickOnBackToHomeButton() {
        WebElement element = getElementByCss(CheckoutPageLocators.BTN_BACKTOHOME_CSS);
        clickToElement_ByJavascript(element);
    }

    /*****************************
     *** Verify methods **********
     *****************************/

    @Step("[Overview page] Verify header of Overview page is displayed")
    public void verify_HeaderOfInformationPage_IsDisplayed() {
        WebElement element = getElementByXpath(CheckoutPageLocators.HEADER_OVERVIEW_PAGE_XPATH);

        Assert.assertTrue(element.isDisplayed(), "Header of Overview Page is NOT displayed");
    }

    @Step("[Overview page] Verify there are {number} product in cart list")
    public void verify_Number_Product_InCartList(int numberProduct) {
        String elementXpath = String.format(CheckoutPageLocators.LIST_ITEM_IN_CART);
        List<WebElement> element = getElementsByXpath(elementXpath);

        Assert.assertEquals(element.size(), numberProduct, "Number of product is not the same in cart list");
    }

    @Step("[Overview page] Verify product name: {productName} is displayed in cart list")
    public void verify_ProductName_IsDisplayed(String productName) {
        String elementXpath = String.format(CheckoutPageLocators.ITEM_IN_CART, productName);
        WebElement element = getElementByXpath(elementXpath);

        Assert.assertTrue(element.isDisplayed(), "Product name is NOT displayed in cart list");
    }

    @Step("[Overview page] Verify quantity of product name {productName} is: {quantity} in cart list")
    public void verify_Quantity_OfProductName(String productName, int quantity) {
        String elementXpath = String.format(CheckoutPageLocators.QUANTITY_OF_A_ITEM, productName);
        WebElement element = getElementByXpath(elementXpath);

        Assert.assertEquals(Integer.parseInt(element.getText()), quantity, "Quantity is not the same");
    }

    @Step("[Overview page] Verify price of product name {productName} is: {price} in cart list")
    public void verify_Price_OfProductName(String productName, String price) {
        String elementXpath = String.format(CheckoutPageLocators.PRICE_OF_A_ITEM, productName);
        WebElement element = getElementByXpath(elementXpath);

        Assert.assertEquals(element.getText(), price, "Price is not the same");
    }

    @Step("[Checkout: Complete page] Verify header of Checkout: Complete page is displayed")
    public void verify_HeaderOfCheckoutCompletePage_IsDisplayed() {
        WebElement element = getElementByXpath(CheckoutPageLocators.HEADER_CHECKOUT_PAGE_XPATH);

        Assert.assertTrue(element.isDisplayed(), "Header of Checker: Complete Page is NOT displayed");
    }

    @Step("[Checkout: Complete page] Verify thank you message is displayed")
    public void verify_ThankYouMessage_IsDisplayed() {
        WebElement element = getElementByXpath(CheckoutPageLocators.THANK_YOU_MESSAGE);

        Assert.assertTrue(element.isDisplayed(), "No thank you message appear to the user");
    }
}
