package com.shopguard.ui;

import com.shopguard.base.BaseTest;
import com.shopguard.driver.DriverManager;
import com.shopguard.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {

    @Test(groups = {"Smoke", "Regression"}, description = "TC08: Add to cart aur End-to-End Checkout")
    public void testFullCheckoutJourney() {
        LoginPage login = new LoginPage(DriverManager.getDriver());
        ProductsPage products = login.loginAs("standard_user", "secret_sauce");

        products.addProductToCart("Sauce Labs Backpack");
        Assert.assertEquals(products.getCartBadgeCount(), 1);

        CartPage cart = products.openCart();
        Assert.assertTrue(cart.isProductInCart("Sauce Labs Backpack"));

        CheckoutStepOnePage stepOne = cart.proceedToCheckout();
        CheckoutStepTwoPage stepTwo = stepOne.submitValidInfo("Vivek", "Prasad", "831001");

        Assert.assertTrue(stepTwo.getSubtotal().contains("29.99"), "Subtotal should contain 29.99");
        CheckoutCompletePage complete = stepTwo.finishCheckout();
        Assert.assertEquals(complete.getCompleteMessage(), "Thank you for your order!");
    }

    @Test(groups = {"Regression"}, description = "TC09: Missing Postal code error check")
    public void testCheckoutMissingPostalCode() {
        LoginPage login = new LoginPage(DriverManager.getDriver());
        ProductsPage products = login.loginAs("standard_user", "secret_sauce");

        products.addProductToCart("Sauce Labs Bike Light");
        CartPage cart = products.openCart();
        CheckoutStepOnePage stepOne = cart.proceedToCheckout();

        stepOne.enterCustomerInfo("Vivek", "Prasad", "");
        stepOne.clickContinue();

        String err = stepOne.getErrorMessage();
        Assert.assertTrue(err.contains("Postal Code is required"), "Expected postal code required message, got: " + err);
    }
}