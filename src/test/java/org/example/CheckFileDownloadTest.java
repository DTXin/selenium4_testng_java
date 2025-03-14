package org.example;

import org.example.base.BaseTest;
import org.example.base.DriverManager;
import org.example.base.PageManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckFileDownloadTest extends BaseTest {

    @Test(description = "Verify file download successful")
    public void TC01_CheckFileDownloaded() throws InterruptedException {
        navigateToUrl();

        WebElement downloadBtn = DriverManager.getDriver()
                .findElement(By.xpath("//button[text()='Download File']//parent::a"));

        String url = downloadBtn.getDomAttribute("href");

        // String url =
        // "https://download-cdn.jetbrains.com/idea/ideaIU-2024.3.4.1.win.zip";

        PageManager.getPageManager().getFileHelper().downloadFile(url);
        Assert.assertTrue(PageManager.getPageManager().getFileHelper().checkFileDownload("LambdaTest.pdf"));
    }
}
