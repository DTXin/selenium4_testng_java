package org.example.fw_ui.pageObjects.CheckoutPage;

public class CheckoutPageLocators {

    public static final String BTN_FINISH_CSS = "button#finish";
    public static final String BTN_CANCEL_CSS = "button#cancel";
    public static final String BTN_BACKTOHOME_CSS = "button#back-to-products";

    public static final String HEADER_OVERVIEW_PAGE_XPATH = "//div[contains(@class,'header_secondary_container')]//*[contains(text(),'Checkout: Overview')]";
    public static final String HEADER_CHECKOUT_PAGE_XPATH = "//div[contains(@class,'header_secondary_container')]//*[contains(text(),'Checkout: Complete!')]";

    public static final String LIST_ITEM_IN_CART = "//div[contains(@class,'cart_list')]//div[@class='cart_item']";
    public static final String ITEM_IN_CART = LIST_ITEM_IN_CART + "//*[@class='inventory_item_name' and text()='%s']";
    public static final String QUANTITY_OF_A_ITEM = "//*[@class='inventory_item_name' and text()='%s']/ancestor::div[@class='cart_item']//div[@class='cart_quantity']";
    public static final String PRICE_OF_A_ITEM = "//*[@class='inventory_item_name' and text()='%s']/ancestor::div[@class='cart_item']//div[@class='inventory_item_price']";

    public static final String THANK_YOU_MESSAGE = "//h2[@class='complete-header' and contains(text(),'Thank you for your order')]";
}
