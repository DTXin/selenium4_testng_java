package org.example.pageObjects.LoginPage;

import io.qameta.allure.Step;
import org.example.base.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {

    @FindBy(css = "input#user-name")
    WebElement username;

    @FindBy(css = "input#password")
    WebElement password;

    @FindBy(id = "login-button")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @Step("Input text to username field")
    public void inputTextToUsername(String text) {
        username.sendKeys(text);
    }

    @Step("Input text to password field")
    public void inputTextToPassword(String text) {
        password.sendKeys(text);
    }

    @Step("Click on login button")
    public void clickOnLoginButton() {
        loginButton.click();
    }
}
