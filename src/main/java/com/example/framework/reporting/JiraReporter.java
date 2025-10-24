package com.example.framework.reporting;

import com.example.framework.config.ConfigLoader;
import java.lang.reflect.Method;
import org.testng.ITestResult;

public final class JiraReporter {

    private JiraReporter() {
        // Utility class
    }

    public static void reportResult(ITestResult result) {
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        JiraTestCase jiraTestCase = method.getAnnotation(JiraTestCase.class);
        if (jiraTestCase == null) {
            return;
        }

        JiraService jiraService = createJiraService();
        if (!jiraService.isConfigured()) {
            System.out.println("Jira configuration missing. Skipping Jira update for test: " + method.getName());
            return;
        }

        String issueKey = jiraTestCase.value();
        boolean passed = result.getStatus() == ITestResult.SUCCESS;
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Automation execution result: ")
                .append(passed ? "PASS" : "FAIL")
                .append(" for test method '")
                .append(method.getName())
                .append("'.");
        if (result.getThrowable() != null) {
            messageBuilder.append(" Failure Reason: ")
                    .append(result.getThrowable().getMessage());
        }

        jiraService.publishTestResult(issueKey, passed, messageBuilder.toString());
    }

    private static JiraService createJiraService() {
        ConfigLoader configLoader = ConfigLoader.getInstance();
        return new JiraService(
                configLoader.getJiraBaseUrl(),
                configLoader.getJiraUsername(),
                configLoader.getJiraApiToken());
    }
}
