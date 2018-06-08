package com.acrosure;

import org.json.simple.JSONObject;

import java.io.IOException;

public class Acrosure {
    private final ApplicationResource applications;
    private final AcrosureCaller caller;

    public Acrosure(String token, String secret) {
        caller = new AcrosureCaller(token, secret);
        this.applications = new ApplicationResource(caller);
    }

    public ApplicationResource applications() {
        return applications;
    }

//    public JSONObject get(String prodId) throws IOException {
//        JSONObject req = new JSONObject(), res;
//        int retCode;
//
//        req.put("product_id", prodId);
//        res = caller.call("get", req);
//        retCode = (Integer) res.get("httpCode");
//
//        if (retCode == 200) {
//            res.remove("httpCode");
//            res.remove("httpMessage");
//        } else {
//            throw new RuntimeException(
//                    "Status: " + retCode + ", " + res.get("httpMessage") + "\n" + res.get("message"));
//        }
//
//        return null;
//    }
}
