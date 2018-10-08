package com.acrosure.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Pagination {
    private long total;
    private long offset;
    private long limit;

    @JsonCreator
    public Pagination(
            @JsonProperty("total") long total,
            @JsonProperty("offset") long offset,
            @JsonProperty("limit") long limit) {
        this.total = total;
        this.offset = offset;
        this.limit = limit;
    }

    public long getTotal() {
        return total;
    }

    public long getOffset() {
        return offset;
    }

    public long getLimit() {
        return limit;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "total=" + total +
                ", offset=" + offset +
                ", limit=" + limit +
                '}';
    }
}
