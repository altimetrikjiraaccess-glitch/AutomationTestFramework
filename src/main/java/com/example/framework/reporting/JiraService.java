package com.example.framework.reporting;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class JiraService {

    private final String baseUrl;
    private final String username;
    private final String apiToken;
    private final HttpClient httpClient;

    public JiraService(String baseUrl, String username, String apiToken) {
        this(baseUrl, username, apiToken, HttpClient.newHttpClient());
    }

    JiraService(String baseUrl, String username, String apiToken, HttpClient httpClient) {
        this.baseUrl = baseUrl;
        this.username = username;
        this.apiToken = apiToken;
        this.httpClient = httpClient;
    }

    public boolean isConfigured() {
        return baseUrl != null && !baseUrl.isBlank()
                && username != null && !username.isBlank()
                && apiToken != null && !apiToken.isBlank();
    }

    public void publishTestResult(String issueKey, boolean passed, String message) {
        if (!isConfigured()) {
            return;
        }

        try {
            String commentBody = buildCommentBody(passed, message);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format("%s/rest/api/3/issue/%s/comment", baseUrl, issueKey)))
                    .header("Content-Type", "application/json")
                    .header("Authorization", buildAuthorizationHeader())
                    .POST(HttpRequest.BodyPublishers.ofString(commentBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.printf("Jira update response for %s: %d%n", issueKey, response.statusCode());
        } catch (Exception e) {
            System.err.println("Failed to publish Jira result for issue " + issueKey + ": " + e.getMessage());
        }
    }

    private String buildCommentBody(boolean passed, String message) {
        String escapedMessage = escapeForJson(message);
        return String.format("{\"body\": \"[%s] %s\"}", passed ? "PASS" : "FAIL", escapedMessage);
    }

    private String escapeForJson(String message) {
        return message.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private String buildAuthorizationHeader() {
        String credentials = username + ":" + apiToken;
        String encoded = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        return "Basic " + encoded;
    }
}
