package com.example.framework.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRecordPage extends BasePage {

    private static final By SAVE_BUTTON = By.cssSelector("button[title='Save']");
    private static final By ACCOUNT_NAME_INPUT = By.cssSelector("input[name='Name']");
    private static final By CUSTOMER_TYPE_COMBOBOX = By.cssSelector("input[aria-label='Customer Type']");
    private static final String CUSTOMER_TYPE_OPTION_XPATH = "//lightning-base-combobox-item//span[@class='slds-truncate' and text()='%s']";
    private static final By ERROR_MESSAGE = By.xpath("//div[contains(@class,'field-level-errors')]//span[contains(@class,'form-error')]");

    public void openNewAccountForm(String baseUrl) {
        driver.get(baseUrl + "/lightning/o/Account/new");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ACCOUNT_NAME_INPUT));
    }

    public void setAccountName(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement accountName = wait.until(ExpectedConditions.elementToBeClickable(ACCOUNT_NAME_INPUT));
        accountName.clear();
        accountName.sendKeys(name);
    }

    public void selectCustomerType(String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement comboBox = wait.until(ExpectedConditions.elementToBeClickable(CUSTOMER_TYPE_COMBOBOX));
        comboBox.click();
        By option = By.xpath(String.format(CUSTOMER_TYPE_OPTION_XPATH, value));
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public void save() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON)).click();
    }

    public boolean isValidationErrorDisplayed(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE));
        return errorElement.getText().contains(expectedMessage);
    }
}
