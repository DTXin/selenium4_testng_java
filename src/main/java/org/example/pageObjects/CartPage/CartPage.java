package org.example.pageObjects.CartPage;

import org.example.fw.base.BasePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CartPage extends BasePage {

    // Click on checkout button
    public void clickOnCheckoutButton() {
        logger.info("User click on checkout button.");

        WebElement checkoutButton = getElementByXpath(CartPageLocators.CHECKOUT_BUTTON);
        checkoutButton.click();
    }

    // Click on continue shopping button
    public void clickOnContinueShoppingButton() {
        logger.info("User click on continue shopping button.");

        WebElement continueShoppingButton = getElementByXpath(CartPageLocators.CONTINUE_SHOPPING_BUTTON);
        continueShoppingButton.click();
    }

    // Click on remove button for a specific item
    public void clickOnRemoveButton(String itemName) {
        logger.info("User click on remove button of item: {}", itemName);

        String elementXpath = String.format(CartPageLocators.REMOVE_BUTTON, itemName);
        WebElement removeButton = getElementByXpath(elementXpath);
        removeButton.click();
    }

    // Verify if the cart page is displayed
    public void verifyCartPage_IsDisplayed() {
        WebElement cartPageHeader = getElementByXpath(CartPageLocators.CART_PAGE);

        logger.info("Verify cart page is displayed {}.", cartPageHeader.isDisplayed());
        Assert.assertTrue(cartPageHeader.isDisplayed(), "Cart page is NOT displayed.");
    }

    // Verify checkout button is displayed
    public void verifyCheckoutButton_IsDisplayed() {
        WebElement checkoutButton = getElementByXpath(CartPageLocators.CHECKOUT_BUTTON);

        logger.info("Verify checkout button is displayed.");
        Assert.assertTrue(checkoutButton.isDisplayed(), "Checkout buttong is NOT displayed.");
    }

    // Verify continue shopping button is displayed
    public void verifyContinueShoppingButton_IsDisplayed() {
        WebElement continueShoppingButotn = getElementByXpath(CartPageLocators.CONTINUE_SHOPPING_BUTTON);

        logger.info("Verify continue shopping button is displayed.");
        Assert.assertTrue(continueShoppingButotn.isDisplayed(), "Continue shopping is NOT displayed.");
    }

    // Verify an item is in the cart
    public void verifyItemInCart(String itemName) {
        String elementXpath = String.format(CartPageLocators.CART_ITEM_NAME);
        WebElement item = getElementByXpath(elementXpath);

        logger.info("Verify item name: {} is in the cart.", itemName);
        Assert.assertTrue(item.isDisplayed(), "Item name is NOT in the cart");
    }

    // Verify an item is not in the cart
    public void verifyItemNotInCart(String itemName) {
        String elementXpath = String.format(CartPageLocators.CART_ITEM_NAME);
        WebElement item = getElementByXpath(elementXpath);

        logger.info("Verify item name: {} is not in the cart.", itemName);
        Assert.assertFalse(item.isDisplayed(), "Item name is in the cart");
    }

}
