# Automation Test Framework

This project provides a lightweight Selenium and TestNG based framework for automating smoke tests against Salesforce.

## Running the smoke test suite

Use Maven to execute the smoke suite in headless mode:

```bash
mvn test -Dsuite=smoke -Dheadless=true
```

The `suite` property maps to the TestNG group filter configured in the build, and `headless` controls whether Chrome runs without a visible browser window. You can set `-Dheadless=false` when debugging locally to run the tests with a visible browser.
