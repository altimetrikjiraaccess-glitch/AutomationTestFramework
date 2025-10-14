# Automation Test Framework

This project demonstrates a Selenium and TestNG based automation framework for validating the Salesforce login workflow. It provides a modular page-object implementation along with configurable browser execution settings.

## Project Structure

- `src/main/java`: Core framework code such as configuration loading, WebDriver management, and page objects.
- `src/test/java`: Test suites that exercise the Salesforce login flow using the shared framework utilities.
- `src/main/resources`: Configuration files that define default browser capabilities and target URLs.

## Prerequisites

- Java 11 or later
- Maven 3.8+
- Google Chrome installed locally (or update `config.properties` to point to a compatible driver)

## Running the Tests

Use Maven to execute the TestNG suite specified in `testng.xml`:

```bash
mvn clean test
```

You can override configuration values such as browser type or headless mode by updating `src/main/resources/config.properties` or by supplying system properties at runtime.
