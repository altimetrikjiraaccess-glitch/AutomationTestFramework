package com.example.framework.tests;

import com.example.framework.pages.GoogleHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleHomePageTest extends BaseTest {

    @Test(description = "Launches Google home page and verifies the search box is displayed")
    public void verifyGoogleHomePageLoads() {
        GoogleHomePage googleHomePage = new GoogleHomePage();
        googleHomePage.open(baseUrl);
        Assert.assertTrue(googleHomePage.isSearchBoxDisplayed(), "Search box should be displayed on Google home page");
    }
}
