package com.shopguard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By pageTitle = By.xpath("//span[@class='title' and text()='Products']");
    private By backpackAddToCartBtn = By.id("add-to-cart-sauce-labs-backpack");
    private By bikeLightAddToCartBtn = By.id("add-to-cart-sauce-labs-bike-light");
    private By backpackRemoveBtn = By.id("remove-sauce-labs-backpack");
    private By shoppingCartBadge = By.className("shopping_cart_badge");
    private By shoppingCartLink = By.className("shopping_cart_link");
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutSidebarLink = By.id("logout_sidebar_link");
    private By sortDropdown = By.className("product_sort_container");
    private By inventoryItemPrices = By.className("inventory_item_price");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isProductsPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
    }

    public void addBackpackToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(backpackAddToCartBtn)).click();
    }

    public void addBikeLightToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(bikeLightAddToCartBtn)).click();
    }

    public void removeBackpackFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(backpackRemoveBtn)).click();
    }

    public String getCartBadgeCount() {
        List<WebElement> badges = driver.findElements(shoppingCartBadge);
        return badges.isEmpty() ? "0" : badges.get(0).getText();
    }

    public void selectSortOption(String visibleText) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown));
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

    public double getFirstProductPrice() {
        List<WebElement> prices = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(inventoryItemPrices));
        String priceText = prices.get(0).getText().replace("$", "");
        return Double.parseDouble(priceText);
    }

    public CartPage goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLink)).click();
        return new CartPage(driver);
    }

    public void openSideMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutSidebarLink)).click();
    }
}