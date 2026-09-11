package com.shopguard.ui;

import com.shopguard.base.BaseTest;
import com.shopguard.driver.DriverManager;
import com.shopguard.pages.LoginPage;
import com.shopguard.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(groups = {"Smoke", "Regression"}, description = "TC01: Valid credentials se successful login check")
    public void testValidLogin() {
        LoginPage login = new LoginPage(DriverManager.getDriver());
        ProductsPage products = login.loginAs("standard_user", "secret_sauce");

        Assert.assertEquals(products.getTitle(), "Products");
        Assert.assertTrue(products.getProductCount() > 0, "Products catalogue load hona chahiye");
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() {
        return new Object[][]{
            {"invalid_user", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
            {"standard_user", "wrong_pass", "Epic sadface: Username and password do not match any user in this service"},
            {"", "secret_sauce", "Epic sadface: Username is required"},
            {"standard_user", "", "Epic sadface: Password is required"},
            {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."}
        };
    }

    @Test(dataProvider = "invalidLoginData", groups = {"Regression"}, description = "TC02-06: Invalid credentials validation check")
    public void testInvalidLoginScenarios(String user, String pass, String expectedMsg) {
        LoginPage login = new LoginPage(DriverManager.getDriver());
        login.loginAs(user, pass);

        Assert.assertTrue(login.isErrorMessageDisplayed());
        Assert.assertEquals(login.getErrorMessage(), expectedMsg);
    }

    @Test(groups = {"Sanity", "Regression"}, description = "TC07: Logout function verify karna")
    public void testLogout() {
        LoginPage login = new LoginPage(DriverManager.getDriver());
        ProductsPage products = login.loginAs("standard_user", "secret_sauce");
        LoginPage backToLogin = products.logout();

        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("saucedemo.com"));
    }
}