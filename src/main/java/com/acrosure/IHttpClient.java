package com.acrosure;

import org.json.JSONObject;

import java.io.IOException;

interface IHttpClient {
    Object call(String method, String methodGroup, JSONObject param) throws IOException, AcrosureException;
}