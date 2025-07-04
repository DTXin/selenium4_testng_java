package org.example.api;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AuthenticationExample {

    @Test
    @Description("Test with Basic Authentication in REST-Assured ")
    public void testBasicAuth() {
        RequestSpecification httpRequest = given().auth().basic("postman", "password");

        Response response = httpRequest.get("https://postman-echo.com/basic-auth");

        System.out.println("Data from the GET API: ");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @Test
    @Description("Test with Direst Authentication in REST-Assured ")
    public void testDigestAuth() {
        RequestSpecification httpRequest = RestAssured.given().auth().digest("postman", "password");

        Response response = httpRequest.get("https://postman-echo.com/basic-auth");

        System.out.println("Data from the GET API: ");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @Test
    public void testOAuth1() {
        given()
                .auth()
                .oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret")
                .get("your end point URL");

    }

    @Test
    public void testOAuth2() {
        given()
                .auth()
                .oauth2("Access token")
                .get("your end point URL");

    }

    @Test
    @Description("Using Token with Cookie in Rest-Assured")
    public void testUseToken_Cookie() {
        RequestSpecification request = given();
        request.baseUri("https://restful-booker.herokuapp.com")
                .contentType("application/json")
                .accept("application/json")
                .header("Cookie", "token=abc123")
                .body("");

        Response response = request.put("/booking/1");

        response.then().statusCode(200);
    }

    @Test
    @Description("Using Token with Bearer in Rest-Assured")
    public void testUseToken_Bearer() {
        String accessToken = "2563|Q4UWVq6jYFFwF2AcWavNaMuKywPQzaszdWC8jQeca5d7ae2b";

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .contentType("application/json")
                .accept("application/json")
                .header("Authorization", "Bearer " + accessToken)
                .body("");

        Response response = request.put("/user/1");
        response.then().statusCode(200);
    }
}
