package com.acrosure;

import org.json.simple.JSONObject;

public class InsurancePackage {
    private final String insurerPackageCode;
    private final String name;
    private final double amount;
    private final double amountWithTax;
    private final JSONObject raw;

    InsurancePackage(JSONObject object) {
        this.insurerPackageCode = (String) object.get("insurer_package_code");
        this.name = (String) object.get("name");
        this.amount = (Double) object.get("amount");
        this.amountWithTax = (Long) object.get("amount_with_tax");
        this.raw = object;
    }

    @Override
    public String toString() {
        return "InsurancePackage{" +
                "insurerPackageCode='" + insurerPackageCode + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", amountWithTax=" + amountWithTax +
                '}';
    }

    public String getInsurerPackageCode() {
        return insurerPackageCode;
    }

    public double getAmount() {
        return amount;
    }

    public double getAmountWithTax() {
        return amountWithTax;
    }

    public JSONObject raw() {
        return raw;
    }

    public String getName() {
        return name;
    }
}
