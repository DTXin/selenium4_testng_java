package org.example.ui;

import com.google.gson.JsonObject;
import org.example.Config;
import org.example.fw_ui.base.BaseTest;
import org.testng.annotations.Test;

public class E2ETest extends BaseTest {

    public JsonObject readFileJson() {
        return getCommon().ReadJsonWithGson(Config.DATATEST_FOLDER + Config.SHOPPING);
    }

    @Test(description = "Verify user process successfully an order.")
    public void T01_Verify_UserProcessSuccessfully_AnOrder() {
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
        getCheckoutPage().verify_Quantity_OfProductName(item.get("name").getAsString(), item.get("quantity").getAsInt());
        getCheckoutPage().verify_Price_OfProductName(item.get("name").getAsString(), item.get("price").getAsString());
        getCheckoutPage().clickOnFinishButton();
        getCheckoutPage().verify_HeaderOfCheckoutCompletePage_IsDisplayed();
        getCheckoutPage().verify_ThankYouMessage_IsDisplayed();
    }
}
