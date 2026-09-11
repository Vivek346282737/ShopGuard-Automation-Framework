package com.shopguard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends BasePage {
    private final By pageTitle = By.className("title");
    private final By inventoryItems = By.className("inventory_item");
    private final By itemNames = By.className("inventory_item_name");
    private final By itemPrices = By.className("inventory_item_price");
    private final By sortDropdown = By.className("product_sort_container");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return getText(pageTitle);
    }

    public int getProductCount() {
        return getElements(inventoryItems).size();
    }

    public List<String> getProductNames() {
        return getElements(itemNames).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getProductPrices() {
        return getElements(itemPrices).stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "").trim()))
                .collect(Collectors.toList());
    }

    public void selectSortOption(String visibleText) {
        Select select = new Select(waitForVisibility(sortDropdown));
        select.selectByVisibleText(visibleText);
    }

    public void addProductToCart(String productName) {
        // Universal button locator inside product item container
        String xpath = String.format("//div[@class='inventory_item'][.//div[contains(@class,'inventory_item_name') and text()='%s']]//button", productName);
        click(By.xpath(xpath));
    }

    public void removeProductFromCart(String productName) {
        String xpath = String.format("//div[@class='inventory_item'][.//div[contains(@class,'inventory_item_name') and text()='%s']]//button", productName);
        click(By.xpath(xpath));
    }

    public int getCartBadgeCount() {
        if (!isDisplayed(cartBadge)) return 0;
        return Integer.parseInt(getText(cartBadge));
    }

    public CartPage openCart() {
        click(cartLink);
        return new CartPage(driver);
    }

    public LoginPage logout() {
        click(menuButton);
        click(logoutLink);
        return new LoginPage(driver);
    }
}