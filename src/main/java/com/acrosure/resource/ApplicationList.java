package com.acrosure.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class ApplicationList {
    private Application[] data;
    private Pagination pagination;

    @JsonCreator
    public ApplicationList(
            @JsonProperty("data") Application[] data,
            @JsonProperty("pagination") Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public Application[] getData() {
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
