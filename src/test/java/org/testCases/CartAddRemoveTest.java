package org.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CartAddRemoveTest extends BaseTest {

    @Test
    public void verifyAddAndRemoveProductsFromCart() {

        // Login with valid credentials
        loginPage.loginAccount("standard_user", "secret_sauce");
        loginPage.clickLoginBTN();

        // Verify successful login
        Assert.assertEquals(
            productsPage.getHeaderText(),
            "Products",
            "Login failed or Products page not displayed"
        );

        // Add both products to cart
        productsPage.addItemsToCart();

        // Open cart
        productsPage.goToCart();

        // Verify both products are present
        Assert.assertTrue(
            cartPage.isProductPresent("Sauce Labs Backpack"),
            "Backpack was not added to cart"
        );

        Assert.assertTrue(
            cartPage.isProductPresent("Sauce Labs Bike Light"),
            "Bike Light was not added to cart"
        );

        // Remove Backpack
        cartPage.removeBackpack();

        // Verify Backpack is removed
        Assert.assertFalse(
            cartPage.isProductPresent("Sauce Labs Backpack"),
            "Backpack was not removed from cart"
        );

        // Verify Bike Light remains
        Assert.assertTrue(
            cartPage.isProductPresent("Sauce Labs Bike Light"),
            "Bike Light should remain in cart"
        );
    }
}