package com.shopguard.driver;

import com.shopguard.config.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    public static WebDriver createDriver() {
        String browser = ConfigManager.getProperty("browser", "chrome").toLowerCase().trim();
        boolean headless = ConfigManager.getBoolean("headless", false);

        WebDriver driver;
        if (browser.equals("edge")) {
            EdgeOptions options = new EdgeOptions();
            if (headless) options.addArguments("--headless=new");
            options.addArguments("--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
            driver = new EdgeDriver(options);
        } else {
            ChromeOptions options = new ChromeOptions();
            if (headless) options.addArguments("--headless=new");
            
            // Turn off password leak check and password manager popups
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.password_manager_leak_detection", false);
            options.setExperimentalOption("prefs", prefs);

            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            options.addArguments(
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-gpu",
                "--window-size=1920,1080",
                "--disable-notifications",
                "--disable-popup-blocking",
                "--disable-features=PasswordLeakDetection"
            );
            driver = new ChromeDriver(options);
        }
        driver.manage().window().maximize();
        return driver;
    }
}