package org.example.fw_api.models;

public class POJOs {
    public record Authentication(String username, String password) {};
    public record Booking (String firstname, String lastname, int totalprice, boolean depositpaid, BookingDates bookingdates, String additionalneeds) {};
    public record BookingDates(String checkin, String checkout) {};
    public record BookingResponse(int bookingID, Booking booking){};
}
