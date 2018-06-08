package com.acrosure;

import org.json.simple.JSONObject;

import java.io.IOException;

public class Application {
    private final String prodId;
    private final String appId;
    private final JSONObject form;

    Application(String prodId, String appId, JSONObject obj) {
        this.prodId = prodId;
        this.appId = appId;
        this.form = obj;
    }

    public JSONObject form() {
        return form;
    }

    public String getProdId() {
        return prodId;
    }

    public String getAppId() {
        return appId;
    }

    @Override
    public String toString() {
        return "Application{" +
                "prodId='" + prodId + '\'' +
                ", appId='" + appId + '\'' +
                '}';
    }
}
