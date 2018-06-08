package com.acrosure;

import org.json.simple.JSONObject;

import java.io.IOException;

public class Acrosure {
    private final ApplicationResource applications;
    private final ProductResource products;

    public Acrosure(String token, String secret) {
        AcrosureCaller caller = new AcrosureCaller(token, secret);
        applications = new ApplicationResource(caller);
        products = new ProductResource(caller);
    }

    public ApplicationResource applications() {
        return applications;
    }

    public ProductResource products() {
        return products;
    }
}
