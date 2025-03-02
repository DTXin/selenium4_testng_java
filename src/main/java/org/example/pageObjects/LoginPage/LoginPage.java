package org.example.pageObjects.LoginPage;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.base.AbstractPage;
import org.example.base.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {
    protected static final Logger logger = LogManager.getLogger();

    public WebDriver driver;

    @FindBy(css="input#user-name")
    WebElement username;

    @FindBy(css="input#password")
    WebElement password;

    @FindBy(id="login-button")
    WebElement loginButton;

    public LoginPage(WebDriver driver1) {
        this.driver = driver1;
        PageFactory.initElements(DriverManager.getDriver(), this);
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
