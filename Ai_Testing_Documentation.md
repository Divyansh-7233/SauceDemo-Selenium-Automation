# AI-Assisted Development and Testing Notes

## Purpose and scope

This document describes the role of generative AI assistance in the development and verification workflow for the SauceDemo Selenium project. It does **not** describe a runtime AI feature in the application.

The application code runs conventional Java, Selenium WebDriver, TestNG, Maven, and ExtentReports. No live generative-AI API, LLM client, AI self-healing, predictive test selection, or automatic AI-generated runtime testing is implemented in the current project.

## Development-time assistance

AI assistance was used as a development-time aid in the project workflow. The supported activities include:

- Reviewing the project structure and existing Java/Maven behavior before making changes.
- Discussing test scenario coverage and documenting the existing five permanent workflows.
- Debugging test-run and report behavior by comparing source code, Maven/Surefire output, and ExtentReports output.
- Reviewing failure screenshot timing and report attachment paths, including inspection of the actual PNG evidence.
- Refining project documentation and explanations for student/college submission use.

AI suggestions were not treated as proof that a feature worked. Code and documentation claims were checked against the implementation and available execution evidence.

## Five permanent scenarios

The five scenarios exercise existing application behavior:

1. End-to-end purchase using the SauceDemo standard test account.
2. Rejection of invalid login credentials.
3. Login validation with both credential fields empty.
4. Adding two products to the cart and removing the Backpack.
5. Checkout validation when required fields are left empty.

The test classes encode their actions and TestNG assertions; AI assistance helped with reviewing and describing these scenarios. The tests themselves run with TestNG and Selenium, not with an AI model. See [docs/test-cases.md](docs/test-cases.md) for implementation-level steps and run-specific results.

## Intentional failure and screenshot verification

`FailureScreenshotDemoTest` is a separate, reusable demonstration. It waits until the SauceDemo username and login controls are visible, then invokes `Assert.fail` with a clear intentional-failure message. The common `BaseTest` failure handler records the throwable, captures a browser screenshot before teardown, and attaches the PNG to the ExtentReports test entry.

The expected Maven outcome of this demonstration is a test failure. It is excluded from ordinary Surefire discovery so it does not make `mvn clean test` fail. The class is selected explicitly with:

```bash
mvn -Dtest=FailureScreenshotDemoTest test
```

The permanent suite is run separately with:

```bash
mvn clean test
```

See [submission-evidence/live-failure-demo-reusable/index.html](submission-evidence/live-failure-demo-reusable/index.html) and its adjacent `screenshots/` PNG for preserved demonstration evidence. This folder is an archived copy and is not automatically refreshed by either command.

## How results were checked

Verification relies on conventional test and report evidence:

- Maven output reports whether compilation and the test goal completed.
- Surefire text/XML reports provide per-class counts and failures.
- ExtentReports HTML provides test status, failure details, and screenshot attachment links.
- The referenced image file is checked directly when verifying a screenshot, rather than assuming an HTML attachment proves the image is valid.
- `BaseTest` source is checked for lifecycle ordering: screenshot capture happens in `@AfterMethod` before `driver.quit()`, the ChromeDriver service is stopped, and ExtentReports is flushed in `@AfterSuite`.

Recorded outcomes and their specific evidence paths are maintained in [docs/test-execution-summary.md](docs/test-execution-summary.md). Results apply only to the run identified there and should not be generalized to future executions.

## AI-related capabilities and limitations

| Area | Current status |
|---|---|
| Generative AI used while developing/reviewing/documenting | Development-time assistance only |
| AI model/API called by application at runtime | No |
| Runtime self-healing selectors | No |
| Predictive or AI-based test selection | No |
| AI-generated tests created during application execution | No |
| Browser automation and assertions | Implemented with Selenium and TestNG |
| Failure logging/reporting | Implemented with ExtentReports and `BaseTest` |

Human review remains important: expected values, assertions, selectors, dependencies, and generated documentation should be checked against the actual application and test output. AI suggestions can be incomplete or incorrect and do not replace test execution or engineering judgment.

## Future possibilities (not implemented)

If the project later adds AI-related capabilities, possible research directions could include using AI to help draft test ideas offline or summarize historical failures. Any such feature would need a separate design, privacy/security review, explicit runtime dependencies, and verifiable acceptance tests. These are future possibilities only; they are not part of this project today.
