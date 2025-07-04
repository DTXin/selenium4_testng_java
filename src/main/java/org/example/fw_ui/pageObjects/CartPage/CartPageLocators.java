package org.example.fw_ui.pageObjects.CartPage;

public class CartPageLocators {
    public static final String BTN_CHECKOUT_XPATH = "//button[@id='checkout']";
    public static final String BTN_CONTINUE_SHOPPING_XPATH = "//button[@id='continue-shopping']";
    public static final String BTN_REMOVE_XPATH = ".//*[text()='%s']//ancestor::div[contains(@class,'cart_item')]//button[contains(@id,'remove')]";

    public static final String HEADER_TITLE_CARTPAGE = "//span[@class='title' and text()='Your Cart']";
    public static final String ITEM_NAME = ".//div[contains(@class,'cart_item_label')]//div[contains(@class,'inventory_item_name')][text()='%s']";
    public static final String ITEM_COUNT = "//span[@class='shopping_cart_badge']";
    public static final String EMPTY_CART_MESSAGE = "//div[@class='cart_list']/div[@class='cart_item']";
    public static final String TOTAL_PRICE = "//div[@class='summary_subtotal_label']";
}
