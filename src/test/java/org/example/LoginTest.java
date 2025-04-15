package org.example;

import org.example.base.BaseTest;
import org.example.base.PageManager;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Verify login successfully")
    public void TC01_Login_GoodPassword() {
        navigateToUrl();
        PageManager.getPageManager().getLoginPage().inputTextToUsername("standard_user");
        PageManager.getPageManager().getLoginPage().inputTextToPassword("secret_sauce");
        PageManager.getPageManager().getLoginPage().clickOnLoginButton();
        PageManager.getPageManager().getProductPage().verifyProductPageIsDisplayed();
    }

    @Test(description = "Verify login successfully")
    public void TC02_Login_WrongPassword() {
        navigateToUrl();
        PageManager.getPageManager().getLoginPage().inputTextToUsername("standard_user");
        PageManager.getPageManager().getLoginPage().inputTextToPassword("secret_sauce");
        PageManager.getPageManager().getLoginPage().clickOnLoginButton();
        PageManager.getPageManager().getProductPage().verifyProductPageIsDisplayed();
    }
}
