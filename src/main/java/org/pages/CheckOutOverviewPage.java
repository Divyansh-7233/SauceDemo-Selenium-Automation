package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutOverviewPage {

    private final WebDriver driver;

    public CheckOutOverviewPage(WebDriver driver){
        this.driver = driver;
    }

    private By cancelBtn = By.id("cancel");
    private By finishBtn = By.id("finish");
    private By total = By.className("summary_subtotal_label");

    public void finishPayment(){
        driver.findElement(finishBtn).click();
    }

    public void cancelPayment(){
        driver.findElement(cancelBtn).click();
    }

    public String getItemTotal(){
        return driver.findElement(total).getText();
    }

}
