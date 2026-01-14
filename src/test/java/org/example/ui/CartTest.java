package org.example.ui;

import org.example.fw_ui.base.BaseTest;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void T01_Verify_CheckOutButon_IsDisplayed() {
        getProductPage().clickOnAddToCartButton("Sauce Labs Backpack");
        getProductPage().clickOnShoppingCartButton();
        getCartPage().verify_HeaderOfCartPage_IsDisplayed();
        getCartPage().verify_CheckoutButton_IsDisplayed();
    }

}
