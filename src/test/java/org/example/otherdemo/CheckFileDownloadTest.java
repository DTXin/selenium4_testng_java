package org.example.otherdemo;

import org.example.fw.base.BaseTest;
// import org.example.fw.manager.DriverManager;
// import org.example.fw.manager.PageManager;
// import org.openqa.selenium.By;
// import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckFileDownloadTest extends BaseTest {

    private static final String downloadFolderPath = System.getProperty("user.dir") + "/src/test/resources/download/";

    @Test(description = "Verify file download successful")
    public void TC01_CheckFileDownloaded() throws InterruptedException {
        // navigateToUrl();

        // WebElement downloadBtn = DriverManager.getDriver()
        // .findElement(By.xpath("//button[text()='Download File']//parent::a"));

        // String url = downloadBtn.getDomAttribute("href");

        String url = "https://download-cdn.jetbrains.com/idea/ideaIU-2024.3.4.1.win.zip";

        getFileHelper().downloadFile(url, downloadFolderPath);
        Assert.assertTrue(getFileHelper().checkFileExists("LambdaTest.pdf", downloadFolderPath));
    }
}
