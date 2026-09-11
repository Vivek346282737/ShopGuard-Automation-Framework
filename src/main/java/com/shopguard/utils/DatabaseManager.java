package com.shopguard.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseManager {
    // In enterprise CI/CD, mock or embedded H2/MySQL connection
    private static final String DB_URL = "jdbc:h2:mem:shopguard_db;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASS = "";

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void initializeMockDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS orders (order_id VARCHAR(50), customer_name VARCHAR(100), amount DECIMAL(10,2), status VARCHAR(20))");
            stmt.execute("MERGE INTO orders KEY(order_id) VALUES('ORD-9821', 'Standard User', 29.99, 'CONFIRMED')");
        } catch (Exception e) {
            System.err.println("DB Init Note: " + e.getMessage());
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