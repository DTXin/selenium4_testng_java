package org.example.pageObjects.ProductPage;

import io.qameta.allure.Step;
import org.example.base.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProductPage extends AbstractPage {
    public WebDriver driver;

    @FindBy(className = "header_secondary_container")
    WebElement header_title;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Verify product page is displayed.")
    public void verifyProductPageIsDisplayed() {
        Assert.assertTrue(header_title.isDisplayed(), "Product page is not displayed.");
    }
}
