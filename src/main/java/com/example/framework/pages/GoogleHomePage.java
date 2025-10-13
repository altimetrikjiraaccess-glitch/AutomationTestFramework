package com.example.framework.pages;

import com.example.framework.driver.DriverFactory;
import org.openqa.selenium.By;

public class GoogleHomePage extends BasePage {

    private static final By SEARCH_BOX = By.name("q");

    public void open(String baseUrl) {
        DriverFactory.getDriver().get(baseUrl);
    }

    public boolean isSearchBoxDisplayed() {
        return DriverFactory.getDriver().findElement(SEARCH_BOX).isDisplayed();
    }
}
