package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.example.base.BaseTest;
import org.example.base.DriverManager;
import org.openqa.selenium.Pdf;
import org.openqa.selenium.PrintsPage;
import org.openqa.selenium.print.PrintOptions;

public class Test_PrintPageToPDF extends BaseTest {

    @Test(description = "Print page to PDF")
    public void TC01_PrintPageToPDF() throws IOException {

        DriverManager.getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/");
        PrintsPage pg = (PrintsPage) DriverManager.getDriver();
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
