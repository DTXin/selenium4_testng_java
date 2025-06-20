package org.example.pageObjects.CartPage;

public class CartPageLocators {
    public static final String CHECKOUT_BUTTON = "//button[@id='checkout']";
    public static final String CONTINUE_SHOPPING_BUTTON = "//button[@id='continue-shopping']";
    public static final String REMOVE_BUTTON = ".//*[text()='%s']//ancestor::div[contains(@class,'cart_item')]//button[contains(@id,'remove')]";

    public static final String CART_PAGE = "//span[@class='title' and text()='Your Cart']";
    public static final String CART_ITEM_NAME = ".//div[contains(@class,'cart_item_label')]//div[contains(@class,'inventory_item_name')][text()='%s']";
    public static final String CART_ITEM_COUNT = "//span[@class='shopping_cart_badge']";
    public static final String EMPTY_CART_MESSAGE = "//div[@class='cart_list']/div[@class='cart_item']";
    public static final String TOTAL_PRICE = "//div[@class='summary_subtotal_label']";
}
