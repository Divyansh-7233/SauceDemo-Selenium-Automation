package org.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EmptyCredentialsTest extends BaseTest {

    @Test
    public void emptyCredentialsTest() {
        loginPage.clickLoginBTN();

        Assert.assertTrue(
            loginPage.isErrorMessageDisplayed(),
            "Error message should appear for empty credentials."
        );

        String error = loginPage.getErrorMessage();

        Assert.assertTrue(
            error.contains("Username is required"),
            "Unexpected validation message: " + error
        );
    }
}
