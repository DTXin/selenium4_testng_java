package org.example.fw_api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

public class SpecBuilder {

    public static RequestSpecification getRequestSpecBuilder() {
        return new RequestSpecBuilder()
                .setBaseUri(Config.URI)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification getRequestSpecBuilder_HasAuthentication() {
        return new RequestSpecBuilder()
                .setBaseUri(Config.URI)
                .setAccept("application/json")
                .setContentType("application/json")
                .addHeader("Cookie", "token=" + BaseTest.TOKEN)
                .build();
    }

    public  static RequestSpecification getRequestSpecBuilder_WithParam(String paramKey, String paramValue) {
        return new RequestSpecBuilder()
                .setBaseUri(Config.URI)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .addQueryParam(paramKey, paramValue)
                .build();
    }

    public  static RequestSpecification getRequestSpecBuilder_WithListParams(Map<String, String> params) {
        return new RequestSpecBuilder()
                .setBaseUri(Config.URI)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .addParams(params)
                .build();
    }

    public static ResponseSpecification getResponseSpecBuilder() {
        return new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                build();
    }
}
