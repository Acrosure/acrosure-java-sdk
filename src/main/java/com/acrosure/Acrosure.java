package com.acrosure;

public class Acrosure {
    private final ApplicationManager applicationManager;
    private final ProductManager productManager;

    public Acrosure(String token) {
        this(token, "https://api.acrosure.com");
    }

    public Acrosure(String token, String host) {
        OkHttpClient client = new OkHttpClient(token, host);
        applicationManager = new ApplicationManager(client);
        productManager = new ProductManager(client);
    }

    public ApplicationManager application() {
        return applicationManager;
    }

    public ProductManager product() {
        return productManager;
    }
}
