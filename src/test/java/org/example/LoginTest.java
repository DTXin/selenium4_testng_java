package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.base.AbstractTest;
import org.example.base.PageManager;
import org.testng.annotations.Test;

public class LoginTest extends AbstractTest {
    protected static final Logger logger = LogManager.getLogger();

    @Test(description = "Verify login successfully")
    public void TC01_Login_GoodPassword() {
        PageManager.getPageManager().getLoginPage().inputTextToUsername("standard_user");
        PageManager.getPageManager().getLoginPage().inputTextToPassword("secret_sauce");
        PageManager.getPageManager().getLoginPage().clickOnLoginButton();
        PageManager.getPageManager().getProductPage().verifyProductPageIsDisplayed();
    }

    @Test(description = "Verify login successfully")
    public void TC02_Login_WrongPassword() {
        PageManager.getPageManager().getLoginPage().inputTextToUsername("standard_user");
        PageManager.getPageManager().getLoginPage().inputTextToPassword("123");
        PageManager.getPageManager().getLoginPage().clickOnLoginButton();
        PageManager.getPageManager().getProductPage().verifyProductPageIsDisplayed();
    }
}
