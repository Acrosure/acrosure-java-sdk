package com.acrosure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Condition<V> {
    private String condition;
    private V value;

    @JsonCreator
    public Condition(
            @JsonProperty("condition") String condition,
            @JsonProperty("value") V value) {
        this.condition = condition;
        this.value = value;
    }

    public String getCondition() {
        return condition;
    }

    public V getValue() {
        return value;
    }
}
