package com.acrosure;

import com.acrosure.resource.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class ProductManager {
    private final HttpClient httpClient;
    private final String METHOD_GROUP;
    private final ObjectMapper mapper;

    ProductManager(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.METHOD_GROUP = "products";
        this.mapper = new ObjectMapper();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setDateFormat(df);
    }

    public Product get(String productId) throws IOException, AcrosureException {
        ObjectNode requestPayload = mapper.createObjectNode();

        requestPayload.put("product_id", productId);
        ObjectNode responseData = (ObjectNode) httpClient.call(METHOD_GROUP, Methods.GET.toString(), requestPayload);

        return mapper.treeToValue(responseData.get("data"), Product.class);
    }

    public Product[] list() throws IOException, AcrosureException {
        ObjectNode requestPayload = mapper.createObjectNode();

        ObjectNode responseData = (ObjectNode) httpClient.call(METHOD_GROUP, Methods.LIST.toString(), requestPayload);

        return mapper.treeToValue(responseData.get("data"), Product[].class);
    }

    private enum Methods {
        GET("get"),
        LIST("list");

        private final String method;

        Methods(String method) {
            this.method = method;
        }

        @Override
        public String toString() {
            return method;
        }
    }
}