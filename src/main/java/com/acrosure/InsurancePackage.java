package com.acrosure;

import org.json.simple.JSONObject;

public class InsurancePackage {
    private final String insurerPackageCode;
    private final String name;
    private final double amount;
    private final double amountWithTax;
    private final JSONObject raw;

    InsurancePackage(String insurerPackageCode, String name, double amount, double amountWithTax, JSONObject raw) {
        this.insurerPackageCode = insurerPackageCode;
        this.name = name;
        this.amount = amount;
        this.amountWithTax = amountWithTax;
        this.raw = raw;
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

    public enum Fields {
        INSURER_PACKAGE_CODE("insurer_package_code"),
        NAME("name"),
        AMOUNT("amount"),
        AMOUNT_WITH_TAX("amount_with_tax");

        private final String field;

        Fields(String field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return field;
        }
    }
}
