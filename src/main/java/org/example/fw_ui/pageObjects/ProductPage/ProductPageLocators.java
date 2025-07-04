package org.example.fw_ui.pageObjects.ProductPage;

public class ProductPageLocators {
    public static final String LOGO_XPATH = "//div[@Class='app_logo']";

    public static final String BTN_ADDTOCART = "//*[text()='%s']//ancestor::div[contains(@class,'inventory_item_description')]//button[contains(@id,'add-to-cart')]";
    public static final String BTN_REMOVE = "//*[text()='%s']//ancestor::div[contains(@class,'inventory_item_description')]//button[contains(@id,'remove')]";

    public static final String LINK_SHOPPING_CART = "//a[contains(@class,'shopping_cart_link')]";

    public static final String SORT_DROPDOWN = "//select[contains(@class,'product_sort_container')]";

    public static final String ITEMS_NAME = "//div[contains(@class,'inventory_item_name ')]";
}
