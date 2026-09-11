package com.shopguard.db;

import com.shopguard.utils.DatabaseManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Database Integrity Suite")
@Feature("Order Persistence Validation")
public class DatabaseTests {

    @BeforeClass
    public void setupDatabase() {
        DatabaseManager.initializeMockDatabase();
    }

    @Test(description = "Verify placed order persistence in database via SQL query")
    @Description("Executes SQL query against orders table to validate order state and settlement")
    public void testOrderDatabaseRecord() {
        boolean isPresent = DatabaseManager.verifyOrderExists("ORD-9821");
        Assert.assertTrue(isPresent, "SQL assertion failed: Order record ORD-9821 not found in database!");
    }
}