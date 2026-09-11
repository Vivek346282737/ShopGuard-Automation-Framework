package com.shopguard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutStepTwoPage extends BasePage {
    private final By subtotal = By.className("summary_subtotal_label");
    private final By finishButton = By.id("finish");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public String getSubtotal() {
        return getText(subtotal);
    }

    public CheckoutCompletePage finishCheckout() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(finishButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);
        try {
            Thread.sleep(300);
        } catch (InterruptedException ignored) {}

        try {
            btn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }

        try {
            new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.urlContains("checkout-complete"));
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }

        wait.until(ExpectedConditions.urlContains("checkout-complete"));
        return new CheckoutCompletePage(driver);
    }
}