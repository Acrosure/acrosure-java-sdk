package com.acrosure.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Product {
    private String id;
    private String name;
    private String type;
    private String insurerProductCode;
    private String handlerId;
    private ArrayNode formItems;
    private ObjectNode sampleFormData;
    private String language;
    private ObjectNode handlerOptions;
    private String completeProcess;
    private boolean isFormAvailable;
    private ObjectNode config;

    @JsonCreator
    public Product(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("type") String type,
            @JsonProperty("insurer_product_code") String insurerProductCode,
            @JsonProperty("handler_id") String handlerId,
            @JsonProperty("form_items") ArrayNode formItems,
            @JsonProperty("sample_form_data") ObjectNode sampleFormData,
            @JsonProperty("language") String language,
            @JsonProperty("handler_option") ObjectNode handlerOptions,
            @JsonProperty("complete_process") String completeProcess,
            @JsonProperty("is_form_available") boolean isFormAvailable,
            @JsonProperty("config") ObjectNode config) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.insurerProductCode = insurerProductCode;
        this.handlerId = handlerId;
        this.formItems = formItems;
        this.sampleFormData = sampleFormData;
        this.language = language;
        this.handlerOptions = handlerOptions;
        this.completeProcess = completeProcess;
        this.isFormAvailable = isFormAvailable;
        this.config = config;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getInsurerProductCode() {
        return insurerProductCode;
    }

    public String getHandlerId() {
        return handlerId;
    }

    public ArrayNode getFormItems() {
        return formItems;
    }

    public ObjectNode getSampleFormData() {
        return sampleFormData;
    }

    public String getLanguage() {
        return language;
    }

    public ObjectNode getHandlerOptions() {
        return handlerOptions;
    }

    public String getCompleteProcess() {
        return completeProcess;
    }

    public boolean isFormAvailable() {
        return isFormAvailable;
    }

    public ObjectNode getConfig() {
        return config;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", insurerProductCode='" + insurerProductCode + '\'' +
                ", handlerId='" + handlerId + '\'' +
                ", formItems=" + formItems +
                ", sampleFormData=" + sampleFormData +
                ", language='" + language + '\'' +
                ", handlerOptions=" + handlerOptions +
                ", completeProcess='" + completeProcess + '\'' +
                ", isFormAvailable=" + isFormAvailable +
                ", config=" + config +
                '}';
    }
}
