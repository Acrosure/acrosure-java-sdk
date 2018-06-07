package com.acrosure;

import org.json.simple.JSONObject;

import java.io.IOException;

public class ApplicationResource {
    private Acrosure client;

    ApplicationResource(Acrosure client) {
        this.client = client;
    }

    /**@TODO What if the application is already created!?
     * @TODO How to handle errors from server? (Not errors from Java itself)
     */
    public Application create(String prodId, JSONObject obj) throws IOException {
        Application app;
        int retCode;
        JSONObject req = new JSONObject(), res;

        req.put("product_id", prodId);
        req.put("form_data", obj);
        req.put("ref1", null);
        req.put("ref2", null);
        req.put("ref3", null);

        res = client.call("create", req);
        retCode = (Integer) res.get("httpCode");

        if (retCode == 200)
            app = new Application(this.client, prodId, (String) ((JSONObject) res.get("data")).get("id"), obj);
        else
            throw new RuntimeException(
                    "Status: " + retCode + ", " + res.get("httpMessage") + "\n" + res.get("message"));

        return app;
    }
}