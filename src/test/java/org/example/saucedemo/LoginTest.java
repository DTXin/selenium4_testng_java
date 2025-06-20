package org.example.saucedemo;

import org.example.fw.base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Verify login successfully!", dataProvider = "valid-logins")
    public void TC01_ValidLogin(String username, String password) {
        getLoginPage().login(username, password);
        getProductPage().verifyProductPageIsDisplayed();
    }

    @Test(description = "Verify login unsuccessfully!")
    public void TC02_InvalidLogin() {
        getLoginPage().login("invalid_user", "invalid_password");
        getLoginPage().verifyErrorIsDisplayed();
    }

    @DataProvider(name = "valid-logins")
    public static Object[][] getValidLogins() {
        return new Object[][] {
                { "standard_user", "secret_sauce" },
                { "problem_user", "secret_sauce" },
                { "performance_glitch_user", "secret_sauce" }
        };
    }
}