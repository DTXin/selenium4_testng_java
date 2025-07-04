package org.example.ui.otherdemo;

import java.util.Set;

import org.example.fw_ui.base.BaseTest;
import org.example.fw_ui.manager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;

public class Test_Cookies extends BaseTest {

    Set<Cookie> cookies;

    @Test(description = "Login and save cookie")
    public void TC01_LoginAndSaveCookie() throws InterruptedException {
        DriverManager.getDriver().get("https://en.wikipedia.org/");
        DriverManager.getDriver().findElement(By.xpath("//input[@id='wpName1']")).clear();
        DriverManager.getDriver().findElement(By.xpath("//input[@id='wpName1']")).sendKeys("Dinhtranxin");
        ;
        DriverManager.getDriver().findElement(By.xpath("//input[@id='wpPassword1']")).clear();
        DriverManager.getDriver().findElement(By.xpath("//input[@id='wpPassword1']")).sendKeys("8QuxNQhu.2f3smP");
        DriverManager.getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(5000);

        cookies = DriverManager.getDriver().manage().getCookies();

        for (Cookie cookie : cookies) {
            System.out.println("Cookies generated are ==> " + cookie);
        }

        Thread.sleep(10000);
    }

    @Test(description = "Login with cookie")
    public void TC01_LoginWithCookie() throws InterruptedException {
        DriverManager.getDriver().get("https://en.wikipedia.org/");

        for (Cookie cookie : cookies) {
            DriverManager.getDriver().manage().addCookie(cookie);
        }

        DriverManager.getDriver().navigate().refresh();

        Thread.sleep(20000);

    }

    // @Test(description = "Login and save cookie")
    // public void TC01_LoginAndSaveCookie() throws InterruptedException {
    // DriverManager.getDriver().get("https://admin-demo.nopcommerce.com/");
    // DriverManager.getDriver().findElement(By.xpath("//input[@type='email']")).clear();
    // DriverManager.getDriver().findElement(By.xpath("//input[@type='email']")).sendKeys("admin@yourstore.com");
    // ;
    // DriverManager.getDriver().findElement(By.xpath("//input[@type='password']")).clear();
    // DriverManager.getDriver().findElement(By.xpath("//input[@type='password']")).sendKeys("admin");
    // DriverManager.getDriver().findElement(By.xpath("//button[@type='submit']")).click();

    // Thread.sleep(5000);

    // cookies = DriverManager.getDriver().manage().getCookies();

    // for (Cookie cookie : cookies) {
    // System.out.println("Cookies generated are ==> " + cookie);
    // }

    // Thread.sleep(10000);
    // }

    // @Test(description = "Login with cookie")
    // public void TC01_LoginWithCookie() throws InterruptedException {
    // DriverManager.getDriver().get("https://admin-demo.nopcommerce.com/");

    // for (Cookie cookie : cookies) {
    // DriverManager.getDriver().manage().addCookie(cookie);
    // }

    // DriverManager.getDriver().get("https://admin-demo.nopcommerce.com/Admin/Affiliate/List");

    // Thread.sleep(20000);

    // }

    // @Test(description = "Login and save cookie")
    // public void TC01_LoginAndSaveCookie() throws InterruptedException {
    // navigateToUrl();
    // PageManager.getPageManager().getLoginPage().inputTextToUsername("standard_user");
    // PageManager.getPageManager().getLoginPage().inputTextToPassword("secret_sauce");
    // PageManager.getPageManager().getLoginPage().clickOnLoginButton();
    // PageManager.getPageManager().getProductPage().verifyProductPageIsDisplayed();

    // cookies = DriverManager.getDriver().manage().getCookies();

    // for (Cookie cookie : cookies) {
    // System.out.println("Cookies generated are ==> " + cookie);
    // }

    // Thread.sleep(10000);
    // }

    // @Test(description = "Login with cookie")
    // public void TC01_LoginWithCookie() throws InterruptedException {
    // DriverManager.getDriver().get("https://www.saucedemo.com/");

    // for (Cookie cookie : cookies) {
    // DriverManager.getDriver().manage().addCookie(cookie);
    // }

    // DriverManager.getDriver().get("https://www.saucedemo.com/cart.html");

    // Thread.sleep(20000);

    // }
}
