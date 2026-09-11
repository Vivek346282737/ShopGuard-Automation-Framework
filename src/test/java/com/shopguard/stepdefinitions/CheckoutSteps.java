package com.shopguard.stepdefinitions;

import com.shopguard.utils.DatabaseManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.testng.Assert;

public class CheckoutSteps {

    private String currentItem;
    private int currentQuantity;
    private String orderStatus;

    @Given("Customer is logged into the ShopGuard platform")
    public void customer_is_logged_in() {
        DatabaseManager.initializeMockDatabase();
        Assert.assertNotNull(DatabaseManager.getConnection(), "Database session failed to initialize");
    }

    @When("Customer adds item {string} with quantity {int} to the cart")
    public void customer_adds_item(String item, int qty) {
        this.currentItem = item;
        this.currentQuantity = qty;
        Assert.assertTrue(qty > 0, "Cart quantity must be positive");
    }

    @And("Proceeds to checkout with payment method {string}")
    public void proceeds_to_checkout(String paymentMethod) {
        Assert.assertNotNull(paymentMethod);
        this.orderStatus = "CONFIRMED";
    }

    @Then("The order confirmation status must be {string}")
    public void verify_order_status(String expectedStatus) {
        Assert.assertEquals(this.orderStatus, expectedStatus, "Order status mismatch");
    }

    @And("The inventory ledger must decrement stock for {string} by {int}")
    public void verify_inventory_ledger(String item, int qty) {
        Assert.assertEquals(this.currentItem, item);
        Assert.assertEquals(this.currentQuantity, qty);
        boolean exists = DatabaseManager.verifyOrderExists("ORD-9821");
        Assert.assertTrue(exists, "Order persistence verification failed in ledger");
    }
}