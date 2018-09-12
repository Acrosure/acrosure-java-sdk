package com.acrosure;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import okhttp3.MediaType;
import org.json.JSONObject;

import java.io.IOException;

public class OkHttpClient implements HttpClient {
    private final String TOKEN;
    private final String HOST;
    private final okhttp3.OkHttpClient httpClient;

    OkHttpClient(String token) {
        this(token, "https://api.phantompage.com");
    }

    OkHttpClient(String token, String host) {
        this.TOKEN = token;
        this.HOST = host;
        httpClient = new okhttp3.OkHttpClient.Builder().build();
    }

    @Override
    public Object call(String methodGroup, String method, JSONObject param) throws IOException, AcrosureException {
        JSONObject apiResponse;
        Request request = buildHttpRequest(methodGroup, method, param);
        Call call = httpClient.newCall(request);
        Response response = call.execute();
        int statusCode = response.code();
        apiResponse = new JSONObject(response.body().string());


        if (statusCode != 200)
            throw new AcrosureException(apiResponse.getString("message"), statusCode);

        return apiResponse.get("data");
    }

    private Request buildHttpRequest(String methodGroup, String method, JSONObject param) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), param.toString());

        return new Request.Builder()
                .url(HOST + "/" + methodGroup + "/" + method)
                .addHeader("Authorization", "Bearer " + this.TOKEN)
                .post(body).build();
    }
}