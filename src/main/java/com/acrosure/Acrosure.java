package com.acrosure;

public class Acrosure {
    private final ApplicationManager applications;

    public Acrosure(String token) {
        HttpClient httpClient = new OkHttpClient(token);
        applications = new ApplicationManager(httpClient);
    }

    public ApplicationManager applications() {
        return applications;
    }
}
