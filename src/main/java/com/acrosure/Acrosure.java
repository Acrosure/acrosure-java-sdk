package com.acrosure;

public class Acrosure {
    private final ApplicationManager applicationManager;

    public Acrosure(String token) {
        this(new OkHttpClient(token));
    }

    public Acrosure(HttpClient httpClient) {
        applicationManager = new ApplicationManager(httpClient);
    }

    public ApplicationManager applications() {
        return applicationManager;
    }
}
