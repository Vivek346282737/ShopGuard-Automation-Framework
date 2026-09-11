package com.shopguard.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseManager {
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/shopguard_db?createDatabaseIfNotExist=true&useSSL=false";
    private static final String H2_URL = "jdbc:h2:mem:shopguard_db;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASS = "";

    public static Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(H2_URL, USER, PASS);
        } catch (Exception e) {
            throw new RuntimeException("Database connection initialization failed: " + e.getMessage());
        }
    }

    public static void initializeMockDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS orders (order_id VARCHAR(50) PRIMARY KEY, customer_name VARCHAR(100), amount DECIMAL(10,2), status VARCHAR(20))");
            stmt.execute("MERGE INTO orders KEY(order_id) VALUES('ORD-9821', 'Standard User', 29.99, 'CONFIRMED')");
        } catch (Exception e) {
            System.err.println("DB Initialization Log: " + e.getMessage());
        }
    }

    public static boolean verifyOrderExists(String orderId) {
        String query = "SELECT COUNT(*) FROM orders WHERE order_id = '" + orderId + "'";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}