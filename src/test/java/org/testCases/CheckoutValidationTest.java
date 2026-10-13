package org.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutValidationTest extends BaseTest {

    @Test
    public void verifyEmptyCheckoutFieldsValidation() {

        // Login with valid credentials
        loginPage.loginAccount("standard_user", "secret_sauce");
        loginPage.clickLoginBTN();

        // Add products to cart
        productsPage.addItemsToCart();
        productsPage.goToCart();

        // Proceed to checkout
        cartPage.checkOut();

        // Leave all checkout fields empty and continue
        checkOutPage.continuePayment();

        // Verify the required-field validation message
        String errorMessage = checkOutPage.getErrorMessage();

        Assert.assertTrue(
            errorMessage.contains("First Name is required"),
            "Expected first-name validation message was not displayed"
        );
    }
}
