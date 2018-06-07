package com.acrosure;

import org.json.simple.JSONObject;

import java.io.IOException;

public class Application {
    private final Acrosure client;
    private final String prodId;
    private final String appId;
    private final JSONObject form;

    Application(Acrosure client, String prodId, String appId, JSONObject obj) {
        this.client = client;
        this.prodId = prodId;
        this.appId = appId;
        this.form = obj;
    }

    public Application update() throws IOException {
        JSONObject req = new JSONObject(), res;
        int retCode;

        req.put("application_id", appId);
        req.put("form_data", form);
        req.put("ref1", null);
        req.put("ref2", null);
        req.put("ref3", null);

        res = client.call("update", req);
        retCode = (Integer) res.get("httpCode");

        if (retCode != 200)
            throw new RuntimeException(
                    "Status: " + retCode + ", " + res.get("httpMessage") + "\n" + res.get("message"));

        return this;
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
