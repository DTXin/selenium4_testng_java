package org.example.api;

import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.fw_api.BaseTest;
import org.example.fw_api.Config;
import org.example.fw_api.models.POJOs;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class apiTest extends BaseTest {

    @Test(priority = 1, description = "Create a new booking")
    public void createNewBooking() {
        Gson gson = new Gson();

        Response response = post(Config.BOOKING_ENDPOINT, gson.toJson(booking));
        response.prettyPrint();

        bookingResponse = new POJOs.BookingResponse(getResponseKeyValue_Int(response, "bookingid"), booking);
        verifyStatusCode(response, 200);
    }

    @Test(priority = 2, description = "Get booking with parameter by firstname and lastname")
    public void getBookingIDs_WithFilterByName() {
        Map<String, String> listParams = new HashMap<>();
        listParams.put("firstname", booking.firstname());
        listParams.put("lastname", booking.lastname());

        Response response = get_WithListParams(Config.BOOKING_ENDPOINT, listParams);
        JsonPath jsonPath = response.jsonPath();

        List<Integer> listBookingID = jsonPath.getList("bookingid");
        Assert.assertTrue(!listBookingID.isEmpty(), "Booking is an empty list.");
    }

    @Test(priority = 3, description = "Delete a booking by ID")
    public void deleteBooking() {
        int ID = bookingResponse.bookingID();

        Response response = delete(Config.BOOKING_ENDPOINT + "/" + ID);
        response.prettyPrint();

        verifyStatusCode(response, 201);
    }
}
