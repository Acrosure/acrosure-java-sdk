package com.acrosure;

import com.acrosure.form.DataGetForm;
import com.acrosure.resource.Data;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * DataManager handles all the data specific requests.
 * Right now there is only one operation: get.
 */
public class DataManager {
    private final HttpClient httpClient;
    private final String METHOD_GROUP;
    private final ObjectMapper mapper;

    DataManager(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.METHOD_GROUP = "data";
        this.mapper = new ObjectMapper();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setDateFormat(df);
    }

    /**
     * Get data
     *
     * @param form                  an instance of form.DataGetForm
     * @return                      array of instances of resource.Data
     * @throws IOException          if there are some JSON-related operation errors
     * @throws AcrosureException    if the server returns error(s)
     */
    public Data[] get(DataGetForm form) throws IOException, AcrosureException {
        ObjectNode requestPayload = mapper.valueToTree(form);

        ObjectNode responseData = (ObjectNode) httpClient.call(METHOD_GROUP, DataManager.Methods.GET.toString(), requestPayload);

        return mapper.treeToValue(responseData.get("data"), Data[].class);
    }

    private enum Methods {
        GET("get");

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
