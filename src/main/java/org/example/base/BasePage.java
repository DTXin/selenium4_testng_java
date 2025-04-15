package org.example.base;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public abstract class BasePage {
    protected static final Logger logger = LogManager.getLogger();

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
    public void clickToElement_ByJavaScript(WebElement element) {
        scrollToElement(element);

        logger.info("Click to element (by javascript): `{}`", element);
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();", element);
    }

    // Get text of element by javascript
    public String getText_ByJavaScript(WebElement element) {
        logger.info("Get text of an element (by javascript): `{}`", element);

        String textOfElement = (String) ((JavascriptExecutor) DriverManager.getDriver())
                .executeScript("return arguments[0].value;", element);

        logger.info("Text of element is: `{}`", textOfElement);
        return textOfElement;
    }

    /********************************************************************
     ** Start Blocks: Scroll Page (Ex: Scroll top, bottom...) ***********
     *******************************************************************/

    // Scroll into view of the browser window
    public void scrollToElement(WebElement element) {
        logger.info("Scroll to element into view...: `{}`", element);
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript(
                "arguments[0].scrollIntoView({block: \"center\",inline: \"center\",behavior: \"smooth\"});", element);
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

    // Check scroll bar is displayed
    public boolean checkScrollBar_IsDisplayed(WebElement element, String scrollBarType) {
        boolean result = false;

        String scrollBar_Horizontal = "return arguments[0].scrollHeight > arguments[0].offsetWidth;";
        String scrollBar_Vertical = "return arguments[0].scrollHeight > arguments[0].offsetHeight;";
        JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getDriver();

        if (scrollBarType.equalsIgnoreCase("Horizontal")) {
            result = (boolean) jse.executeScript(scrollBar_Horizontal, element);
        } else if (scrollBarType.equalsIgnoreCase("Vertical")) {
            result = (boolean) jse.executeScript(scrollBar_Vertical, element);
        }

        logger.info("Result of scroll bar `{}` is displayed: `{}`", scrollBarType, result);
        return result;
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
            webElement = DriverManager.getDriver().findElement(By.xpath(xpath));
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
            listElement = DriverManager.getDriver().findElements(By.xpath(xpath));
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
            webElement = DriverManager.getDriver().findElement(By.cssSelector(css));
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
            listElement = DriverManager.getDriver().findElements(By.cssSelector(css));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        return listElement;
    }

    /***************************************************************************
     ** Start Blocks: Waiting element (Ex: wait for clickable, for visible....)*
     **************************************************************************/

    // Wait element for visible
    public void waitElementForVisible(WebElement element) {
        logger.info("+++ Wait element `{}` for visible +++", element);
        DriverManager.getDriverWait().until(ExpectedConditions.visibilityOf(element));
    }

    // Wait element for clickable
    public void waitElementForClickable(WebElement element) {
        logger.info("+++ Wait element `{}` for clickable +++", element);
        DriverManager.getDriverWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    // Wait element for invisible
    public void waitElementForInvisible(WebElement element) {
        logger.info("+++ Wait element `{}` for invisible +++", element);
        DriverManager.getDriverWait().until(ExpectedConditions.invisibilityOf(element));
    }

    // Wait until text present
    public void waitUtilTextPresent(WebElement element, String expectedText) {
        logger.info("+++ Wait until text `{}` for present +++", expectedText);
        DriverManager.getDriverWait().until(ExpectedConditions.textToBePresentInElement(element, expectedText));
    }

    /***************************************
     ** Start Blocks: All for popup.... ****
     **************************************/

    /********************************
     ** Start Blocks: Other.... *****
     *******************************/

    // Get current URL of page
    public String getCurrentURL() {
        String url = DriverManager.getDriver().getCurrentUrl();

        logger.info("The current URL: " + url);
        return url;
    }

    // Close all tabs and except the first tab
    public void closeAllTabsExceptFirstTab() {
        logger.info("Close all tabs and except the first one...");

        String originalTab = DriverManager.getDriver().getWindowHandle();
        for (String tab : DriverManager.getDriver().getWindowHandles()) {
            if (!tab.equals(originalTab)) {
                DriverManager.getDriver().switchTo().window(tab);
                DriverManager.getDriver().close();
            }
        }
        DriverManager.getDriver().switchTo().window(originalTab);
    }

    // Get browser tab name
    public String getBrowserTabName() {
        String browserTabName = DriverManager.getDriver().getTitle();

        logger.info("Browser tab name is: `{}`", browserTabName);
        return browserTabName;
    }

    // Check a element not exists
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

    // Check a element displays in view port
    public boolean isElemntDisplays_InViewPort(WebElement element) {
        boolean result = false;

        // Get coordinate X and Y of element
        int xCoordinate = element.getLocation().getX();
        int yCoordinate = element.getLocation().getY();

        // Get height and width of screen window
        int screenHeight = DriverManager.getDriver().manage().window().getSize().getHeight();
        int screenWidth = DriverManager.getDriver().manage().window().getSize().getWidth();

        // Condition for element displays in view port
        if (xCoordinate >= 0 && yCoordinate >= 0 && xCoordinate <= screenWidth && yCoordinate <= screenHeight) {
            result = true;
        }

        return result;
    }

    // Check a element displays inside other element
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
