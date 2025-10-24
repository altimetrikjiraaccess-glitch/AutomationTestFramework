package com.example.framework.tests;

import com.example.framework.config.ConfigLoader;
import com.example.framework.pages.AccountFieldsPage;
import com.example.framework.pages.AccountRecordPage;
import com.example.framework.pages.AccountValidationRulesPage;
import com.example.framework.pages.ObjectManagerPage;
import com.example.framework.pages.SalesforceHomePage;
import com.example.framework.pages.SalesforceLoginPage;
import com.example.framework.pages.SalesforceSetupPage;
import com.example.framework.reporting.JiraTestCase;
import java.util.Arrays;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountCustomerTypeFieldTest extends BaseTest {

    private static final String FIELD_LABEL = "Customer Type";
    private static final String VALIDATION_RULE_NAME = "Require_Customer_Number_For_Customers";
    private static final String VALIDATION_RULE_DESCRIPTION = "Ensures Customer Number is entered when Customer Type is Customer.";
    private static final String VALIDATION_RULE_FORMULA = "AND(ISPICKVAL(Customer_Type__c, \"Customer\"), ISBLANK(Customer_Number__c))";
    private static final String VALIDATION_RULE_MESSAGE = "Customer Number is required when Customer Type is Customer.";

    @Test(description = "Creates Customer Type picklist field and validation rule on Account", groups = {"smoke"}, dependsOnGroups = {"login"})
    @JiraTestCase("SCRUM-1")
    public void createCustomerTypeFieldAndValidationRule() {
        SalesforceLoginPage loginPage = new SalesforceLoginPage();
        loginPage.open(baseUrl);
        loginPage.login(ConfigLoader.getInstance().getSalesforceUsername(), ConfigLoader.getInstance().getSalesforcePassword());

        SalesforceHomePage homePage = new SalesforceHomePage();
        homePage.openSetup();

        SalesforceSetupPage setupPage = new SalesforceSetupPage();
        setupPage.openObjectManager();

        ObjectManagerPage objectManagerPage = new ObjectManagerPage();
        objectManagerPage.openObject("Account");

        AccountFieldsPage accountFieldsPage = new AccountFieldsPage();
        accountFieldsPage.openFieldsAndRelationships();
        accountFieldsPage.ensurePicklistFieldExists(FIELD_LABEL, Arrays.asList("Prospect", "Customer", "Partner"));

        AccountValidationRulesPage validationRulesPage = new AccountValidationRulesPage();
        validationRulesPage.openValidationRules();
        validationRulesPage.ensureValidationRuleExists(
                VALIDATION_RULE_NAME,
                VALIDATION_RULE_DESCRIPTION,
                VALIDATION_RULE_FORMULA,
                VALIDATION_RULE_MESSAGE,
                "Customer Number");

        AccountRecordPage accountRecordPage = new AccountRecordPage();
        accountRecordPage.openNewAccountForm(baseUrl);
        accountRecordPage.setAccountName("Automation Account " + System.currentTimeMillis());
        accountRecordPage.selectCustomerType("Customer");
        accountRecordPage.save();

        Assert.assertTrue(accountRecordPage.isValidationErrorDisplayed(VALIDATION_RULE_MESSAGE),
                "Validation message should appear when Customer Number is missing.");
    }
}
