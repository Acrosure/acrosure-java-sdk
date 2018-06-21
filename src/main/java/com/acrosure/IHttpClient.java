package com.acrosure;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import java.io.IOException;

interface IHttpClient {
    JSONAware call(String method, String methodGroup, JSONObject param) throws IOException, AcrosureException;
}