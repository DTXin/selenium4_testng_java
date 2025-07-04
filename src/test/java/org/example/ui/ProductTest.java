package org.example.ui;

import org.example.fw_ui.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @BeforeMethod
    public void login() {
        getLoginPage().login("standard_user", "secret_sauce");
    }

    @Test
    public void TC01_Verify_RemoveButtonDisplayed_AfterClickOnAddToCart() {
        getProductPage().clickOnAddToCartButton("Sauce Labs Backpack");
        getProductPage().verify_RemoveButton_IsDisplayed("Sauce Labs Backpack");
    }

    @Test
    public void TC01_Verify_ListProductName_OrderByAscending() {
        getProductPage().selectTextOnSortDropdown("Name (A to Z)");
        getProductPage().verify_ListProductName_OrderByAscending();
    }

    @Test
    public void TC02_Verify_ListProductName_OrderByDescending() {
        getProductPage().selectTextOnSortDropdown("Name (Z to A)");
        getProductPage().verify_ListProductName_OrderByDecending();
    }
}
