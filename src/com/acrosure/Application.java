package com.acrosure;

import org.json.simple.JSONObject;

import java.io.IOException;

public class Application {
    private final String prodId;
    private final String appId;
    private final JSONObject form;
    private String status;

    Application(String prodId, String appId, JSONObject obj, String status) {
        this.prodId = prodId;
        this.appId = appId;
        this.form = obj;
        this.status = status;
    }

    Application(JSONObject obj) {
        this(
                (String) obj.get("product_id"),
                (String) obj.get("id"),
                (JSONObject) obj.get("form_data"),
                (String) obj.get("status"));
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
                ", status='" + status + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    void setStatus(String status) {
        this.status = status;
    }
}
