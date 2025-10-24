package com.example.framework.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SalesforceSetupPage extends BasePage {

    private static final By OBJECT_MANAGER_TAB = By.cssSelector("a[title='Object Manager']");

    public void openObjectManager() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement objectManagerTab = wait.until(ExpectedConditions.elementToBeClickable(OBJECT_MANAGER_TAB));
        objectManagerTab.click();
    }
}
