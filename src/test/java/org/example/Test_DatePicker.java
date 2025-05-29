package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.testng.Assert;
import org.example.base.BaseTest;
import org.example.base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.Test;

public class Test_DatePicker extends BaseTest {

        @Test(description = "Test date picker")
        public void TC01_DatePicker() throws InterruptedException {
                // navigateToUrl();

                // Get the current date from the system clock
                LocalDate today = LocalDate.now();
                int currentYear = today.getYear();
                int currentDay = today.getDayOfMonth();

                // Click on the date picker to open the calendar
                WebElement datePicker = DriverManager.getDriver().findElement(By.name("my-date"));
                datePicker.click();

                // Click on the current month by searching by text
                WebElement monthElement = DriverManager.getDriver()
                                .findElement(By.xpath(String.format("//th[contains(text(),'%d')]", currentYear)));
                monthElement.click();

                // Click on the left arrow using relative locators
                WebElement arrowLeft = DriverManager.getDriver().findElement(
                                RelativeLocator.with(By.tagName("th")).toRightOf(monthElement));
                // arrowLeft.click();

                // Click on the current month of that year
                WebElement monthPastYear = DriverManager.getDriver().findElement(
                                RelativeLocator.with(By.cssSelector("span[class$='month focused']")).below(arrowLeft));
                monthPastYear.click();

                // Click on the present day in that month
                WebElement dayElement = DriverManager.getDriver()
                                .findElement(By.xpath(String.format("//td[@class='day' and contains(text(),'%d')]",
                                                currentDay)));
                dayElement.click();

                String oneYearBack = datePicker.getDomProperty("value");
                System.out.println("Date selected - DomProperty: " + oneYearBack);

                Thread.sleep(20000);

                // Assert that the expected date is equal to the one selected in the date picker
                LocalDate previousYear = today.minusYears(0);
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                String expectedDate = previousYear.format(dateFormat);
                System.out.println("Expected date: " + expectedDate);
                Assert.assertTrue(oneYearBack.equals(expectedDate),
                                "The date selected is not equal to the expected date");
        }
}
