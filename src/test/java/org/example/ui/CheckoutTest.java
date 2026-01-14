package org.example.ui;

import com.google.gson.JsonObject;
import org.example.Config;
import org.example.fw_ui.base.BaseTest;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    public JsonObject readFileJson() {
        return getCommon().ReadJsonWithGson(Config.DATATEST_FOLDER + Config.SHOPPING);
    }

    @Test(description = "Verify number product displayed in cart list at checkout overview page")
    public void T01_Verify_NumberProduct_Displayed() {
        JsonObject product = readFileJson().getAsJsonObject("product_names");
        JsonObject item = product.getAsJsonObject("item-1");

        getLoginPage().login(USERNAME, PASSWORD);
        getProductPage().verify_ProductPage_IsDisplayed();
        getProductPage().clickOnAddToCartButton(item.get("name").getAsString());
        getProductPage().clickOnShoppingCartButton();
        getCartPage().verify_HeaderOfCartPage_IsDisplayed();
        getCartPage().clickOnCheckoutButton();
        getInformationPage().verify_HeaderOfInformationPage_IsDisplayed();
        getInformationPage().inputTextToFirstName("Dinh");
        getInformationPage().inputTextToLastName("Tran");
        getInformationPage().inputTextToZipCode("00084");
        getInformationPage().clickOnContinueButton();
        getCheckoutPage().verify_HeaderOfInformationPage_IsDisplayed();
        getCheckoutPage().verify_Number_Product_InCartList(1);
    }

    @Test(description = "Verify quantity of a product name in cart list at checkout overview page")
    public void T02_Verify_QuantityOfProductName() {
        JsonObject product = readFileJson().getAsJsonObject("product_names");
        JsonObject item = product.getAsJsonObject("item-1");

        getLoginPage().login(USERNAME, PASSWORD);
        getProductPage().verify_ProductPage_IsDisplayed();
        getProductPage().clickOnAddToCartButton(item.get("name").getAsString());
        getProductPage().clickOnShoppingCartButton();
        getCartPage().verify_HeaderOfCartPage_IsDisplayed();
        getCartPage().clickOnCheckoutButton();
        getInformationPage().verify_HeaderOfInformationPage_IsDisplayed();
        getInformationPage().inputTextToFirstName("Dinh");
        getInformationPage().inputTextToLastName("Tran");
        getInformationPage().inputTextToZipCode("00084");
        getInformationPage().clickOnContinueButton();
        getCheckoutPage().verify_HeaderOfInformationPage_IsDisplayed();
        getCheckoutPage().verify_Quantity_OfProductName(item.get("name").getAsString(), item.get("quantity").getAsInt());
    }

    @Test(description = "Verify price of a product name in cart list at checkout overview page")
    public void T03_Verify_PriceOfProductName() {
        JsonObject product = readFileJson().getAsJsonObject("product_names");
        JsonObject item = product.getAsJsonObject("item-1");

        getLoginPage().login(USERNAME, PASSWORD);
        getProductPage().verify_ProductPage_IsDisplayed();
        getProductPage().clickOnAddToCartButton(item.get("name").getAsString());
        getProductPage().clickOnShoppingCartButton();
        getCartPage().verify_HeaderOfCartPage_IsDisplayed();
        getCartPage().clickOnCheckoutButton();
        getInformationPage().verify_HeaderOfInformationPage_IsDisplayed();
        getInformationPage().inputTextToFirstName("Dinh");
        getInformationPage().inputTextToLastName("Tran");
        getInformationPage().inputTextToZipCode("00084");
        getInformationPage().clickOnContinueButton();
        getCheckoutPage().verify_HeaderOfInformationPage_IsDisplayed();
        getCheckoutPage().verify_Price_OfProductName(item.get("name").getAsString(), item.get("price").getAsString());
    }
}
