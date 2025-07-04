package org.example.ui;

import org.example.fw_ui.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InformationTest extends BaseTest {

    @BeforeMethod
    public void login() {
        getLoginPage().login("standard_user", "secret_sauce");
    }

    @Test
    public void T01_Verify_ErrorIsDisplayed_WithoutFirstName() {
        getProductPage().clickOnAddToCartButton("Sauce Labs Backpack");
        getProductPage().clickOnShoppingCartlink();
        getCartPage().verify_HeaderOfCartPage_IsDisplayed();
        getCartPage().clickOnCheckoutButton();
        getInformationPage().verify_HeaderOfInformationPage_IsDisplayed();
        getInformationPage().inputTextToLastName("Dinh Tran");
        getInformationPage().clickOnContinueButton();
        getInformationPage().verify_ContentOfErrorMessage("Error: First Name is required");
    }

    @Test
    public void T02_Verify_ErrorIsDisplayed_WithoutLastName() {
        getProductPage().clickOnAddToCartButton("Sauce Labs Backpack");
        getProductPage().clickOnShoppingCartlink();
        getCartPage().verify_HeaderOfCartPage_IsDisplayed();
        getCartPage().clickOnCheckoutButton();
        getInformationPage().verify_HeaderOfInformationPage_IsDisplayed();
        getInformationPage().inputTextToFirstName("Xin");
        getInformationPage().clickOnContinueButton();
        getInformationPage().verify_ContentOfErrorMessage("Error: Last Name is required");
    }
}
