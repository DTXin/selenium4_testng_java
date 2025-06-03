package org.example;

import java.io.File;

import org.example.fw.base.BaseTest;
import org.example.utils.FileHelper;
import org.testng.annotations.Test;
import org.testng.Assert;

public class TestFileHelper extends BaseTest {

    private static final String downloadPath = System.getProperty("user.dir") + "\\src\\test\\resources\\download\\";

    @Test
    public void testUnzipFile() {
        FileHelper fileHelper = new FileHelper();

        String testZipFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\download\\allure-2.30.0.zip";
        String testFileName = "allure-2.30.0.zip";

        // Call the unzipFile method
        fileHelper.unZip(testZipFilePath, downloadPath);

        // Verify that the file was extracted
        File extractedFile = new File(downloadPath + testFileName);
        Assert.assertTrue(extractedFile.exists(), "Extracted file does not exist.");
        Assert.assertTrue(extractedFile.isFile(), "Extracted file is not a valid file.");
    }

    @Test
    public void testDeleteFile() {
        FileHelper fileHelper = new FileHelper();

        String testFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\download\\";
        String testFileName = "accounts.rar";

        fileHelper.deleteFile(testFileName, testFilePath);

        // Verify that the file was deleted
        File extractedFile = new File(downloadPath + testFileName);
        Assert.assertFalse(extractedFile.exists(), "Extracted file does exist.");
    }
}
