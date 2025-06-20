package org.example.pageObjects.ProductPage;

import io.qameta.allure.Step;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.example.fw.base.BasePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ProductPage extends BasePage {

    @Step("Verify product page is displayed.")
    public void verifyProductPageIsDisplayed() {
        WebElement app_logo = getElementByXpath(ProductPageLocators.LOGO_XPATH);

        logger.info("Verify product page is displayed = `{}`", app_logo.isDisplayed());
        Assert.assertTrue(app_logo.isDisplayed(), "Product page is NOT displayed.");
    }

    @Step("Click con add to cart button")
    public void clickOnAddToCartButton(String productName) {
        String elementXpath = String.format(ProductPageLocators.ADDTOCART_BUTTON, productName);
        WebElement addToCartButton = getElementByXpath(elementXpath);

        logger.info("Click on add to cart button of product name {}", productName);
        addToCartButton.click();
    }

    @Step("Click on shopping cart link")
    public void clickOnShoppingCartlink() {
        logger.info("User click on shopping cart link.");

        WebElement cartLink = getElementByXpath(ProductPageLocators.SHOPPING_CART_LINK);
        cartLink.click();
    }

    @Step("Verify remove button of a product name: {productName} is displayed.")
    public void verifyRemoveButtonIsDisplayed(String productName) {
        String elementXpath = String.format(ProductPageLocators.REMOVE_BUTTON, productName);
        WebElement remoteButton = getElementByXpath(elementXpath);

        logger.info("Verify remove button of a product name {} is displayed.", productName);
        Assert.assertTrue(remoteButton.isDisplayed(), "Remove button is NOT displayed.");
    }

    @Step("Select text on sort dropdown with value: {selectText}")
    public void selectTextOnSortDropdown(String selectText) {
        WebElement dropdown = getElementByXpath(String.format(ProductPageLocators.SORT_DROPDOWN));
        selectTextOnDropdown(dropdown, selectText);
    }

    @Step("Verify list product name is order by ascending.")
    public void verifyListProductName_IsOrderByAscending() {
        logger.info("Verify list product name is order by ascending.");

        // Get list product name
        List<WebElement> elementXpath = getElementsByXpath(ProductPageLocators.ITEMS_NAME);

        // Get text of list product name
        List<String> listItemsName_SortActual = getTextAllElements(elementXpath);

        // From list actual above. We sorted it by ascending
        List<String> listItemsName_SortExpceted = listItemsName_SortActual.stream().sorted()
                .collect(Collectors.toList());

        // Assert actual and expected
        Assert.assertEquals(listItemsName_SortActual, listItemsName_SortExpceted);
    }

    @Step("Verify list product name is order by decending.")
    public void verifyListProductName_IsOrderByDecending() {
        logger.info("Verify list product name is order by decending.");

        // Get list product name
        List<WebElement> elementXpath = getElementsByXpath(ProductPageLocators.ITEMS_NAME);

        // Get text of list product name
        List<String> listItemsName_SortActual = getTextAllElements(elementXpath);

        // From list actual above. We sorted it by ascending
        List<String> listItemsName_SortExpceted = listItemsName_SortActual.stream().sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        // Assert actual and expected
        Assert.assertEquals(listItemsName_SortActual, listItemsName_SortExpceted);
    }
}
