package org.example.pageObjects.LoginPage;

import io.qameta.allure.Step;
import org.example.fw.base.BasePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends BasePage {

    @Step("Input text to username field")
    public void inputTextToUsername(String text) {
        WebElement username = getElementByCss(LoginPageLocators.USERNAME_CSS);

        logger.info("User send text `{}` to username field", text);
        username.sendKeys(text);
    }

    @Step("Input text to password field")
    public void inputTextToPassword(String text) {
        WebElement password = getElementByCss(LoginPageLocators.PASSWORD_CSS);

        logger.info("User send text `{}` to password field", text);
        password.sendKeys(text);
    }

    @Step("Click on login button")
    public void clickOnLoginButton() {
        WebElement loginButton = getElementByCss(LoginPageLocators.LOGIN_BUTTON_CSS);

        logger.info("User click on login button");
        loginButton.click();
    }

    @Step("Verify error is displayed after login failed")
    public void verifyErrorDisplayed() {
        WebElement error = getElementByXpath(LoginPageLocators.ERROR);

        logger.info("User click on login button");
        Assert.assertTrue(error.isDisplayed(), "Error is NOT displayed");
    }

    /*
     * High level step
     */
    public void login(String username, String password) {
        navigateToURL();
        inputTextToUsername(username);
        inputTextToPassword(password);
        clickOnLoginButton();
    }
}
