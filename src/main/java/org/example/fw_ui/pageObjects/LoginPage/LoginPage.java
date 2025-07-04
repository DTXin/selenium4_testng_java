package org.example.fw_ui.pageObjects.LoginPage;

import io.qameta.allure.Step;

import org.example.fw_ui.base.BasePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends BasePage {

    /*****************************
     *** Action methods **********
     *****************************/

    @Step("[Login Page] Input text to username field")
    public void inputTextToUsername(String text) {
        WebElement username = getElementByCss(LoginPageLocators.TXT_USERNAME_CSS);
        username.sendKeys(text);
    }

    @Step("[Login Page] Input text to password field")
    public void inputTextToPassword(String text) {
        WebElement password = getElementByCss(LoginPageLocators.TXT_PASSWORD_CSS);
        password.sendKeys(text);
    }

    @Step("[Login Page] Click on login button")
    public void clickOnLoginButton() {
        WebElement loginButton = getElementByCss(LoginPageLocators.BTN_LOGIN_CSS);
        loginButton.click();
    }

    /*****************************
     *** Verify methods **********
     *****************************/

    @Step("[Login Page] Verify error message container is displayed")
    public void verify_ErrorMessageContainer_IsDisplayed() {
        WebElement error = getElementByXpath(LoginPageLocators.ERROR_MESSAGE_CONTAINER);

        Assert.assertTrue(error.isDisplayed(), "Error message container is NOT displayed");
    }

    /*
     * High level step
     */
    public void login(String username, String password) {
        inputTextToUsername(username);
        inputTextToPassword(password);
        clickOnLoginButton();
    }
}
