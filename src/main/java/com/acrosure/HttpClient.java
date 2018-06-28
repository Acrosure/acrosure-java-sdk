package com.acrosure;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import okhttp3.MediaType;
import org.json.JSONObject;

import java.io.IOException;

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