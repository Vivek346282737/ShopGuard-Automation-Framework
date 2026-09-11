package com.shopguard.ui;

import com.shopguard.base.BaseTest;
import com.shopguard.pages.*;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("E-Commerce Order Suite")
@Feature("Checkout & Cart Lifecycle")
public class CheckoutTests extends BaseTest {

    @Test(groups = {"smoke", "regression"}, description = "End-to-end checkout lifecycle test")
    @Description("Complete multi-step purchase flow and assert thank you message")
    public void testFullCheckoutJourney() {
        LoginPage loginPage = new LoginPage(getDriver());
        ProductsPage productsPage = loginPage.loginAs("standard_user", "secret_sauce");

        productsPage.addBackpackToCart();
        Assert.assertEquals(productsPage.getCartBadgeCount(), "1", "Cart badge count mismatch");

        CartPage cartPage = productsPage.goToCart();
        Assert.assertTrue(cartPage.isItemInCart("Sauce Labs Backpack"), "Product item not present in cart");

        CheckoutStepOnePage stepOne = cartPage.clickCheckout();
        CheckoutStepTwoPage stepTwo = stepOne.fillInformation("Vivek", "Prasad", "560001");
        CheckoutCompletePage completePage = stepTwo.clickFinish();

        Assert.assertEquals(completePage.getConfirmationHeader(), "Thank you for your order!", "Order confirmation header mismatch");
    }

    @Test(groups = {"regression"}, description = "Validate cart add and dynamic removal flow")
    @Description("Add items and remove product verifying cart badge update")
    public void testAddAndRemoveFromCart() {
        LoginPage loginPage = new LoginPage(getDriver());
        ProductsPage productsPage = loginPage.loginAs("standard_user", "secret_sauce");

        productsPage.addBackpackToCart();
        Assert.assertEquals(productsPage.getCartBadgeCount(), "1");

        productsPage.removeBackpackFromCart();
        Assert.assertEquals(productsPage.getCartBadgeCount(), "0", "Cart badge should be empty or zero after removal");
    }

    @Test(groups = {"regression"}, description = "Validate product sorting by price low to high")
    @Description("Change sorting to Price (low to high) and assert lowest priced item at top")
    public void testProductSortingLowToHigh() {
        LoginPage loginPage = new LoginPage(getDriver());
        ProductsPage productsPage = loginPage.loginAs("standard_user", "secret_sauce");

        productsPage.selectSortOption("Price (low to high)");
        double lowestPrice = productsPage.getFirstProductPrice();
        Assert.assertEquals(lowestPrice, 7.99, "First sorted item price mismatch");
    }

    @Test(groups = {"regression"}, description = "Checkout form validation: Missing first name")
    @Description("Assert inline validation banner when first name is omitted")
    public void testCheckoutMissingFirstName() {
        LoginPage loginPage = new LoginPage(getDriver());
        ProductsPage productsPage = loginPage.loginAs("standard_user", "secret_sauce");
        CartPage cartPage = productsPage.goToCart();
        CheckoutStepOnePage stepOne = cartPage.clickCheckout();
        stepOne.fillInformation("", "Prasad", "560001");
        Assert.assertEquals(stepOne.getErrorMessage(), "Error: First Name is required", "First name error banner mismatch");
    }

    @Test(groups = {"regression"}, description = "Checkout form validation: Missing postal code")
    @Description("Assert inline validation banner when postal code is omitted")
    public void testCheckoutMissingPostalCode() {
        LoginPage loginPage = new LoginPage(getDriver());
        ProductsPage productsPage = loginPage.loginAs("standard_user", "secret_sauce");
        CartPage cartPage = productsPage.goToCart();
        CheckoutStepOnePage stepOne = cartPage.clickCheckout();
        stepOne.fillInformation("Vivek", "Prasad", "");
        Assert.assertEquals(stepOne.getErrorMessage(), "Error: Postal Code is required", "Postal code error banner mismatch");
    }
}