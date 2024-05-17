package com.apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ClickUpClient {

    private static final String API_KEY = "pk_74411390_GQEQLKC3PJ3JBV21VPVVIDJBDEPP2GJ5";
    private static final String BASE_URL = "https://api.clickup.com/api/v2/";

    private static RequestSpecification clickUpSpec() {
        return RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", API_KEY)
                .when()
                .baseUri(BASE_URL);
    }

    public static Response deleteFolder(String folderId) {
        return RestAssured
                .given(clickUpSpec())
                .when()
                .delete("folder/" + folderId)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }
}
