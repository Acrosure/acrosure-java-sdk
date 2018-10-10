package com.acrosure.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class PolicyList {
    private Policy[] data;
    private Pagination pagination;

    @JsonCreator
    public PolicyList(
            @JsonProperty("data") Policy[] data,
            @JsonProperty("pagination") Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public Policy[] getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    @Override
    public String toString() {
        return "ApplicationList{" +
                "data=" + Arrays.toString(data) +
                ", pagination=" + pagination +
                '}';
    }
}
