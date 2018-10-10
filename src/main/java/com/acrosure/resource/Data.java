package com.acrosure.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
    private String value;
    private String label;

    @JsonCreator
    public Data(
            @JsonProperty("value") String value,
            @JsonProperty("label") String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Data{" +
                "value='" + value + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
