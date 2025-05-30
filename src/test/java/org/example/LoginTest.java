package org.example;

import org.example.base.BaseTest;
import org.example.base.PageManager;
import org.example.base.TestListener;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

@Listeners({ TestListener.class })
public class LoginTest extends BaseTest {

    @Test(description = "Verify login successfully 1")
    public void TC01_Login_GoodPassword() {
        PageManager.getPageManager().getLoginPage().login("standard_user", "secret_sauce");
        // PageManager.getPageManager().getLoginPage().inputTextToUsername("standard_user");
        // PageManager.getPageManager().getLoginPage().inputTextToPassword("secret_sauce");
        // PageManager.getPageManager().getLoginPage().clickOnLoginButton();
        PageManager.getPageManager().getProductPage().verifyProductPageIsDisplayed();
    }

    @Test(description = "Verify login successfully 2")
    public void TC02_Login_WrongPassword() {
        PageManager.getPageManager().getLoginPage().login("standard_user", "secret_sauce1");
        // PageManager.getPageManager().getLoginPage().inputTextToUsername("standard_user");
        // PageManager.getPageManager().getLoginPage().inputTextToPassword("secret_sauce1");
        // PageManager.getPageManager().getLoginPage().clickOnLoginButton();
        PageManager.getPageManager().getProductPage().verifyProductPageIsDisplayed();
    }
}
