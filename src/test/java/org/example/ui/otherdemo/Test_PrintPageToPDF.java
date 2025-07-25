package org.example.ui.otherdemo;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.example.fw_ui.base.BaseTest;
import org.example.fw_ui.manager.DriverManager;
import org.openqa.selenium.Pdf;
import org.openqa.selenium.PrintsPage;
import org.openqa.selenium.print.PrintOptions;

public class Test_PrintPageToPDF extends BaseTest {

    @Test(description = "Print page to PDF")
    public void TC01_PrintPageToPDF() throws IOException {

        getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/");
        PrintsPage pg = (PrintsPage) getDriver();
        PrintOptions printOptions = new PrintOptions();
        Pdf pdf = pg.print(printOptions);
        String pdfBase64 = pdf.getContent();
        Assert.assertTrue((pdfBase64).contains("JVBER"));

        byte[] decodedImg = Base64.getDecoder()
                .decode(pdfBase64.getBytes(StandardCharsets.UTF_8));
        Path destinationFile = Paths.get("D:\\my-pdf.pdf");
        Files.write(destinationFile, decodedImg);

    }
}
