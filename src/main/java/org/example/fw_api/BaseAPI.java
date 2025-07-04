package org.example.fw_api;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseAPI {
    protected static final Logger logger = LogManager.getLogger();

    @Step("User send a GET request")
    public static Response get(String path) {
        logger.info("GET: {}", path);
        Response response = given(SpecBuilder.getRequestSpecBuilder())
                .log().all()
                .when()
                .get(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract().response();

        logger.info("Response of get request: \n {}", response.prettyPrint());
        return response;
    }

    @Step("User send a GET request with headers")
    public static Response get(String path, Map<String, String> headers) {
        logger.info("GET: {} with headers {}", path, headers);
        Response response = given(SpecBuilder.getRequestSpecBuilder().headers(headers))
                .log().all()
                .when()
                .get(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract().response();

        logger.info("Response of get request with headers: \n {}", response.prettyPrint());
        return response;
    }

    @Step("User send a GET request with a parameter")
    public static Response get_WithParams(String path, String paramKey, String paramValue) {
        logger.info("GET not authentication: {} with a parameters: key={} and value={}", path, paramKey, paramValue);
        Response response = given(SpecBuilder.getRequestSpecBuilder_WithParam(paramKey, paramValue))
                .log().all()
                .when()
                .get(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract().response();

        logger.info("Response of get request with a parameter: \n {}", response.prettyPrint());
        return response;
    }

    @Step("User send a GET request with list parameters")
    public static Response get_WithListParams(String path, Map<String, String> params) {
        logger.info("GET: {} with a list parameters: {}", path, params);
        Response response = given(SpecBuilder.getRequestSpecBuilder_WithListParams(params))
                .log().all()
                .when()
                .get(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract().response();

        logger.info("Response of get request with list parameters: \n {}", response.prettyPrint());
        return response;
    }

    @Step("User send a POST request (has authentication) with body")
    public static Response post(String path, Object payLoad) {
        logger.info("POST: {} with body: {}", path, payLoad);
        Response response = given(SpecBuilder.getRequestSpecBuilder_HasAuthentication())
                .log().all()
                .body(payLoad)
                .when()
                .post(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract().response();

        logger.info("Response of post request with body: \n {}", response.prettyPrint());
        return response;
    }

    @Step("User send a POST request (has authentication) with body is a file json")
    public static Response post_WithFileJson(String path, String filePathBody) {
        logger.info("POST: {} with body is a file json: {}", path, filePathBody);
        Response response = given(SpecBuilder.getRequestSpecBuilder_HasAuthentication())
                .log().all()
                .body(new File(filePathBody))
                .when()
                .post(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract().response();

        logger.info("Response of post request with body is a file json: \n {}", response.prettyPrint());
        return response;
    }

    @Step("User send a POST request (not authentication) with body")
    public static Response post_NotAuthentication(String path, Object payLoad) {
        logger.info("POST (not authentication): {} with body: {}", path, payLoad);
        Response response = given(SpecBuilder.getRequestSpecBuilder_HasAuthentication())
                .log().all()
                .body(payLoad)
                .when()
                .post(path)
                .then()
                .spec(SpecBuilder.getResponseSpecBuilder())
                .extract().response();

        logger.info("Response of post (not authentication) request with body: \n {}", response.prettyPrint());
        return response;
    }

    @Step("User send a PUT request with body")
    public static Response put(String path, Object payLoad) {
        logger.info("PUT: {} with body: {}", path, payLoad);
        Response response = given(SpecBuilder.getRequestSpecBuilder_HasAuthentication())
                .log().all()
                .body(payLoad)
                .when()
                .put(path)
                .then()
                .extract().response();

        logger.info("Response of put request with body: \n {}", response.prettyPrint());
        return response;
    }

    @Step("User send a DELETE request")
    public static Response delete(String path) {
        logger.info("DELETE: {}", path);
        Response response = given(SpecBuilder.getRequestSpecBuilder_HasAuthentication())
                .log().all()
                .when()
                .delete(path)
                .then()
                .extract().response();

        logger.info("Response of delete request: \n {}", response.prettyPrint());
        return response;
    }

    @Step("User send a DELETE request with body")
    public static Response delete(String path, Object payLoad) {
        logger.info("DELETE: {} with body: {}", path, payLoad);
        Response response = given(SpecBuilder.getRequestSpecBuilder_HasAuthentication())
                .log().all()
                .body(payLoad)
                .when()
                .delete(path)
                .then()
                .extract().response();

        logger.info("Response of delete with body: \n {}", response.prettyPrint());
        return response;
    }

    @Step("User send a DELETE request with a parameter")
    public static Response delete_WithParams(String path, String paramKey, String paramValue) {
        logger.info("DELETE: {} with a parameter: key={} and value={}", path, paramKey, paramValue);
        Response response = given(SpecBuilder.getRequestSpecBuilder_WithParam(paramKey, paramValue))
                .log().all()
                .when()
                .delete(path)
                .then()
                .extract().response();

        logger.info("Response of delete with a parameter: \n {}", response.prettyPrint());
        return response;
    }

    @Step("Get response value by key: {1}")
    public static String getResponseKeyValue_String(Response response, String responseKey) {
        JsonPath jsonPath = response.jsonPath();
        String responseValue = jsonPath.get(responseKey).toString();

        logger.info("Value by key {} is: {}", responseKey, responseValue);
        Allure.step("Value by key " + responseKey + " is: " + responseValue);
        return responseValue;
    }

    @Step("Get response value by key: {1}")
    public static int getResponseKeyValue_Int(Response response, String responseKey) {
        JsonPath jsonPath = response.jsonPath();
        int responseValue = jsonPath.get(responseKey);

        logger.info("Value by key: {} is {}", responseKey, responseValue);
        Allure.step("Value by key " + responseKey + " is: " + responseValue);
        return responseValue;
    }

    @Step("Get status code from response")
    public static int getStatusCode(Response response) {
        int statusCode = response.getStatusCode();

        logger.info("Status code is: {}", statusCode);
        return statusCode;
    }

    @Step("Get a status line from response")
    public static String getStatusLine(Response response) {
        String statusLine = response.getStatusLine();

        logger.info("Status line is: {}", statusLine);
        return statusLine;
    }

    @Step("Get content type from response")
    public static String getResponseContentType(Response response) {
        String contentType = response.getContentType();

        logger.info("Content type is: {}", contentType);
        return contentType;
    }

    @Step("Get cookie by name: {1} from response")
    public static String getResponseCookieName(Response response, String cookieName) {
        String cookieValue = response.getCookie(cookieName);

        logger.info("Cookie by name is: {}", cookieValue);
        return cookieValue;
    }

    @Step("Verify status code expected: {1}")
    public static void verifyStatusCode(Response response, int expectedStatusCode) {
        logger.info("Verify status code: {} == {}", response.getStatusCode(), expectedStatusCode);
        Allure.step("Status code actual: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "The status code NOT match.");
    }
}
