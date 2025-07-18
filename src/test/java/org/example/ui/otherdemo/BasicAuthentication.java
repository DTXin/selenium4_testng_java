package org.example.ui.otherdemo;

import org.example.fw_ui.base.BaseTest;
import org.example.fw_ui.manager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicAuthentication extends BaseTest {

    @Test(description = "Verify basic authentication")
    public void TC01_Login_With_BasicAuthentication() throws Exception {

        ((HasAuthentication) getDriver()).register(() -> new UsernameAndPassword("guest", "guest"));

        getDriver().get("https://jigsaw.w3.org/HTTP/Basic/");
        WebElement body = getDriver().findElement(By.tagName("body"));
        Assert.assertTrue(body.getText().contains("Your browser made it!"));
    }

}
