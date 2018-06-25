package com.acrosure;

import org.json.JSONObject;

public class InsurancePackage {
    private final String insurerPackageCode;
    private final String name;
    private final double amount;
    private final double amountWithTax;
    private final double amountPerUnit;
    private final double amountWithTaxPerUnit;
    private final JSONObject raw;

    InsurancePackage(
            String insurerPackageCode,
            String name,
            double amount,
            double amountWithTax,
            double amountPerUnit,
            double amountWithTaxPerUnit,
            JSONObject raw) {
        this.insurerPackageCode = insurerPackageCode;
        this.name = name;
        this.amount = amount;
        this.amountWithTax = amountWithTax;
        this.amountPerUnit = amountPerUnit;
        this.amountWithTaxPerUnit = amountWithTaxPerUnit;
        this.raw = raw;
    }

    static InsurancePackage parseJson(JSONObject jsonObject) throws AcrosureException {
        try {
            return new InsurancePackage(
                    jsonObject.getString(Fields.INSURER_PACKAGE_CODE.toString()),
                    jsonObject.getString(Fields.NAME.toString()),
                    jsonObject.getDouble(Application.Fields.AMOUNT.toString()),
                    jsonObject.getDouble(Application.Fields.AMOUNT_WITH_TAX.toString()),
                    jsonObject.getDouble(Fields.AMOUNT_PER_UNIT.toString()),
                    jsonObject.getDouble(Fields.AMOUNT_WITH_TAX_PER_UNIT.toString()),
                    jsonObject);
        } catch (NullPointerException e) {
            throw new AcrosureException("Malformed responded JSON", 1);
        }
    }

    @Override
    public String toString() {
        return "InsurancePackage{" +
                "insurerPackageCode='" + insurerPackageCode + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", amountWithTax=" + amountWithTax +
                ", amountPerUnit=" + amountPerUnit +
                ", amountWithTaxPerUnit=" + amountWithTaxPerUnit +
                '}';
    }

    public String insurerPackageCode() {
        return insurerPackageCode;
    }

    public String name() {
        return name;
    }

    public double amount() {
        return amount;
    }

    public double amountWithTax() {
        return amountWithTax;
    }

    public double amountPerUnit() {
        return amountPerUnit;
    }

    public double amountWithTaxPerUnit() {
        return amountWithTaxPerUnit;
    }

    public JSONObject raw() {
        return raw;
    }

    public enum Fields {
        INSURER_PACKAGE_CODE("insurer_package_code"),
        NAME("name"),
        AMOUNT("amount"),
        AMOUNT_WITH_TAX("amount_with_tax"),
        AMOUNT_PER_UNIT("amount_per_unit"),
        AMOUNT_WITH_TAX_PER_UNIT("amount_with_tax_per_unit");

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
