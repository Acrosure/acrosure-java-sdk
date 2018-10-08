package com.acrosure;

public class Acrosure {
    private final ApplicationManager applicationManager;

    public Acrosure(String token) {
        this(token, "https://api.acrosure.com");
    }

    public Acrosure(String token, String host) {
        applicationManager = new ApplicationManager(new OkHttpClient(token, host));
    }

    public ApplicationManager application() {
        return applicationManager;
    }
}
