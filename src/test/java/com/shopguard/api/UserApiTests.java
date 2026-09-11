package com.shopguard.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserApiTests {

    @BeforeClass(alwaysRun = true)
    public void setupApi() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test(groups = {"API", "Smoke"}, description = "API01: GET Users List and status 200")
    public void testGetUsersList() {
        Response res = RestAssured.given()
                .queryParam("page", 2)
                .when()
                .get("/api/users");

        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.jsonPath().getInt("page"), 2);
        Assert.assertTrue(res.jsonPath().getList("data").size() > 0);
    }

    @Test(groups = {"API", "Regression"}, description = "API02: POST Create User and status 201")
    public void testCreateUser() {
        String payload = "{\"name\": \"Vivek Prasad\", \"job\": \"Quality Engineer\"}";

        Response res = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/api/users");

        Assert.assertEquals(res.getStatusCode(), 201);
        Assert.assertEquals(res.jsonPath().getString("name"), "Vivek Prasad");
        Assert.assertNotNull(res.jsonPath().getString("id"));
    }

    @Test(groups = {"API", "Regression"}, description = "API03: GET User Not Found (Negative 404)")
    public void testUserNotFound() {
        Response res = RestAssured.get("/api/users/9999");
        Assert.assertEquals(res.getStatusCode(), 404);
    }
}