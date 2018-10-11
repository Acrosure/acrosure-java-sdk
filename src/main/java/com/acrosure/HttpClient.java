package com.acrosure;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

/**
 * HttpClient defines the interface that any HTTP clients need to implement.
 * In order the replace the current client, users can create their own by implementing
 * this interface.
 */
interface HttpClient {
    /**
     * Makes an HTTP request to the server
     *
     * @param methodGroup           the method group
     * @param method                the method
     * @param param                 the request body resides in POST message
     * @return                      an instance of JsonNode
     * @throws IOException          if there are some JSON-related operation errors
     * @throws AcrosureException    if the server returns error(s)
     */
    JsonNode call(String methodGroup, String method, ObjectNode param) throws IOException, AcrosureException;
}