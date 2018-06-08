package com.acrosure;

import org.json.simple.JSONObject;

import java.io.IOException;

public class ApplicationResource {
    private final AcrosureCaller caller;
    private final String RESOURCE;

    ApplicationResource(AcrosureCaller caller) {
        this.caller = caller;
        RESOURCE = "applications";
    }

    public Application get(String appId) throws IOException {
        JSONObject req = new JSONObject(), res;
        int retCode;

        req.put("application_id", appId);
        res = caller.call("get", RESOURCE, req);
        retCode = (Integer) res.get("httpCode");

        if (retCode == 200)
            return new Application((JSONObject) res.get("data"));
        else
            throw new RuntimeException(
                    "Status: " + retCode + ", " + res.get("httpMessage") + "\n" + res.get("message"));
    }

    public JSONObject getPackages(String appId) throws IOException {
        JSONObject req = new JSONObject(), res;
        int retCode;

        req.put("application_id", appId);
        res = caller.call("get-packages", RESOURCE, req);
        retCode = (Integer) res.get("httpCode");

        if (retCode == 200) {
            res.remove("httpCode");
            res.remove("httpMessage");
        } else {
            throw new RuntimeException(
                    "Status: " + retCode + ", " + res.get("httpMessage") + "\n" + res.get("message"));
        }

        return res;
    }

    /**@TODO How to handle errors from server? (Not errors from Java itself)
     */
    public Application create(String prodId, JSONObject obj) throws IOException {
        Application app;
        int retCode;
        JSONObject req = new JSONObject(), res;

        req.put("product_id", prodId);
        req.put("form_data", obj);

        res = caller.call("create", RESOURCE, req);
        retCode = (Integer) res.get("httpCode");

        if (retCode == 200)
            app = new Application(prodId, (String) ((JSONObject) res.get("data")).get("id"), obj, "INITIAL");
        else
            throw new RuntimeException(
                    "Status: " + retCode + ", " + res.get("httpMessage") + "\n" + res.get("message"));

        return app;
    }

    public Application update(Application app) throws IOException {
        JSONObject req = new JSONObject(), res;
        int retCode;

        req.put("application_id", app.getAppId());
        req.put("form_data", app.form());

        res = caller.call("update", RESOURCE, req);
        retCode = (Integer) res.get("httpCode");

        if (retCode == 200) {
            app.setStatus((String) ((JSONObject) res.get("data")).get("status"));
        } else {
            throw new RuntimeException(
                    "Status: " + retCode + ", " + res.get("httpMessage") + "\n" + res.get("message"));
        }

        return app;
    }
}