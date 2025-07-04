package org.example.fw_ui.pageObjects.CartPage;

import org.example.fw_ui.base.BasePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.qameta.allure.Step;

public class CartPage extends BasePage {

    /*****************************
     *** Action methods **********
     *****************************/

    @Step("[Cart Page] Click on checkout button")
    public void clickOnCheckoutButton() {
        WebElement checkoutButton = getElementByXpath(CartPageLocators.BTN_CHECKOUT_XPATH);
        clickToElement_ByJavascript(checkoutButton);
    }

    @Step("[Cart Page] Click on continue shopping button")
    public void clickOnContinueShoppingButton() {
        WebElement continueShoppingButton = getElementByXpath(CartPageLocators.BTN_CONTINUE_SHOPPING_XPATH);
        clickToElement_ByJavascript(continueShoppingButton);
    }

    @Step("[Cart Page] Click on remove button for a specific item: {itemName}")
    public void clickOnRemoveButton(String itemName) {
        String elementXpath = String.format(CartPageLocators.BTN_REMOVE_XPATH, itemName);
        WebElement removeButton = getElementByXpath(elementXpath);
        clickToElement_ByJavascript(removeButton);
    }

    /*****************************
     *** Verify methods **********
     *****************************/

    @Step("[Cart Page] Verify header of cart page is displayed")
    public void verify_HeaderOfCartPage_IsDisplayed() {
        WebElement cartPageHeader = getElementByXpath(CartPageLocators.HEADER_TITLE_CARTPAGE);

        Assert.assertTrue(cartPageHeader.isDisplayed());
    }

    @Step("[Cart Page] Verify checkout button is displayed")
    public void verify_CheckoutButton_IsDisplayed() {
        WebElement checkoutButton = getElementByXpath(CartPageLocators.BTN_CHECKOUT_XPATH);

        Assert.assertTrue(checkoutButton.isDisplayed());
    }

    @Step("[Cart Page] Verify continue shopping button is displayed")
    public void isContinueShoppingButtonDisplayed() {
        WebElement continueShoppingButotn = getElementByXpath(CartPageLocators.BTN_CONTINUE_SHOPPING_XPATH);

        Assert.assertTrue(continueShoppingButotn.isDisplayed());
    }

    @Step("[Cart Page] Verify item name: {itemName} exist in the cart")
    public void verify_ItemName_ExistInCart(String itemName) {
        String elementXpath = String.format(CartPageLocators.ITEM_NAME, itemName);
        WebElement item = getElementByXpath(elementXpath);

        Assert.assertTrue(item.isDisplayed());
    }
}
