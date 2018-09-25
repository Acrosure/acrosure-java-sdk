package com.acrosure;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

interface HttpClient {
    JsonNode call(String methodGroup, String method, ObjectNode param) throws IOException, AcrosureException;
}