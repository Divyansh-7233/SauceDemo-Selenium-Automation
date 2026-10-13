package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutCompletePage {

    private final WebDriver driver;

    public CheckOutCompletePage(WebDriver driver){
        this.driver = driver;
    }

    private By backHomeBtn = By.id("back-to-products");
    private By successMsg = By.className("complete-header");

    public void backHome(){
        driver.findElement(backHomeBtn).click();
    }

    public String getSuccessMessage(){
        return driver.findElement(successMsg).getText();
    }

}
