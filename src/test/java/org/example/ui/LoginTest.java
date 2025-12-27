package org.example.ui;

import org.example.fw_ui.base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Verify login successfully!", dataProvider = "valid-logins")
    public void TC01_ValidLogin(String username, String password) {
        getLoginPage().login(username, password);
        getProductPage().verify_ProductPage_IsDisplayed();
    }

//    @Test(description = "Verify login unsuccessfully!")
//    public void TC02_InvalidLogin() {
//        getLoginPage().login("standard_user", "wrong_pass");
//        getLoginPage().verify_ErrorMessageContainer_IsDisplayed();
//    }

    @DataProvider(name = "valid-logins")
    public static Object[][] getValidLogins() {
        return new Object[][] {
                { "standard_user", "secret_sauce" },
                { "problem_user", "secret_sauce" },
                { "performance_glitch_user", "secret_sauce" }
        };
    }
}