package com.example.framework.pages;

import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SalesforceHomePage extends BasePage {

    private static final By SETUP_GEAR_BUTTON = By.cssSelector("button[title='Setup']");
    private static final By SETUP_LINK = By.cssSelector("a[title='Setup']");

    public void openSetup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement gearButton = wait.until(ExpectedConditions.elementToBeClickable(SETUP_GEAR_BUTTON));
        gearButton.click();

        WebElement setupLink = wait.until(ExpectedConditions.elementToBeClickable(SETUP_LINK));
        setupLink.click();

        switchToSetupWindow();
    }

    private void switchToSetupWindow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        String originalHandle = driver.getWindowHandle();
        wait.until(d -> driver.getWindowHandles().size() > 1);
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        driver.manage().window().maximize();
    }
}
