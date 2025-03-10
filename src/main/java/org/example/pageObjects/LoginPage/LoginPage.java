package org.example.pageObjects.LoginPage;

import io.qameta.allure.Step;
import org.example.base.BasePage;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    @Step("Input text to username field")
    public void inputTextToUsername(String text) {
        WebElement username = getElementByCss(LoginPageLocators.USERNAME_CSS);
        username.sendKeys(text);
    }

    @Step("Input text to password field")
    public void inputTextToPassword(String text) {
        WebElement password = getElementByCss(LoginPageLocators.PASSWORD_CSS);
        password.sendKeys(text);
    }

    @Step("Click on login button")
    public void clickOnLoginButton() {
        WebElement loginButton = getElementByCss(LoginPageLocators.LOGINBUTTON_CSS);
        loginButton.click();
    }
}
