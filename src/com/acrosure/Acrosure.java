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
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Acrosure {
    private final String token;
    private final String secret;
    private final CloseableHttpClient httpClient;
    private final ApplicationResource applications;

    public Acrosure(String token, String secret) {
        this.token = token;
        this.secret = secret;
        this.applications = new ApplicationResource(this);
        this.httpClient = HttpClients.createDefault();
    }

    public ApplicationResource applications() {
        return applications;
    }

    /**@TODO validate op string
     */
    JSONObject call(String op, JSONObject param) throws IOException {
        JSONObject res;
        HttpRequestBase req = initHttpRequest(op, param);

        try (CloseableHttpResponse response = httpClient.execute(req)) {
            StatusLine sl = response.getStatusLine();
            HttpEntity recEntity = response.getEntity();
            BufferedReader content = new BufferedReader(new InputStreamReader(
                    recEntity.getContent(),
                    StandardCharsets.UTF_8));

            res = buildJson(sl, content);
            EntityUtils.consume(recEntity);
        }

        return res;
    }

    /**@TODO Is it gonna be POST for all of the requests?
     */
    private HttpRequestBase initHttpRequest(String op, JSONObject param) {
        StringEntity entity = new StringEntity(
                param.toJSONString(),
                ContentType.create("application/json", "UTF-8"));
        HttpPost httpPost = new HttpPost("https://api.phantompage.com/web/applications/" + op);

        httpPost.addHeader("Authorization", "Bearer " + this.token);
        httpPost.addHeader("Content-Type", "application/json");

        httpPost.setEntity(entity);

        return httpPost;
    }

    private JSONObject buildJson(StatusLine sl, BufferedReader content) throws IOException {
        StringBuilder json = new StringBuilder("");

        while (content.ready())
            json.append(content.readLine());

        JSONObject obj = (JSONObject) JSONValue.parse(json.toString());

        obj.put("httpCode", sl.getStatusCode());
        obj.put("httpMessage", sl.getReasonPhrase());

        return obj;
    }

    private void printContent(BufferedReader c) throws IOException {
        while (c.ready())
            System.out.print(c.readLine());

        System.out.println();
    }
}
