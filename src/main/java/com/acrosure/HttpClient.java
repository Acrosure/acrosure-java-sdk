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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpClient implements IHttpClient {
    private final String token;
    private final String HOST;
    private final CloseableHttpClient httpClient;

    HttpClient(String token) {
        this.token = token;
        httpClient = HttpClients.createDefault();
        HOST = "https://api.phantompage.com";
    }

    @Override
    public Object call(String method, String methodGroup, JSONObject param) throws IOException, AcrosureException {
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
                if (apiResponse == null)
                    throw new AcrosureException(statusLine.getReasonPhrase(), statusCode);
                else
                    throw new AcrosureException((String) apiResponse.getString("message"), statusCode);
            }
        }

        return apiResponse.get("data");
    }

    private HttpRequestBase buildHttpRequest(String method, String methodGroup, JSONObject param) {
        StringEntity entity = new StringEntity(
                param.toString(),
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

        JSONObject jsonObject = new JSONObject(jsonString.toString());

        return jsonObject;
    }

    private void printContent(BufferedReader content) throws IOException {
        while (content.ready())
            System.out.print(content.readLine());

        System.out.println();
    }
}