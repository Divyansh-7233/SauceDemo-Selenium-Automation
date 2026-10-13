package org.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HappyPath extends BaseTest{

    String uName = "standard_user";
    String uPassword = "secret_sauce";
    String fName = "Test";
    String lName = "001";
    String pCode = "1980";

    @Test
    public void e2ePurchaseFlow(){
        loginPage.loginAccount(uName,uPassword);
        loginPage.clickLoginBTN();

        String urlProducts = productsPage.getCurrentUrl();
        Assert.assertEquals(urlProducts, "https://www.saucedemo.com/inventory.html");

        String headerProducts = productsPage.getHeaderText();
        Assert.assertEquals(headerProducts, "Products");

        productsPage.addItemsToCart();
        productsPage.goToCart();

        cartPage.checkOut();

        checkOutPage.enterShippingAddress(fName,lName,pCode);
        checkOutPage.continuePayment();

        String itemTotal = checkOutOverviewPage.getItemTotal();
        Assert.assertTrue(itemTotal.contains("39.98"),"Total price is Incorrect!");
        checkOutOverviewPage.finishPayment();

        String successMSG = checkOutCompletePage.getSuccessMessage();
        Assert.assertEquals(successMSG, "Thank you for your order!");

        checkOutCompletePage.backHome();
    }
}
