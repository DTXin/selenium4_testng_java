package org.example;

import org.example.fw.base.BaseTest;
// import org.example.fw.manager.DriverManager;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    // @Test(description = "Verify login successfully 1")
    // public void TC01_Login_GoodPassword1() {
    // DriverManager.getDriver().navigate().to("https://www.saucedemo.com/");
    // getLoginPage().inputTextToUsername("standard_user");
    // getLoginPage().inputTextToPassword("secret_sauce");
    // getLoginPage().clickOnLoginButton();
    // // getLoginPage().login("standard_user", "secret_sauce");
    // getProductPage().verifyProductPageIsDisplayed();
    // }

    // @Test(description = "Verify login successfully 2")
    // public void TC02_Login_WrongPassword2() {
    // DriverManager.getDriver().navigate().to("https://www.saucedemo.com/");
    // getLoginPage().inputTextToUsername("problem_user");
    // getLoginPage().inputTextToPassword("secret_sauce");
    // getLoginPage().clickOnLoginButton();
    // // getLoginPage().login("problem_user", "secret_sauce");
    // getProductPage().verifyProductPageIsDisplayed();
    // }

    // @Test(description = "Verify login successfully 3")
    // public void TC02_Login_ValidPassword3() {
    // DriverManager.getDriver().navigate().to("https://www.saucedemo.com/");
    // getLoginPage().inputTextToUsername("performance_glitch_user");
    // getLoginPage().inputTextToPassword("secret_sauce");
    // getLoginPage().clickOnLoginButton();
    // // getLoginPage().login("performance_glitch_user", "secret_sauce");
    // getProductPage().verifyProductPageIsDisplayed();
    // }

    // @Test(description = "Verify login successfully 4")
    // public void TC01_Login_GoodPassword4() {
    // DriverManager.getDriver().navigate().to("https://www.saucedemo.com/");
    // getLoginPage().inputTextToUsername("standard_user");
    // getLoginPage().inputTextToPassword("secret_sauce");
    // getLoginPage().clickOnLoginButton();
    // // getLoginPage().login("standard_user", "secret_sauce");
    // getProductPage().verifyProductPageIsDisplayed();
    // }

    // @Test(description = "Verify login successfully 5")
    // public void TC02_Login_WrongPassword5() {
    // DriverManager.getDriver().navigate().to("https://www.saucedemo.com/");
    // getLoginPage().inputTextToUsername("problem_user");
    // getLoginPage().inputTextToPassword("secret_sauce");
    // getLoginPage().clickOnLoginButton();
    // // getLoginPage().login("problem_user", "secret_sauce");
    // getProductPage().verifyProductPageIsDisplayed();
    // }

    // @Test(description = "Verify login successfully 6")
    // public void TC02_Login_ValidPassword6() {
    // DriverManager.getDriver().navigate().to("https://www.saucedemo.com/");
    // getLoginPage().inputTextToUsername("performance_glitch_user");
    // getLoginPage().inputTextToPassword("secret_sauce");
    // getLoginPage().clickOnLoginButton();
    // // getLoginPage().login("performance_glitch_user", "secret_sauce");
    // getProductPage().verifyProductPageIsDisplayed();
    // }

    @Test(description = "Verify login successfully 1")
    public void TC01_Login_GoodPassword1() {
        getLoginPage().login("standard_user", "secret_sauce");
        getProductPage().verifyProductPageIsDisplayed();
    }

    @Test(description = "Verify login successfully 2")
    public void TC02_Login_WrongPassword2() {
        getLoginPage().login("problem_user", "wrong_password");
        getLoginPage().verifyErrorDisplayed();
    }

    @Test(description = "Verify login successfully 3")
    public void TC02_Login_ValidPassword3() {
        getLoginPage().login("performance_glitch_user", "secret_sauce1");
        getProductPage().verifyProductPageIsDisplayed();
    }
}
