package org.testCases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.pages.CartPage;
import org.pages.CheckOutCompletePage;
import org.pages.CheckOutOverviewPage;
import org.pages.CheckOutPage;
import org.pages.LoginPage;
import org.pages.ProductsPage;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;


public class BaseTest {

    private static final Path REPORT_DIRECTORY = Paths.get(
            System.getProperty("user.dir"), "target", "extent-reports");

    protected WebDriver driver;
    private ChromeDriverService driverService;

    private static ExtentReports extent;
    protected ExtentTest test;

    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected CartPage cartPage;
    protected CheckOutPage checkOutPage;
    protected CheckOutCompletePage checkOutCompletePage;
    protected CheckOutOverviewPage checkOutOverviewPage;


    @BeforeSuite
    public void setupReport() throws IOException {
        Files.createDirectories(REPORT_DIRECTORY);

        ExtentSparkReporter reporter = new ExtentSparkReporter(
                REPORT_DIRECTORY.resolve("index.html").toString());
        reporter.config().setReportName(
                "Web Automation Results of 'Saucedemo' Application");
        reporter.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Divyansh Bhainsora");
    }

    @BeforeMethod
    public void beforeTest(Method method) {
        test = extent.createTest(method.getName());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driverService = ChromeDriverService.createDefaultService();
        driver = new ChromeDriver(driverService, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkOutCompletePage = new CheckOutCompletePage(driver);
        checkOutPage = new CheckOutPage(driver);
        checkOutOverviewPage = new CheckOutOverviewPage(driver);

    }

    @AfterMethod(alwaysRun = true)
    public void getResult(ITestResult result) {
        try {
            if (test != null && result.getStatus() == ITestResult.FAILURE) {
                test.log(Status.FAIL, "Test Failed: " + result.getName());
                test.log(Status.FAIL, result.getThrowable());
                attachFailureScreenshot(result);
            } else if (test != null && result.getStatus() == ITestResult.SUCCESS) {
                test.log(Status.PASS, "Test Passed: " + result.getName());
            } else if (test != null && result.getStatus() == ITestResult.SKIP) {
                test.log(Status.SKIP, "Test Skipped: " + result.getName());
            }
        } finally {
            WebDriver testDriver = driver;
            ChromeDriverService testDriverService = driverService;
            driver = null;
            driverService = null;
            try {
                if (testDriver != null) {
                    testDriver.quit();
                }
            } finally {
                if (testDriverService != null) {
                    testDriverService.stop();
                }
            }
        }
    }

    private void attachFailureScreenshot(ITestResult result) {
        if (!(driver instanceof TakesScreenshot screenshotDriver)) {
            test.log(Status.WARNING, "This WebDriver does not support screenshots.");
            return;
        }

        String className = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();
        String fileName = (className + "_" + methodName + "_" + System.currentTimeMillis())
                .replaceAll("[^A-Za-z0-9._-]", "_") + ".png";
        Path screenshotDirectory = REPORT_DIRECTORY.resolve("screenshots");
        Path screenshotPath = screenshotDirectory.resolve(fileName);

        try {
            Files.createDirectories(screenshotDirectory);
            Files.write(screenshotPath, screenshotDriver.getScreenshotAs(OutputType.BYTES));
            String reportRelativePath = REPORT_DIRECTORY.relativize(screenshotPath)
                    .toString()
                    .replace('\\', '/');
            test.fail("Screenshot captured when the test failed.",
                    MediaEntityBuilder.createScreenCaptureFromPath(reportRelativePath).build());
        } catch (IOException | WebDriverException exception) {
            test.log(Status.WARNING, "Could not capture or attach failure screenshot: "
                    + exception.getMessage());
        }
    }

    @AfterSuite
    public void tearDown() {
        if (extent != null) {
            extent.flush();
        }
    }

}
