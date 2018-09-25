package com.acrosure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import okhttp3.MediaType;
import java.io.IOException;
import java.io.InputStream;

public class OkHttpClient implements HttpClient {
    private final String TOKEN;
    private final String HOST;
    private final okhttp3.OkHttpClient httpClient;
    private final ObjectMapper mapper;

    OkHttpClient(String token) {
        this(token, "https://api.phantompage.com");
    }

    OkHttpClient(String token, String host) {
        this.TOKEN = token;
        this.HOST = host;
        this.httpClient = new okhttp3.OkHttpClient.Builder().build();
        this.mapper = new ObjectMapper();
    }

    @Override
    public JsonNode call(String methodGroup, String method, ObjectNode param) throws IOException, AcrosureException {
        Request request = buildHttpRequest(methodGroup, method, param);
        Call call = httpClient.newCall(request);
        Response response = call.execute();
        int statusCode = response.code();
        ObjectNode jsonTreeResponse = (ObjectNode) mapper.readTree(response.body().byteStream());


        if (statusCode == 500)
            throw new AcrosureException("Server failed on executing the request", statusCode);
        if (statusCode != 200 || !jsonTreeResponse.get("status").asText().equals("ok"))
            throw new AcrosureException(jsonTreeResponse.get("message").asText(), statusCode);

        return jsonTreeResponse.get("data");
    }

    private Request buildHttpRequest(
            String methodGroup,
            String method,
            ObjectNode param) throws JsonProcessingException {
        String body = mapper.writeValueAsString(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body);

        return new Request.Builder()
                .url(HOST + "/" + methodGroup + "/" + method)
                .addHeader("Authorization", "Bearer " + this.TOKEN)
                .post(requestBody).build();
    }
}