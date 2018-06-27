package com.acrosure;

import okhttp3.*;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpClient implements IHttpClient {
    private final String token;
    private final String HOST;
    private final OkHttpClient httpClient;

    HttpClient(String token) {
        this.token = token;
        httpClient = new OkHttpClient.Builder().build();
        HOST = "https://api.phantompage.com";
    }

    @Override
    public Object call(String method, String methodGroup, JSONObject param) throws IOException, AcrosureException {
        JSONObject apiResponse;
        Request request = buildHttpRequest(method, methodGroup, param);
        Call call = httpClient.newCall(request);
        Response response = call.execute();
        int statusCode = response.code();
        apiResponse = new JSONObject(response.body().string());


        if (statusCode != 200) {
            if (apiResponse == null)
                throw new AcrosureException(response.message(), statusCode);
            else
                throw new AcrosureException(apiResponse.getString("message"), statusCode);
        }

        return apiResponse.get("data");
    }

    private Request buildHttpRequest(String method, String methodGroup, JSONObject param) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), param.toString());

        return new Request.Builder()
                .url(HOST + "/" + methodGroup + "/" + method)
                .addHeader("Authorization", "Bearer " + this.token)
                .post(body).build();
    }
}