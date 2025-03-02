package org.example.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

public abstract class AbstractPage {

    // Click an element using javascript executor.
    public void clickUsingJavaScript(WebDriver driver,WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    // Source: https://www.selenium.dev/documentation/webdriver/actions_api/wheel/
    // Scroll to element
    public void scrollToElement(WebDriver driver, WebElement element) {
        new Actions(driver).scrollToElement(element).perform();
    }


    // Scroll to element using javascript executor
    public void sendKeysUsingJavaScript(WebDriver driver, WebElement element, String text) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("document." + element + ".setAttribute('value','" + text + "');");
        executor.executeScript("document.getElementById('email').setAttribute('value','admin02@mailinator.com');");
        executor.executeScript("document.getElementById('password').setAttribute('value','123456');");
    }
}
