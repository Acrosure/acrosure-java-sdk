package com.acrosure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Premium {
    private double netPremium;
    private double grossPremium;
    private double vat;
    private double duty;

    @JsonCreator
    Premium(
            @JsonProperty("net_premium") double netPremium,
            @JsonProperty("gross_premium") double grossPremium,
            @JsonProperty("vat") double vat,
            @JsonProperty("duty") double duty) {
        this.netPremium = netPremium;
        this.grossPremium = grossPremium;
        this.vat = vat;
        this.duty = duty;
    }

    public double getNetPremium() {
        return netPremium;
    }

    public double getGrossPremium() {
        return grossPremium;
    }

    public double getVat() {
        return vat;
    }

    public double getDuty() {
        return duty;
    }

    @Override
    public String toString() {
        return "Premium{" +
                "netPremium=" + netPremium +
                ", grossPremium=" + grossPremium +
                ", vat=" + vat +
                ", duty=" + duty +
                '}';
    }
}
