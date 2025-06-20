package org.example.saucedemo;

import org.example.fw.base.BaseTest;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void T01_VerifyCheckoutButon_IsDisplayed() {
        getLoginPage().login("standard_user", "secret_sauce");
        getProductPage().verifyProductPageIsDisplayed();
        getProductPage().clickOnAddToCartButton("Sauce Labs Backpack");
        getProductPage().clickOnShoppingCartlink();
        getCartPage().verifyCartPage_IsDisplayed();
        getCartPage().verifyCheckoutButton_IsDisplayed();
    }

    @Test
    public void T02_VerifyContinueShoppingButon_IsDisplayed() {
        getLoginPage().login("standard_user", "secret_sauce");
        getProductPage().verifyProductPageIsDisplayed();
        getProductPage().clickOnAddToCartButton("Sauce Labs Backpack");
        getProductPage().clickOnShoppingCartlink();
        getCartPage().verifyCartPage_IsDisplayed();
        getCartPage().verifyContinueShoppingButton_IsDisplayed();
    }
}
