package com.acrosure;

import org.json.simple.JSONObject;

public class Application {
    private final String productId;
    private final String id;
    private final JSONObject data;
    private ApplicationStatus status;
    private InsurancePackage insurancePackage;

    Application(String productId, String id, JSONObject obj, ApplicationStatus status) {
        this.productId = productId;
        this.id = id;
        this.data = obj;
        this.status = status;
        insurancePackage = null;
    }

    Application(String productId, String id, JSONObject obj) {
        this(productId, id, obj, ApplicationStatus.INITIAL);
    }

    Application(JSONObject obj) {
        this(
                (String) obj.get("product_id"),
                (String) obj.get("id"),
                (JSONObject) obj.get("form_data"),
                ApplicationStatus.valueOf((String) obj.get("status")));
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

    public ApplicationStatus getStatus() {
        return status;
    }

    void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public void setPackage(InsurancePackage insurancePackage) {
        this.insurancePackage = insurancePackage;
    }

    public InsurancePackage getInsurancePackage() {
        return insurancePackage;
    }

    @Override
    public String toString() {
        return "Application{" +
                "productId='" + productId + '\'' +
                ", id='" + id + '\'' +
                ", status=" + status +
                ", insurancePackage=" + insurancePackage +
                '}';
    }
}
