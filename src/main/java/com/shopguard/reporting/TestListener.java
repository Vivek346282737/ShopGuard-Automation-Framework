package com.shopguard.reporting;

import com.shopguard.driver.DriverManager;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {
    private static final Logger log = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Starting execution: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test PASSED: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test FAILED: {} | Reason: {}", result.getMethod().getMethodName(), result.getThrowable().getMessage());
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            saveScreenshot(result.getMethod().getMethodName(), screenshot);
            attachScreenshot(screenshot);
        }
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] attachScreenshot(byte[] screenshot) {
        return screenshot;
    }

    private void saveScreenshot(String testName, byte[] data) {
        try {
            Files.createDirectories(Paths.get("reports/screenshots/"));
            String stamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String file = "reports/screenshots/" + testName + "_" + stamp + ".png";
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(data);
            }
            log.info("Saved failure screenshot locally at: {}", file);
        } catch (Exception e) {
            log.error("Screenshot capture failed", e);
        }
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("Suite Execution Started: {}", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Suite Execution Finished: {}", context.getName());
    }
}