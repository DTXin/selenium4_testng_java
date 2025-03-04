package org.example.base;

import java.util.List;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public abstract class AbstractPage {
    protected static final Logger logger = LogManager.getLogger();

    /********************************************************************
     ** Start Blocks: Group action on Element (Ex: Click, Hover...) *****
     *******************************************************************/

    // Click to element
    public void clickToElement(WebElement element) {
        scrollToElenment(element);

        logger.info("Click to element: `{}`", element);
        element.click();
    }

    // Click an element using javascript executor.
    public void clickToElement_ByJavaScript(WebElement element) throws InterruptedException {
        scrollToElenment(element);

        logger.info("Click to element (by javascript): `{}`", element);
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();", element);
    }

    // Get text of element by javascript
    public String getText_ByJavaScript(WebElement element) {
        logger.info("Get text of an element (by javascript): `{}`", element);

        String textOfElement = "";
        textOfElement = (String) ((JavascriptExecutor) DriverManager.getDriver())
                .executeScript("return arguments[0].value;", element);
        logger.info("Text of element is: `{}`", textOfElement);

        return textOfElement;
    }

    /********************************************************************
     ** End Blocks: Group action on Element (Ex: Click, Hover...) *****
     *******************************************************************/

    /********************************************************************
     ** Start Blocks: Scroll Page (Ex: Scroll top, bottom...) ***********
     *******************************************************************/

    // Scroll into view of the browser window
    public void scrollToElenment(WebElement element) {
        logger.info("Scroll to element into view...: `{}`", element);
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript(
                "arguments[0].scrollIntoView({block: \"center\",inline: \"center\",behavior: \"smooth\"});",
                element);
    }

    // Scroll to top in page
    public void scrollToTop() {
        logger.info("Scroll up to footer page...");
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("document.documentElement.scrollTop = 0;");
    }

    // Scroll to bottom in page
    public void scrollToBottom() {
        logger.info("Scroll down to footer page...");
        ((JavascriptExecutor) DriverManager.getDriver())
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /********************************************************************
     ** End Blocks: Scroll Page (Ex: Scroll top, footer...) *************
     *******************************************************************/

    /********************************************************************
     ** Start Blocks: Convert xpath, css, id... to WebElement ***********
     * 
     ** @param1: String xpath or css or id ******************************
     ** @param2: String for formatting **********************************
     *******************************************************************/

    // Convert element from xpath which can not use @FindBy fo find
    public WebElement getElementByXpath(String xpath, String... arg) {
        WebElement webElement = null;

        try {
            logger.info("Convert xpath element to webelement: xpath is `{}` ", xpath);
            webElement = DriverManager.getDriver().findElement(By.xpath(xpath));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        return webElement;
    }

    // Convert list of element from xpath which can not use @FindBy fo find
    public List<WebElement> getElementsByXpath(String xpath, String... arg) {
        List<WebElement> listElement = new ArrayList<WebElement>();

        try {
            logger.info("Convert xpath of a list element to list webElement: xpath is `{}` ", xpath);
            listElement = DriverManager.getDriver().findElements(By.xpath(xpath));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        return listElement;
    }

    /********************************************************************
     ** End Blocks: Convert xpath, css, id... to WebElement *************
     *******************************************************************/
}
