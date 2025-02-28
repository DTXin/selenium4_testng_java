package org.example.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.base.DriverManager;
// import org.example.base.ExtentReportManager;
import org.testng.Assert;

// import com.aventstack.extentreports.Status;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BingMainPage {
    protected static final Logger logger = LogManager.getLogger();

    public WebDriver driver;

    public JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

    @FindBy(id = "sb_form_q")
    WebElement searchBox;

    @FindBy(xpath = "//label[@for='sb_form_go']")
    WebElement goButton;

    @FindBy(id = "b_tween")
    WebElement resultsCountDiv;

    public BingMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public void navigateTo(String url) {
        logger.info("=== Logger: Navigate to website `{}` ===", url);
        // ExtentReportManager.getExtentTest().log(Status.INFO, "Navigate to website: "
        // + url);
        DriverManager.getDriver().navigate().to(url);
    }

    public void search(String textToType) {
        logger.info("=== Logger: Search with texttext `{}` of browser `{}`===", textToType,
                DriverManager.getDriver().toString());
        // ExtentReportManager.getExtentTest().log(Status.INFO, "Search with texttext: "
        // + textToType);
        searchBox.clear();
        // searchBox.sendKeys(textToType);
        js.executeScript("document.getElementById('sb_form_q').setAttribute('value','123456');");
        goButton.click();
    }

    public void resultsCount(String expectedCount) {
        Assert.assertTrue(resultsCountDiv.getText().contains(expectedCount),
                "The results DIV doesn't contain the specified text.");
    }
}
