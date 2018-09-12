package com.acrosure;

import org.json.JSONObject;

import java.io.IOException;

interface HttpClient {
    Object call(String methodGroup, String method, JSONObject param) throws IOException, AcrosureException;
}