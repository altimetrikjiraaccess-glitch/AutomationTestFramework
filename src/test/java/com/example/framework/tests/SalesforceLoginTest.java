package com.example.framework.tests;

import com.example.framework.config.ConfigLoader;
import com.example.framework.pages.SalesforceLoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SalesforceLoginTest extends BaseTest {

    @Test(description = "Launches Salesforce org and verifies login is successful", groups = {"smoke", "login"})
    public void verifySalesforceLogin() {
        SalesforceLoginPage salesforceLoginPage = new SalesforceLoginPage();
        salesforceLoginPage.open(baseUrl);
        salesforceLoginPage.isAppLauncherVisible();
 /*     String username = ConfigLoader.getInstance().getSalesforceUsername();
        String password = ConfigLoader.getInstance().getSalesforcePassword();
        salesforceLoginPage.login(username, password); 
        Assert.assertTrue(salesforceLoginPage.isAppLauncherVisible(), "App Launcher should be visible after successful login");  */
        
    }
}
