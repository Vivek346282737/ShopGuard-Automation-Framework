package com.shopguard.api;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("API Validation Suite")
@Feature("User & Order Service Endpoints")
public class UserApiTests {

    @BeforeClass(groups = {"smoke", "regression"})
    public void setupApi() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test(groups = {"smoke", "regression"}, description = "Retrieve resource record (GET)")
    @Description("Validate HTTP 200 OK and schema consistency of resource endpoint")
    public void testGetUsersList() {
        given()
        .when()
            .get("/posts/1")
        .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("title", notNullValue());
    }

    @Test(groups = {"smoke", "regression"}, description = "Create resource entry (POST)")
    @Description("Validate HTTP 201 Created response and assert returned entity ID")
    public void testCreateUser() {
        String payload = "{\"title\": \"Order Validation\", \"body\": \"Automated Verification\", \"userId\": 1}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("title", equalTo("Order Validation"))
            .body("id", notNullValue());
    }

    @Test(groups = {"regression"}, description = "Negative query: resource not found (GET)")
    @Description("Validate HTTP 404 response on non-existent resource query")
    public void testUserNotFound() {
        given()
        .when()
            .get("/posts/999999")
        .then()
            .statusCode(404);
    }
}