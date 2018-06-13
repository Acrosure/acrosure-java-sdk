package com.acrosure;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpClient {
    private final String token;
    private final String HOST;
    private final CloseableHttpClient httpClient;

    HttpClient(String token) {
        this.token = token;
        httpClient = HttpClients.createDefault();
        HOST = "https://api.phantompage.com";
    }

    JSONAware call(String method, String methodGroup, JSONObject param) throws IOException, AcrosureException {
        JSONObject apiResponse;
        HttpRequestBase httpRequest = buildHttpRequest(method, methodGroup, param);

        try (CloseableHttpResponse httpResponse = httpClient.execute(httpRequest)) {
            int statusCode;
            StatusLine statusLine = httpResponse.getStatusLine();
            HttpEntity recEntity = httpResponse.getEntity();
            BufferedReader content = new BufferedReader(new InputStreamReader(
                    recEntity.getContent(),
                    StandardCharsets.UTF_8));

            apiResponse = parseJson(content);
            statusCode = statusLine.getStatusCode();
            EntityUtils.consume(recEntity);

            if (statusCode != 200) {
                throw new AcrosureException((String) apiResponse.get("message"), statusCode);
            }
        }

        return (JSONAware) apiResponse.get("data");
    }

    private HttpRequestBase buildHttpRequest(String method, String methodGroup, JSONObject param) {
        StringEntity entity = new StringEntity(
                param.toJSONString(),
                ContentType.create("application/json", "UTF-8"));
        HttpPost httpPost = new HttpPost(HOST + "/" + methodGroup + "/" + method);

        httpPost.addHeader("Authorization", "Bearer " + this.token);
        httpPost.setEntity(entity);

        return httpPost;
    }

    private JSONObject parseJson(BufferedReader content) throws IOException {
        StringBuilder jsonString = new StringBuilder("");

        while (content.ready())
            jsonString.append(content.readLine());

        JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonString.toString());

        return jsonObject;
    }

    private void printContent(BufferedReader content) throws IOException {
        while (content.ready())
            System.out.print(content.readLine());

        System.out.println();
    }
}