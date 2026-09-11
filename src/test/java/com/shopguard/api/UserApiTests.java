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
@Feature("User Management Endpoints")
public class UserApiTests {

    @BeforeClass(groups = {"smoke", "regression"})
    public void setupApi() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test(groups = {"smoke", "regression"}, description = "Retrieve list of users (GET)")
    @Description("Validate HTTP 200 and schema consistency of user list")
    public void testGetUsersList() {
        given()
            .queryParam("page", 2)
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("page", equalTo(2))
            .body("data", hasSize(greaterThan(0)))
            .body("data[0].id", notNullValue());
    }

    @Test(groups = {"smoke", "regression"}, description = "Create user record (POST)")
    @Description("Validate HTTP 201 response and verify returned resource ID")
    public void testCreateUser() {
        String payload = "{\"name\": \"Vivek\", \"job\": \"Automation Analyst\"}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .body("name", equalTo("Vivek"))
            .body("job", equalTo("Automation Analyst"))
            .body("id", notNullValue());
    }

    @Test(groups = {"regression"}, description = "Negative query: user not found (GET)")
    @Description("Validate HTTP 404 response on missing user record")
    public void testUserNotFound() {
        given()
        .when()
            .get("/users/23")
        .then()
            .statusCode(404);
    }
}