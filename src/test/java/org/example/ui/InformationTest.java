package org.example.ui;

import com.google.gson.JsonObject;
import org.example.Config;
import org.example.fw_ui.base.BaseTest;
import org.testng.annotations.Test;

public class InformationTest extends BaseTest {

    public JsonObject readFileJson() {
        return getCommon().ReadJsonWithGson(Config.DATATEST_FOLDER + Config.SHOPPING);
    }

    @Test(description = "Verify error message show when user not input first name field")
    public void T01_Verify_ErrorIsDisplayed_WithoutFirstName() {
        JsonObject product = readFileJson().getAsJsonObject("product_names");
        JsonObject item = product.getAsJsonObject("item-1");

        getLoginPage().login(USERNAME, PASSWORD);
        getProductPage().verify_ProductPage_IsDisplayed();
        getProductPage().clickOnAddToCartButton(item.get("name").getAsString());
        getProductPage().clickOnShoppingCartButton();
        getCartPage().verify_HeaderOfCartPage_IsDisplayed();
        getCartPage().clickOnCheckoutButton();
        getInformationPage().verify_HeaderOfInformationPage_IsDisplayed();
        getInformationPage().inputTextToLastName("Dinh Tran");
        getInformationPage().clickOnContinueButton();
        getInformationPage().verify_ContentOfErrorMessage("Error: First Name is required");
    }

    @Test(description = "Verify error message show when user not input last name field")
    public void T02_Verify_ErrorIsDisplayed_WithoutLastName() {
        JsonObject product = readFileJson().getAsJsonObject("product_names");
        JsonObject item = product.getAsJsonObject("item-1");

        getLoginPage().login(USERNAME, PASSWORD);
        getProductPage().verify_ProductPage_IsDisplayed();
        getProductPage().clickOnAddToCartButton(item.get("name").getAsString());
        getProductPage().clickOnShoppingCartButton();
        getCartPage().verify_HeaderOfCartPage_IsDisplayed();
        getCartPage().clickOnCheckoutButton();
        getInformationPage().verify_HeaderOfInformationPage_IsDisplayed();
        getInformationPage().inputTextToFirstName("Dinh Tran");
        getInformationPage().clickOnContinueButton();
        getInformationPage().verify_ContentOfErrorMessage("Error: Last Name is required");
    }

    @Test(description = "Verify error message show when user not input zip/postal code field")
    public void T03_Verify_ErrorIsDisplayed_WithoutZipcode() {
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
        getInformationPage().clickOnContinueButton();
        getInformationPage().verify_ContentOfErrorMessage("Error: Postal Code is required");
    }
}
