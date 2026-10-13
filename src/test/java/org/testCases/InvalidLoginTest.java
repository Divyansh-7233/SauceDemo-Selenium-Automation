package org.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidLoginTest extends BaseTest {

    @Test
    public void invalidLoginTest() {
        loginPage.loginAccount("invalid_user", "wrong_password");
        loginPage.clickLoginBTN();

        Assert.assertTrue(
            loginPage.isErrorMessageDisplayed(),
            "Error message should be displayed for invalid login."
        );

        String error = loginPage.getErrorMessage();

        Assert.assertTrue(
            error.contains("Username and password do not match"),
            "Unexpected error message: " + error
        );
    }
}
