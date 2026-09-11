package com.shopguard.base;

import com.shopguard.config.ConfigManager;
import com.shopguard.driver.DriverFactory;
import com.shopguard.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected static final Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        log.info("Initializing browser session...");
        WebDriver driver = DriverFactory.createDriver();
        DriverManager.setDriver(driver);

        String url = ConfigManager.getProperty("baseUrl", "https://www.saucedemo.com/");
        driver.get(url);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            log.info("Closing browser session...");
            driver.quit();
            DriverManager.unload();
        }
    }
}