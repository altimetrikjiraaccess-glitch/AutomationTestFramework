package com.example.framework.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountFieldsPage extends BasePage {

    private static final By FIELDS_AND_RELATIONSHIPS_TAB = By.cssSelector("a[title='Fields & Relationships']");
    private static final By NEW_BUTTON = By.cssSelector("div[title='New']");
    private static final By PICKLIST_OPTION = By.cssSelector("input[value='Picklist']");
    private static final By NEXT_BUTTON = By.cssSelector("button[title='Next']");
    private static final By FIELD_LABEL_INPUT = By.id("MasterLabel");
    private static final By PICKLIST_VALUES_TEXTAREA = By.id("picklistValues");
    private static final By FIELD_LEVEL_SECURITY_NEXT = By.cssSelector("button[title='Next']");
    private static final By SAVE_BUTTON = By.cssSelector("button[title='Save']");

    public void openFieldsAndRelationships() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(FIELDS_AND_RELATIONSHIPS_TAB));
        tab.click();
    }

    public void ensurePicklistFieldExists(String fieldLabel, List<String> values) {
        if (isFieldPresent(fieldLabel)) {
            return;
        }

        createPicklistField(fieldLabel, values);
    }

    private boolean isFieldPresent(String fieldLabel) {
        By fieldLink = By.xpath(String.format("//table[contains(@class,'slds-table')]//a[text()='%s']", fieldLabel));
        return !driver.findElements(fieldLink).isEmpty();
    }

    private void createPicklistField(String fieldLabel, List<String> values) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.elementToBeClickable(NEW_BUTTON)).click();
        wait.until(ExpectedConditions.elementToBeClickable(PICKLIST_OPTION)).click();
        wait.until(ExpectedConditions.elementToBeClickable(NEXT_BUTTON)).click();

        WebElement fieldLabelInput = wait.until(ExpectedConditions.visibilityOfElementLocated(FIELD_LABEL_INPUT));
        fieldLabelInput.clear();
        fieldLabelInput.sendKeys(fieldLabel);

        WebElement picklistValues = wait.until(ExpectedConditions.visibilityOfElementLocated(PICKLIST_VALUES_TEXTAREA));
        picklistValues.clear();
        picklistValues.sendKeys(String.join(System.lineSeparator(), values));

        wait.until(ExpectedConditions.elementToBeClickable(NEXT_BUTTON)).click();
        wait.until(ExpectedConditions.elementToBeClickable(FIELD_LEVEL_SECURITY_NEXT)).click();
        wait.until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON)).click();
    }
}
