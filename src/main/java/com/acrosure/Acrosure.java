package com.acrosure;

public class Acrosure {
    private final ApplicationManager applications;

    public Acrosure(String token) {
        IHttpClient httpClient = new HttpClient(token);
        applications = new ApplicationManager(httpClient);
    }

    public ApplicationManager applications() {
        return applications;
    }
}
