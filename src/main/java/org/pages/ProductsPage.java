package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {

    private final WebDriver driver;

    public ProductsPage(WebDriver driver){
        this.driver = driver;
    }

    private By header = By.className("title");
    private By sauceLbBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private By sauceLBLight = By.id("add-to-cart-sauce-labs-bike-light");
    private By cart = By.id("shopping_cart_container");

    //Verify that the user login was successfully.
    public String getHeaderText(){
        return driver.findElement(header).getText();
    }
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    //Add item to the Cart
    public void addItemsToCart(){
        driver.findElement(sauceLbBackpack).click();
        driver.findElement(sauceLBLight).click();
    }

    //Go to the cart Page
    public void goToCart(){
        driver.findElement(cart).click();
    }

}
