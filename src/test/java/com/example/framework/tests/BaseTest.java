package com.example.framework.tests;

import com.example.framework.config.ConfigLoader;
import com.example.framework.driver.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected String baseUrl;

    @BeforeMethod
    public void setUp() {
        baseUrl = ConfigLoader.getInstance().getBaseUrl();
        DriverFactory.initDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
