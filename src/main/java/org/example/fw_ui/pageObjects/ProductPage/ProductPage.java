package org.example.fw_ui.pageObjects.ProductPage;

import io.qameta.allure.Step;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.example.fw_ui.base.BasePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ProductPage extends BasePage {

    /*****************************
     *** Action methods **********
     *****************************/

    @Step("[Product Page] Click on add to cart button of product name: {productName}")
    public void clickOnAddToCartButton(String productName) {
        String elementXpath = String.format(ProductPageLocators.BTN_ADDTOCART, productName);
        WebElement addToCartButton = getElementByXpath(elementXpath);
        clickToElement_ByJavascript(addToCartButton);
    }

    @Step("[Product Page] Click on shopping cart link")
    public void clickOnShoppingCartlink() {
        WebElement cartLink = getElementByXpath(ProductPageLocators.LINK_SHOPPING_CART);
        clickToElement_ByJavascript(cartLink);
    }

    @Step("[Product Page] Select text: {selectText} on sort dropdown")
    public void selectTextOnSortDropdown(String selectText) {
        WebElement dropdown = getElementByXpath(String.format(ProductPageLocators.SORT_DROPDOWN));
        selectTextOnDropdown(dropdown, selectText);
    }

    /*****************************
     *** Verify methods **********
     *****************************/

    @Step("[Product Page] Verify remove button of a product name: {productName} is displayed")
    public void verify_RemoveButton_IsDisplayed(String productName) {
        String elementXpath = String.format(ProductPageLocators.BTN_REMOVE, productName);
        WebElement removeButton = getElementByXpath(elementXpath);

        Assert.assertTrue(removeButton.isDisplayed(), "Remove button is NOT displayed.");
    }

    @Step("[Product Page] Verify product page is displayed.")
    public void verify_ProductPage_IsDisplayed() {
        WebElement app_logo = getElementByXpath(ProductPageLocators.LOGO_XPATH);

        Assert.assertTrue(app_logo.isDisplayed());
    }

    @Step("[Product Page] Verify list product name are order by ascending.")
    public void verify_ListProductName_OrderByAscending() {
        List<WebElement> elementXpath = getElementsByXpath(ProductPageLocators.ITEMS_NAME);
        List<String> listProductName_SortActual = getTextAllElements(elementXpath);

        // From list actual above. We sorted it by ascending
        List<String> listProductName_SortExpceted = listProductName_SortActual.stream()
                .sorted()
                .collect(Collectors.toList());

        // Assert actual and expected
        Assert.assertEquals(listProductName_SortActual, listProductName_SortExpceted);
    }

    @Step("[Product Page] Verify list product name are order by decending.")
    public void verify_ListProductName_OrderByDecending() {
        List<WebElement> elementsXpath = getElementsByXpath(ProductPageLocators.ITEMS_NAME);
        List<String> listProductName_SortActual = getTextAllElements(elementsXpath);

        // From list actual above. We sorted it by decending
        List<String> listProductName_SortExpceted = listProductName_SortActual.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        // Assert actual and expected
        Assert.assertEquals(listProductName_SortActual, listProductName_SortExpceted);
    }
}
