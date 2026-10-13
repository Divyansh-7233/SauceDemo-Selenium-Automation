# Test Cases

This document describes the five permanent TestNG scenarios implemented in `src/test/java/org/testCases`. Their steps and expected results are derived from the test methods and page objects. Recorded statuses below are tied to the latest verified normal-suite run on 13 October 2026 at 21:15 (+05:30); they are not guarantees about a future execution. An earlier passing run is also preserved under `submission-evidence/final-run/`.

## Common preconditions and test data

- Java/Maven are configured to compile the project for release 25.
- Google Chrome is installed, the browser driver can be resolved, and the machine can reach `https://www.saucedemo.com/`.
- `BaseTest.@BeforeMethod` opens the SauceDemo login page and initializes the page objects before each test method.
- The public SauceDemo training account used by successful-login scenarios is `standard_user` / `secret_sauce`.

## TC-01 — End-to-end purchase workflow

| Field | Details |
|---|---|
| **Test ID** | TC-01 |
| **Test name** | `HappyPath.e2ePurchaseFlow` |
| **Objective** | Verify a purchase from login through order confirmation. |
| **Preconditions** | Common preconditions above; a fresh browser is opened on the login page. |
| **Test data** | Username `standard_user`; password `secret_sauce`; first name `Test`; last name `001`; postal code `1980`; Sauce Labs Backpack and Sauce Labs Bike Light. |
| **Test steps** | 1. Enter valid credentials and submit. 2. Assert the inventory URL and `Products` header. 3. Add the Backpack and Bike Light. 4. Open the cart and start checkout. 5. Enter the checkout data and continue. 6. Assert the item subtotal contains `39.98`. 7. Finish the order and assert `Thank you for your order!`. 8. Select Back Home. |
| **Expected result** | Login and checkout complete; the asserted URL, header, subtotal, and order confirmation match the expected values. |
| **Recorded actual result** | **PASS** in the latest verified `mvn clean test` run (13 October 2026, 21:15 +05:30). Its Surefire summary records one test, no failures/errors/skips; the generated ExtentReports report marks the test passed. |
| **Final status for that run** | **PASS** |

## TC-02 — Invalid login credentials

| Field | Details |
|---|---|
| **Test ID** | TC-02 |
| **Test name** | `InvalidLoginTest.invalidLoginTest` |
| **Objective** | Confirm invalid credentials are rejected with a visible error. |
| **Preconditions** | Common preconditions above; `BaseTest` opens the login page. |
| **Test data** | Username `invalid_user`; password `wrong_password`. |
| **Test steps** | 1. Enter the invalid username and password. 2. Submit Login. 3. Assert that an error is displayed. 4. Assert that its text contains `Username and password do not match`. |
| **Expected result** | Login is rejected and the expected error substring is present. |
| **Recorded actual result** | **PASS** in the latest verified `mvn clean test` run (13 October 2026, 21:15 +05:30); Surefire records one test, no failures/errors/skips, and ExtentReports marks it passed. |
| **Final status for that run** | **PASS** |

## TC-03 — Empty login credentials

| Field | Details |
|---|---|
| **Test ID** | TC-03 |
| **Test name** | `EmptyCredentialsTest.emptyCredentialsTest` |
| **Objective** | Confirm the login form rejects submission when both credential fields are blank. |
| **Preconditions** | Common preconditions above; `BaseTest` opens the login page. |
| **Test data** | Username and password are both left empty. |
| **Test steps** | 1. Leave both fields empty. 2. Submit Login. 3. Assert that an error is displayed. 4. Assert that its text contains `Username is required`. |
| **Expected result** | Required-username validation is shown. |
| **Recorded actual result** | **PASS** in the latest verified `mvn clean test` run (13 October 2026, 21:15 +05:30); Surefire records one test, no failures/errors/skips, and ExtentReports marks it passed. |
| **Final status for that run** | **PASS** |

## TC-04 — Add and remove cart products

| Field | Details |
|---|---|
| **Test ID** | TC-04 |
| **Test name** | `CartAddRemoveTest.verifyAddAndRemoveProductsFromCart` |
| **Objective** | Check that two products can be added and that removing one leaves the other in the cart. |
| **Preconditions** | Common preconditions above; a fresh browser is opened on the login page. |
| **Test data** | `standard_user` / `secret_sauce`; Sauce Labs Backpack and Sauce Labs Bike Light. |
| **Test steps** | 1. Log in and assert the `Products` header. 2. Add both products and open the cart. 3. Assert both products are present. 4. Remove the Backpack. 5. Assert the Backpack is absent and Bike Light remains. |
| **Expected result** | Both products are initially present; after removal, only Bike Light remains. |
| **Recorded actual result** | **PASS** in the latest verified `mvn clean test` run (13 October 2026, 21:15 +05:30); Surefire records one test, no failures/errors/skips, and ExtentReports marks it passed. |
| **Final status for that run** | **PASS** |

## TC-05 — Checkout required-field validation

| Field | Details |
|---|---|
| **Test ID** | TC-05 |
| **Test name** | `CheckoutValidationTest.verifyEmptyCheckoutFieldsValidation` |
| **Objective** | Confirm checkout validation requires the first-name field. |
| **Preconditions** | Common preconditions above; a fresh browser is opened on the login page. |
| **Test data** | `standard_user` / `secret_sauce`; two configured cart products; all checkout fields left blank. |
| **Test steps** | 1. Log in. 2. Add the configured products and open the cart. 3. Proceed to checkout. 4. Leave the checkout fields empty and select Continue. 5. Assert the checkout error contains `First Name is required`. |
| **Expected result** | The required-first-name validation message is displayed. |
| **Recorded actual result** | **PASS** in the latest verified `mvn clean test` run (13 October 2026, 21:15 +05:30); Surefire records one test, no failures/errors/skips, and ExtentReports marks it passed. |
| **Final status for that run** | **PASS** |

## Separate intentional failure demonstration

`FailureScreenshotDemoTest.demonstratesFailureScreenshotReporting` is not a sixth permanent scenario. It waits for the login username and Login controls to become visible and then intentionally calls:

```java
Assert.fail("Live demo: intentional failure to verify screenshot reporting");
```

Run it by itself:

```bash
mvn -Dtest=FailureScreenshotDemoTest test
```

It is expected to produce one failed test and a non-zero Maven result. The intent is to verify that `BaseTest` records the failure reason, captures a visible login-page screenshot, and attaches the PNG in ExtentReports. Surefire excludes the demo by default, so `mvn clean test` runs the five permanent scenarios only.

The preserved demonstration is in `submission-evidence/live-failure-demo-reusable/`: `index.html`, `org.testCases.FailureScreenshotDemoTest.txt`, and the linked PNG under `screenshots/`. The failure report is evidence of the deliberate demo and must not be confused with the normal-suite results.

## Evidence and result interpretation

The latest verified normal-suite results were read from the generated `target/surefire-reports/` text summaries and `target/extent-reports/index.html` after the 21:15 run. The earlier saved normal-suite report and Surefire text summaries under `submission-evidence/final-run/` are preserved evidence from a different run and are not overwritten by this verification.
