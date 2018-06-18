package com.acrosure;

public class Acrosure {
    private final ApplicationResource applications;

    public Acrosure(String token) {
        HttpClient httpClient = new HttpClient(token);
        applications = new ApplicationResource(httpClient);
    }

    public ApplicationResource applications() {
        return applications;
    }
}
