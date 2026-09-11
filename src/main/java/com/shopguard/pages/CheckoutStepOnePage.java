package com.shopguard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutStepOnePage extends BasePage {
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    private void fillField(By locator, String value) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        if (value != null && !value.isEmpty()) {
            element.sendKeys(value);
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", 
                element
            );
        }
    }

    public void enterCustomerInfo(String firstName, String lastName, String postalCode) {
        fillField(firstNameField, firstName);
        fillField(lastNameField, lastName);
        fillField(postalCodeField, postalCode);
    }

    public void clickContinue() {
        WebElement btn = waitForClickability(continueButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    public CheckoutStepTwoPage submitValidInfo(String fName, String lName, String zip) {
        enterCustomerInfo(fName, lName, zip);
        clickContinue();
        wait.until(ExpectedConditions.urlContains("checkout-step-two"));
        return new CheckoutStepTwoPage(driver);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}