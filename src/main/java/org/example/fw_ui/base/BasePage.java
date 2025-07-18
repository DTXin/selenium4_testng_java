package org.example.fw_ui.base;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.fw_ui.manager.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class BasePage extends DriverManager {
    public final Logger logger = LogManager.getLogger();

    /********************************************************************
     ** Start Blocks: Group action on Element (Ex: Click, Hover...) *****
     *******************************************************************/

    // Click to element
    public void clickToElement(WebElement element) {
        scrollToElement(element);

        logger.info("Click to element: `{}`", element);
        element.click();
    }

    // Click an element using javascript executor.
    public void clickToElement_ByJavascript(WebElement element) {
        scrollToElement(element);

        logger.info("Click to element (by javascript): `{}`", element);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
    }

    // Input text to an element using javascript executor.
    public void inputText_ByJavascript(WebElement element, String textValue) {
        scrollToElement(element);

        logger.info("Input text to element (by javascript): `{}`", element);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].value='" + textValue + "';", element);
    }

    // Select a text from dropdown
    public void selectTextOnDropdown(WebElement dropdown, String selectText) {
        logger.info("Select a text: {} on dropdown: {}", selectText, dropdown);

        Select select = new Select(dropdown);
        select.selectByVisibleText(selectText);
    }

    // Get text of an element by javascript
    public String getText_ByJavaScript(WebElement element) {
        logger.info("Get text of an element (by javascript): `{}`", element);

        String textOfElement = (String) ((JavascriptExecutor) getDriver())
                .executeScript("return arguments[0].value;", element);

        logger.info("Text of element is: `{}`", textOfElement);
        return textOfElement;
    }

    // Get text of elements
    public List<String> getTextAllElements(List<WebElement> elements) {
        logger.info("Get text of multiple element.");

        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    /********************************************************************
     ** Start Blocks: Scroll Page (Ex: Scroll top, bottom...) ***********
     *******************************************************************/

    // Scroll into view of the browser window
    public void scrollToElement(WebElement element) {
        logger.info("Scroll to element into view...: `{}`", element);
        ((JavascriptExecutor) getDriver()).executeScript(
                "arguments[0].scrollIntoView({block: \"center\",inline: \"center\",behavior: \"smooth\"});", element);
    }

    // Scroll to top in page
    public void scrollToTop() {
        logger.info("Scroll up to footer page...");
        ((JavascriptExecutor) getDriver()).executeScript("document.documentElement.scrollTop = 0;");
    }

    // Scroll to bottom in page
    public void scrollToBottom() {
        logger.info("Scroll down to footer page...");
        ((JavascriptExecutor) getDriver())
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /********************************************************************
     ** Start Blocks: Convert xpath, css, id... to WebElement ***********
     ** 
     ** @param1: String xpath or css or id ******************************
     ** @param2: String for formatting **********************************
     *******************************************************************/

    // Convert element from xpath which can not use @FindBy fo find
    public WebElement getElementByXpath(String xpath, String... arg) {
        WebElement webElement = null;

        try {
            logger.info("Convert xpath element to web element: xpath is `{}` ", xpath);
            webElement = getDriver().findElement(By.xpath(xpath));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        return webElement;
    }

    // Convert list of element from xpath which can not use @FindBy fo find
    public List<WebElement> getElementsByXpath(String xpath, String... arg) {
        List<WebElement> listElement = new ArrayList<>();

        try {
            logger.info("Convert xpath of a list element to list webElement: xpath is `{}` ", xpath);
            listElement = getDriver().findElements(By.xpath(xpath));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        return listElement;
    }

    // Convert element from css which can not use @FindBy
    public WebElement getElementByCss(String css, String... arg) {
        WebElement webElement = null;

        try {
            logger.info("Convert css element to web element: css is `{}` ", css);
            webElement = getDriver().findElement(By.cssSelector(css));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        return webElement;
    }

    // Convert css of list element to list webElement which can not use @FindBy
    public List<WebElement> getElementsByCss(String css, String... arg) {
        List<WebElement> listElement = new ArrayList<>();

        try {
            logger.info("Convert css of a list element to list webElement: css is `{}` ", css);
            listElement = getDriver().findElements(By.cssSelector(css));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        return listElement;
    }

    /***************************************************************************
     ** Start Blocks: Waiting element (Ex: wait for clickable, for visible....)*
     **************************************************************************/

    // Wait element for visible
    public void waitUntilElement_ForVisible(WebElement element) {
        logger.info("Wait until element `{}` for visible", element);
        getDriverWait().until(ExpectedConditions.visibilityOf(element));
    }

    // Wait element for clickable
    public void waitUntilElement_ForClickable(WebElement element) {
        logger.info("Wait until element `{}` for clickable", element);
        getDriverWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    // Wait element for invisible
    public void waitUntilElement_ForInvisible(WebElement element) {
        logger.info("Wait until element `{}` for invisible", element);
        getDriverWait().until(ExpectedConditions.invisibilityOf(element));
    }

    // Wait until text present
    public void waitUntilText_ForPresent(WebElement element, String expectedText) {
        logger.info("Wait until text `{}` for present", expectedText);
        getDriverWait().until(ExpectedConditions.textToBePresentInElement(element, expectedText));
    }

    /***************************************************************************
     ** Start Blocks: for popup or dialog boxes: Alert, Confirms, Prompts.... **
     ***************************************************************************/
    public void waitUntil_AlertDialog_IsDisplayed() {
        logger.info("Wait until alert/confirms/prompts dialog is displayed...");
        getDriverWait().until(ExpectedConditions.alertIsPresent());
    }

    /********************************
     ** Start Blocks: Other.... *****
     *******************************/

    // Get current URL of page
    public String getCurrentURL() {
        String url = getDriver().getCurrentUrl();

        logger.info("The current URL: {}", url);
        return url;
    }

    // Close all tabs and except the first tab
    public void closeAllTabsExceptFirstTab() {
        logger.info("Close all tabs and except the first one...");

        String originalTab = getDriver().getWindowHandle();
        for (String tab : getDriver().getWindowHandles()) {
            if (!tab.equals(originalTab)) {
                getDriver().switchTo().window(tab);
                getDriver().close();
            }
        }
        getDriver().switchTo().window(originalTab);
    }

    // Get browser tab name
    public String getBrowserTabName() {
        String browserTabName = getDriver().getTitle();

        logger.info("Browser tab name is: `{}`", browserTabName);
        return browserTabName;
    }

    // Check an element not exists
    public boolean checkElementNotExists(WebElement element) {
        boolean isNotExists = false;

        try {
            element.click();
            element.getText();
            element.sendKeys("Try to send keys");
        } catch (Exception exception) {
            logger.info("Element `{}` is not exists", element);
            isNotExists = true;
        }

        return isNotExists;
    }

    // Get the current date in the system with the format
    public String getCurrentDate(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date dates = new Date();
        String currentDate = dateFormat.format(dates);

        logger.info("Current date is `{}`", currentDate);
        return currentDate;
    }

    // Convert a date to format (Ex: from format: yyyy-MM-dd to format: MM-dd-yyyy)
    public String convertDateToNewFormat(String date, String oldFormat, String newFormat) {
        String convertedDate = "";
        try {
            SimpleDateFormat originalFormat = new SimpleDateFormat(oldFormat);
            Date parsedDate = originalFormat.parse(date);
            SimpleDateFormat targetFormat = new SimpleDateFormat(newFormat);
            convertedDate = targetFormat.format(parsedDate);
            logger.info("Converted date `{}` to new format `{}`: `{}`", date, newFormat, convertedDate);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
        return convertedDate;
    }

    // Check an element displays in view port
    public boolean isElementDisplays_InViewPort(WebElement element) {
        boolean result = false;

        // Get coordinate X and Y of element
        int xCoordinate = element.getLocation().getX();
        int yCoordinate = element.getLocation().getY();

        // Get height and width of screen window
        int screenHeight = getDriver().manage().window().getSize().getHeight();
        int screenWidth = getDriver().manage().window().getSize().getWidth();

        // Condition for element displays in view port
        if (xCoordinate >= 0 && yCoordinate >= 0 && xCoordinate <= screenWidth && yCoordinate <= screenHeight) {
            result = true;
        }

        return result;
    }

    // Check an element displays inside other element
    public boolean isElementDisplays_InsideOtherElement(WebElement element, WebElement otherElement) {
        boolean result = false;

        // Get coordinate and height/width of element
        int xElementCoordinate = element.getLocation().getX();
        int yElementCoordinate = element.getLocation().getY();
        int elementWidth = xElementCoordinate + element.getSize().getWidth();
        int elementHeight = yElementCoordinate + element.getSize().getHeight();

        // Get coordinate and height/width of other element
        int xOtherCoordinate = otherElement.getLocation().getX();
        int yOtherCoordinate = otherElement.getLocation().getY();
        int otherElementWidth = xOtherCoordinate + otherElement.getSize().getWidth();
        int otherElementHeight = yOtherCoordinate + otherElement.getSize().getHeight();

        boolean xCoordinateBoolean = xOtherCoordinate <= xElementCoordinate;
        boolean widthBoolean = elementWidth <= otherElementWidth;
        boolean yCoordinateBoolean = yOtherCoordinate <= yElementCoordinate;
        boolean heightBoolean = elementHeight <= otherElementHeight;

        if (xCoordinateBoolean && widthBoolean && yCoordinateBoolean && heightBoolean) {
            result = true;
        }

        return result;
    }

}
