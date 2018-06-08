package com.acrosure;

import org.json.simple.JSONObject;

import java.io.IOException;

public class ProductResource {
    private final AcrosureCaller caller;
    private final String RESOURCE;

    public ProductResource(AcrosureCaller caller) {
        this.caller = caller;
        RESOURCE = "products";
    }

    public JSONObject get(String prodId) throws IOException {
        JSONObject req = new JSONObject(), res;
        int retCode;

        req.put("product_id", prodId);
        res = caller.call("get", RESOURCE, req);
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
}
