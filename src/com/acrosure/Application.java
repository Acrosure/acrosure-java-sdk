package com.acrosure;

import org.json.simple.JSONObject;

public class Application {
    private final String productId;
    private final String id;
    private final JSONObject data;
    private String status;

    Application(String productId, String id, JSONObject obj, String status) {
        this.productId = productId;
        this.id = id;
        this.data = obj;
        this.status = status;
    }

    Application(JSONObject obj) {
        this(
                (String) obj.get("product_id"),
                (String) obj.get("id"),
                (JSONObject) obj.get("form_data"),
                (String) obj.get("status"));
    }

    public JSONObject data() {
        return data;
    }

    public String getProductId() {
        return productId;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Application{" +
                "productId='" + productId + '\'' +
                ", id='" + id + '\'' +
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
