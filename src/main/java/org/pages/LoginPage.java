package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

//    ---Initialize the driver---
    private final WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

//    ---Element Locators---
    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginBtn = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

//    ---Actions of the page---

//    Login to the account enter username and password
    public void loginAccount(String name, String pwd){
        driver.findElement(username).sendKeys(name);
        driver.findElement(password).sendKeys(pwd);
    }

//    Click the Login Button
    public void clickLoginBTN(){
        driver.findElement(loginBtn).click();
    }

    public String getErrorMessage() {
    return driver.findElement(errorMessage).getText();
    }

    public boolean isErrorMessageDisplayed() {
    return driver.findElement(errorMessage).isDisplayed();
    }

    public void clearLoginFields() {
    driver.findElement(username).clear();
    driver.findElement(password).clear();
    }

}
