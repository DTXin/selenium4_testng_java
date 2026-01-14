package org.example.ui;

import com.google.gson.JsonObject;
import org.example.Config;
import org.example.fw_ui.base.BaseTest;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {
    public JsonObject readFileJson() {
        return getCommon().ReadJsonWithGson(Config.DATATEST_FOLDER + Config.SHOPPING);
    }

    @Test(description = "Verify remove button is displayed after user click on Add To Cart")
    public void TC01_Verify_RemoveButtonDisplayed_AfterClickOnAddToCart() {
        JsonObject product = readFileJson().getAsJsonObject("product_names");
        JsonObject item = product.getAsJsonObject("item-1");

        getLoginPage().login(USERNAME, PASSWORD);
        getProductPage().verify_ProductPage_IsDisplayed();
        getProductPage().clickOnAddToCartButton(item.get("name").getAsString());
        getProductPage().verify_RemoveButton_IsDisplayed(item.get("name").getAsString());
    }

    @Test(description = "Verify list product name are order by Ascending", groups = {"Regression"} )
    public void TC01_Verify_ListProductName_OrderByAscending() {
        JsonObject sortOptions = readFileJson().getAsJsonObject("sort_options");

        getLoginPage().login(USERNAME, PASSWORD);
        getProductPage().verify_ProductPage_IsDisplayed();
        getProductPage().selectTextOnSortDropdown(sortOptions.get("aToZ").getAsString());
        getProductPage().verify_ListProductName_OrderByAscending();
    }

    @Test(description = "Verify list product name are order by Descending")
    public void TC02_Verify_ListProductName_OrderByDescending() {
        JsonObject sortOptions = readFileJson().getAsJsonObject("sort_options");

        getLoginPage().login(USERNAME, PASSWORD);
        getProductPage().verify_ProductPage_IsDisplayed();
        getProductPage().selectTextOnSortDropdown(sortOptions.get("zToA").getAsString());
        getProductPage().verify_ListProductName_OrderByDescending();
    }

    @Test(description = "Verify list product name are order by Low to High")
    public void TC01_Verify_ListProductName_OrderByLowToHigh() {
        JsonObject sortOptions = readFileJson().getAsJsonObject("sort_options");

        getLoginPage().login(USERNAME, PASSWORD);
        getProductPage().verify_ProductPage_IsDisplayed();
        getProductPage().selectTextOnSortDropdown(sortOptions.get("lowToHigh").getAsString());
        getProductPage().verify_ListProductPrice_OrderByLowToHigh();
    }

    @Test(description = "Verify list product name are order by High to Low")
    public void TC01_Verify_ListProductName_OrderByHighToLow() {
        JsonObject sortOptions = readFileJson().getAsJsonObject("sort_options");

        getLoginPage().login(USERNAME, PASSWORD);
        getProductPage().verify_ProductPage_IsDisplayed();
        getProductPage().selectTextOnSortDropdown(sortOptions.get("highToLow").getAsString());
        getProductPage().verify_ListProductPrice_OrderByHighToLow();
    }
}
