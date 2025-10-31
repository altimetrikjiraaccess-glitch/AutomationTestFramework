package com.example.framework.pages;

import com.example.framework.driver.DriverFactory;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

public class SalesforceLoginPage extends BasePage {

    private static final By USERNAME_INPUT = By.id("username");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.id("Login");
    private static final By APP_LAUNCHER_BUTTON = By.cssSelector("button[title='App Launcher']");

    public void open(String baseUrl) {
   //   DriverFactory.getDriver().get(baseUrl);
        DriverFactory.getDriver().get("https://www.google.com/");      
    }

    public void login(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_INPUT));
        usernameField.clear();
        usernameField.sendKeys(username);

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT));
        passwordField.clear();
        passwordField.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON)).click();
    }   

    public void isAppLauncherVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement box = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        box.sendKeys("Codex");
        box.sendKeys(Keys.RETURN);
/*      WebElement appLauncherButton = wait.until(ExpectedConditions.visibilityOfElementLocated(APP_LAUNCHER_BUTTON));
        return appLauncherButton.isDisplayed();  */
    }
}
