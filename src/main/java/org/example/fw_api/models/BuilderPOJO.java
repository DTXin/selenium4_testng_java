package org.example.fw_api.models;

import org.example.fw_api.Config;

public class BuilderPOJO {

    public static POJOs.Authentication getDataLogin() {
        return new POJOs.Authentication(Config.USERNAME, Config.PASSWORD);
    }

    public static POJOs.Booking getCreateBookingDate() {
        String firstName = "Xin";
        String lastName = "Dinh";
        int totalPrice = 150;
        boolean depositPaid = true;
        String additionalneeds = "Breakfast";

        POJOs.BookingDates bookingDates = new POJOs.BookingDates("2025-07-01", "2026-07-01");

        return new POJOs.Booking(firstName, lastName, totalPrice, depositPaid, bookingDates, additionalneeds);
    }
}
