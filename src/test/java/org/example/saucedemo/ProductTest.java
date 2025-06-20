package org.example.saucedemo;

import org.example.fw.base.BaseTest;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @Test
    public void TC01_Verify_RemoveButtonIsDisplayed_AfterClickOnAddToCart() {
        getLoginPage().login("standard_user", "secret_sauce");
        getProductPage().verifyProductPageIsDisplayed();
        getProductPage().clickOnAddToCartButton("Sauce Labs Backpack");
        getProductPage().verifyRemoveButtonIsDisplayed("Sauce Labs Backpack");
    }

    @Test
    public void TC01_Verify_ListProductName_OrderByAscending() {
        getLoginPage().login("standard_user", "secret_sauce");
        getProductPage().verifyProductPageIsDisplayed();
        getProductPage().selectTextOnSortDropdown("Name (A to Z)");
        getProductPage().verifyListProductName_IsOrderByAscending();
    }

    @Test
    public void TC02_Verify_ListProductName_OrderByDescending() {
        getLoginPage().login("standard_user", "secret_sauce");
        getProductPage().verifyProductPageIsDisplayed();
        getProductPage().selectTextOnSortDropdown("Name (Z to A)");
        getProductPage().verifyListProductName_IsOrderByDecending();
    }
}
