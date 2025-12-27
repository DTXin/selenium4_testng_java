package org.example.api;

import org.example.fw_api.BaseTest;
import org.example.Config;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class GetMethodExample extends BaseTest {

    @Test
    @Description("Verify result from response when using then method of Rest-Assured.")
    public void Test01_verifyResult_UseThenMethod() {
        Response response = get_WithParams(Config.API_USER_ENDPOINT,"username", "anhtester");
        response.prettyPrint();

        // Verify result from the response
        response.then().statusCode(200);
        response.then().contentType("application/json");
        response.then().body("response.username", equalTo("anhtester"));
    }

    @Test
    @Description("Verify result from response when using Assert method of TestNG.")
    public void Test02_verifyResponse_UseAssertTestNG() {
        Response response = get("/book/363");
        response.prettyPrint();

        // Verify result from the respont with Assert of TestNG
        Assert.assertEquals(response.getStatusCode(), 200, "Status code it NOT correct");
        Assert.assertEquals(response.getContentType(), "application/json", "Content-Type is NOT correct");

        // Get result from body.
        Assert.assertTrue(response.getBody().asString().contains("Success"), "Message Success is NOT exist");
    }

    @Test
    @Description("Verify body from response when using then method or JsonPath library.")
    public void Test03_verifyBodyResponse_UseJSONPATH() {
        Response response = get("/book/363");
        response.prettyPrint();

        String name = getResponseKeyValue_String(response, "response.name");
        String price = getResponseKeyValue_String(response, "response.price");
        String imagePath2 = getResponseKeyValue_String(response, "response.image[0].path");

        // Using Assert of TestNG to verify
        Assert.assertTrue(name.contains("Tester v1.1.12"), "Name it NOT correct");
        Assert.assertEquals(price, "1230", "Price is NOT correct");
        Assert.assertTrue(imagePath2.contains("public/images/b37wSREdqCSZp"), "Link path is NOT correct");
    }
}
