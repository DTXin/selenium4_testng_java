package org.example.fw_api;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.example.fw_api.models.Booking;
import org.example.fw_api.models.BookingResponse;
import org.example.fw_api.models.BuilderPOJO;
import org.example.fw_api.models.Authentication;
import org.testng.annotations.BeforeClass;

public class BaseTest extends BaseAPI {
    public static String TOKEN;
    public BookingResponse bookingResponse;
    public Booking booking;
    public Authentication loginPOJO;

    @BeforeClass(description = "Create an authorization token")
    public void createToken() {
        // New object in model
        loginPOJO = BuilderPOJO.getDataLogin();
        booking = BuilderPOJO.getCreateBookingDate();
        bookingResponse = new BookingResponse();

        Gson gson = new Gson();

        Response response = post_NotAuthentication(Config.AUTHEN_ENDPOINT, gson.toJson(loginPOJO));
        verifyStatusCode(response, 200);
        TOKEN = getResponseKeyValue_String(response, Config.TOKEN_KEY);
    }
}
