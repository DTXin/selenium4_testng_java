package org.example.fw_ui.pageObjects.LoginPage;

public class LoginPageLocators {
    public static final String TXT_USERNAME_CSS = "input#user-name";
    public static final String TXT_PASSWORD_CSS = "input#password";

    public static final String BTN_LOGIN_CSS = "input#login-button";
    
    public static final String ERROR_MESSAGE_CONTAINER = "//div[@class=\"error-message-container error\"]";
    public static final String ERROR_MESSAGE_INVALID_USER = ERROR_MESSAGE_CONTAINER + "//h3[contains(text(),\"Username and password do not match any user in this service\")]";
    public static final String ERROR_MESSAGE_LOCKED_USER = ERROR_MESSAGE_CONTAINER + "//h3[contains(text(),\"this user has been locked out\")]";

}
