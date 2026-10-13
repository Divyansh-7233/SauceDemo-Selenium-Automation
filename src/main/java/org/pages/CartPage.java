package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private By continueShoppingBtn = By.id("continue-shopping");
    private By checkOutBtn = By.id("checkout");

    public void continueShopping() {
        driver.findElement(continueShoppingBtn).click();
    }

    public void checkOut() {
        driver.findElement(checkOutBtn).click();
    }

    // Remove Backpack from cart
    public void removeBackpack() {
        driver.findElement(
            By.id("remove-sauce-labs-backpack")
        ).click();
    }

    // Verify whether a product is present in the cart
    public boolean isProductPresent(String productName) {
        return driver.findElements(By.cssSelector(".cart_item .inventory_item_name"))
                .stream()
                .anyMatch(item -> item.getText().equals(productName));
    }

}
