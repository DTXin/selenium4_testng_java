package org.example.saucedemo;

import org.example.fw.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @BeforeMethod
    public void login() {
        getLoginPage().login("standard_user", "secret_sauce");
    }

    @Test
    public void T01_Verify_CheckOutButon_IsDisplayed() {
        getProductPage().clickOnAddToCartButton("Sauce Labs Backpack");
        getProductPage().clickOnShoppingCartlink();
        getCartPage().verify_HeaderOfCartPage_IsDisplayed();
        getCartPage().verify_CheckoutButton_IsDisplayed();
    }

}
