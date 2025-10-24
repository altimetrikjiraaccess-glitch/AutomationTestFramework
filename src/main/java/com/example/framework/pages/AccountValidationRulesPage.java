package com.example.framework.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountValidationRulesPage extends BasePage {

    private static final By VALIDATION_RULES_LINK = By.cssSelector("a[title='Validation Rules']");
    private static final By NEW_BUTTON = By.cssSelector("div[title='New']");
    private static final By RULE_NAME_INPUT = By.id("Name");
    private static final By DESCRIPTION_TEXTAREA = By.id("Description");
    private static final By FORMULA_TEXTAREA = By.id("Formula");
    private static final By ERROR_MESSAGE_TEXTAREA = By.id("ErrorMessage");
    private static final By ERROR_LOCATION_DROPDOWN = By.id("ErrorDisplayField");
    private static final By SAVE_BUTTON = By.cssSelector("button[title='Save']");

    public void openValidationRules() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(VALIDATION_RULES_LINK));
        link.click();
    }

    public void ensureValidationRuleExists(String ruleName, String description, String formula, String errorMessage, String errorField) {
        if (isValidationRulePresent(ruleName)) {
            return;
        }

        createValidationRule(ruleName, description, formula, errorMessage, errorField);
    }

    private boolean isValidationRulePresent(String ruleName) {
        By ruleRow = By.xpath(String.format("//table[contains(@class,'slds-table')]//a[text()='%s']", ruleName));
        return !driver.findElements(ruleRow).isEmpty();
    }

    private void createValidationRule(String ruleName, String description, String formula, String errorMessage, String errorField) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(NEW_BUTTON)).click();

        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(RULE_NAME_INPUT));
        nameInput.clear();
        nameInput.sendKeys(ruleName);

        WebElement descriptionInput = wait.until(ExpectedConditions.visibilityOfElementLocated(DESCRIPTION_TEXTAREA));
        descriptionInput.clear();
        descriptionInput.sendKeys(description);

        WebElement formulaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(FORMULA_TEXTAREA));
        formulaInput.clear();
        formulaInput.sendKeys(formula);

        WebElement errorMessageInput = wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE_TEXTAREA));
        errorMessageInput.clear();
        errorMessageInput.sendKeys(errorMessage);

        WebElement errorLocation = wait.until(ExpectedConditions.elementToBeClickable(ERROR_LOCATION_DROPDOWN));
        errorLocation.click();
        By option = By.xpath(String.format("//select[@id='ErrorDisplayField']/option[text()='%s']", errorField));
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();

        wait.until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON)).click();
    }
}
