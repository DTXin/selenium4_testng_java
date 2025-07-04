package org.example.fw_api.models;

// import net.datafaker.Faker;
import org.example.fw_api.Config;

public class BuilderPOJO {

    public static Authentication getDataLogin() {
        return new Authentication(Config.USERNAME, Config.PASSWORD);
    }

    public static Booking getCreateBookingDate() {
        // Faker faker = new Faker();

        Booking bookingPOJO = new Booking();
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2025-07-01");
        bookingDates.setCheckout("2026-07-01");

        bookingPOJO.setFirstname("Xin");
        bookingPOJO.setLastname("Dinh");
        bookingPOJO.setTotalprice(150);
        bookingPOJO.setDepositpaid(true);
        bookingPOJO.setBookingDates(bookingDates);
        bookingPOJO.setAdditionalneeds("Breakfast");

        return bookingPOJO;
    }
}
