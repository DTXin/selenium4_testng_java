package org.example.fw_api;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.example.fw_api.models.POJOs.Booking;
import org.example.fw_api.models.POJOs.BookingResponse;
import org.example.fw_api.models.BuilderPOJO;
import org.example.fw_api.models.POJOs.Authentication;
import org.testng.annotations.BeforeClass;

public class BaseTest extends BaseAPI {
    public static String TOKEN;
    public BookingResponse bookingResponse;
    public Booking booking;
    public Authentication loginPOJO;

    @BeforeClass(description = "Create an authorization token")
    public void createToken() {
        loginPOJO = BuilderPOJO.getDataLogin();
        booking = BuilderPOJO.getCreateBookingDate();

        Gson gson = new Gson();

        Response response = post_NotAuthentication(Config.AUTHEN_ENDPOINT, gson.toJson(loginPOJO));
        verifyStatusCode(response, 200);
        TOKEN = getResponseKeyValue_String(response, Config.TOKEN_KEY);
    }
}
