package com.example.framework.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ObjectManagerPage extends BasePage {

    private static final By SEARCH_INPUT = By.cssSelector("input[placeholder='Search Objects...']");

    public void openObject(String objectName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
        searchInput.clear();
        searchInput.sendKeys(objectName);

        By objectLink = By.xpath(String.format("//table[contains(@class,'slds-table')]//a[@title='%s']", objectName));
        WebElement objectRow = wait.until(ExpectedConditions.elementToBeClickable(objectLink));
        objectRow.click();
    }
}
