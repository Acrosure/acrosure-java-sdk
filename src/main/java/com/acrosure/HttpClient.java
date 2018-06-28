package com.acrosure;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class HttpClient implements IHttpClient {
    private final String token;
    private final String HOST;
    private final OkHttpClient httpClient;

    HttpClient(String token) {
        final String domain = "api.phantompage.com";
        final String cert = "sha256/28zFYvyx23W0C+SWKQT8W+2CP/KSRbQ/aeyUxyvocaM=";
        this.token = token;
        HOST = "https://" + domain;
        httpClient = new OkHttpClient.Builder()
                .certificatePinner(
                        new CertificatePinner.Builder()
                                .add(domain, cert)
                                .build())
                .build();
    }

    @Override
    public Object call(String method, String methodGroup, JSONObject param) throws IOException, AcrosureException {
        JSONObject apiResponse;
        Request request = buildHttpRequest(method, methodGroup, param);
        Call call = httpClient.newCall(request);
        Response response = call.execute();
        int statusCode = response.code();
        apiResponse = new JSONObject(response.body().string());


        if (statusCode != 200)
            throw new AcrosureException(apiResponse.getString("message"), statusCode);

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