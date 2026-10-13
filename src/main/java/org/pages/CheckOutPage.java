package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutPage {

    private final WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
    }

    private By fName = By.id("first-name");
    private By lName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By cancelBtn = By.id("cancel");
    private By errorMessage = By.cssSelector("[data-test='error']");

    public void enterShippingAddress(String firstName, String lastName, String zipCode){
        driver.findElement(fName).sendKeys(firstName);
        driver.findElement(lName).sendKeys(lastName);
        driver.findElement(postalCode).sendKeys(zipCode);
    }

    public void continuePayment(){
        driver.findElement(continueBtn).click();
    }

    public void cancelPayment(){
        driver.findElement(cancelBtn).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

}
