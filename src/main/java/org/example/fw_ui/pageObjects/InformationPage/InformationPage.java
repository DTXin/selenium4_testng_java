package org.example.fw_ui.pageObjects.InformationPage;

import org.example.fw_ui.base.BasePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.qameta.allure.Step;

public class InformationPage extends BasePage {

    /*****************************
     *** Action methods **********
     *****************************/

    @Step("[Info Page] Input text to firstname field")
    public void inputTextToFirstName(String firstname) {
        WebElement element = getElementByCss(InformationPageLocator.TXT_FIRSTNAME_CSS);
        element.sendKeys(firstname);
    }

    @Step("[Info Page] Input text to lastname field")
    public void inputTextToLastName(String lastname) {
        WebElement element = getElementByCss(InformationPageLocator.TXT_LASTNAME_CSS);
        element.sendKeys(lastname);
    }

    @Step("[Info Page] Input text to zip code field")
    public void inputTextToZipCode(String zipcode) {
        WebElement element = getElementByCss(InformationPageLocator.TXT_ZIPCODE_CSS);
        inputText_ByJavascript(element, zipcode);
    }

    @Step("[Info Page] Click on continue button")
    public void clickOnContinueButton() {
        WebElement element = getElementByCss(InformationPageLocator.BTN_CONTINUE_CSS);
        clickToElement_ByJavascript(element);
    }

    /*****************************
     *** Verify methods **********
     *****************************/

    @Step("Verify header of Information Page is displayed")
    public void verify_HeaderOfInformationPage_IsDisplayed() {
        WebElement element = getElementByCss(InformationPageLocator.HEADER_PAGE_CSS);

        Assert.assertTrue(element.isDisplayed(), "Header of Information Page is NOT displayed");
    }

    @Step("Verify message: {expectedMessage} is displayed")
    public void verify_ContentOfErrorMessage(String expectedMessage) {
        WebElement element = getElementByXpath(InformationPageLocator.ERROR_MESSAGE_CONTAINER);

        Assert.assertEquals(element.getText(), expectedMessage);
    }
}
