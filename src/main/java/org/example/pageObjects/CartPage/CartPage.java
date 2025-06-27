package org.example.pageObjects.CartPage;

import org.example.fw.base.BasePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.qameta.allure.Step;

public class CartPage extends BasePage {

    /*****************************
     *** Action methods **********
     *****************************/

    @Step("[Cart Page] Click on checkout button")
    public void clickOnCheckoutButton() {
        WebElement checkoutButton = getElementByXpath(CartPageLocators.CHECKOUT_BUTTON);
        clickToElement_ByJavascript(checkoutButton);
    }

    @Step("[Cart Page] Click on continue shopping button")
    public void clickOnContinueShoppingButton() {
        WebElement continueShoppingButton = getElementByXpath(CartPageLocators.CONTINUE_SHOPPING_BUTTON);
        clickToElement_ByJavascript(continueShoppingButton);
    }

    @Step("[Cart Page] Click on remove button for a specific item: {itemName}")
    public void clickOnRemoveButton(String itemName) {
        String elementXpath = String.format(CartPageLocators.REMOVE_BUTTON, itemName);
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
        WebElement checkoutButton = getElementByXpath(CartPageLocators.CHECKOUT_BUTTON);

        Assert.assertTrue(checkoutButton.isDisplayed());
    }

    @Step("[Cart Page] Verify continue shopping button is displayed")
    public void isContinueShoppingButtonDisplayed() {
        WebElement continueShoppingButotn = getElementByXpath(CartPageLocators.CONTINUE_SHOPPING_BUTTON);

        Assert.assertTrue(continueShoppingButotn.isDisplayed());
    }

    @Step("[Cart Page] Verify item name: {itemName} exist in the cart")
    public void verify_ItemName_ExistInCart(String itemName) {
        String elementXpath = String.format(CartPageLocators.CART_ITEM_NAME);
        WebElement item = getElementByXpath(elementXpath);

        Assert.assertTrue(item.isDisplayed());
    }
}
