package org.testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class FailureScreenshotDemoTest extends BaseTest {

    @Test
    public void demonstratesFailureScreenshotReporting() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));

        Assert.fail("Live demo: intentional failure to verify screenshot reporting");
    }
}
