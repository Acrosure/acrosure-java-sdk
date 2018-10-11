package com.acrosure;

import com.acrosure.form.ApplicationCreateForm;
import com.acrosure.form.ApplicationQuery;
import com.acrosure.form.ApplicationUpdateForm;
import com.acrosure.resource.Application;
import com.acrosure.resource.ApplicationList;
import com.acrosure.resource.Package;
import com.acrosure.resource.Policy;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * ApplicationManager handles all the application-specific requests.
 * These includes:
 * <ul>
 *     <li>Get an existing application</li>
 *     <li>List all the applications that associated with the token</li>
 *     <li>Create an application</li>
 *     <li>Update an existing application</li>
 *     <li>Get insurance package(s) that is compatible with an application</li>
 *     <li>Select insurance package for an application</li>
 *     <li>Submit an application</li>
 *     <li>Confirm an application</li>
 * </ul>
 */
public class ApplicationManager {
    private final HttpClient httpClient;
    private final String METHOD_GROUP;
    private final ObjectMapper mapper;

    ApplicationManager(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.METHOD_GROUP = "applications";
        this.mapper = new ObjectMapper();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setDateFormat(df);
    }

    /**
     * Get an existing application
     *
     * @param applicationId         the application ID
     * @return                      an application
     * @throws IOException          if there are some JSON-related operation errors
     * @throws AcrosureException    if the server returns error(s)
     */
    public Application get(String applicationId) throws IOException, AcrosureException {
        ObjectNode requestPayload = mapper.createObjectNode();

        requestPayload.put("application_id", applicationId);
        ObjectNode responseData = (ObjectNode) httpClient.call(METHOD_GROUP, Methods.GET.toString(), requestPayload);

        return mapper.treeToValue(responseData.get("data"), Application.class);
    }

    /**
     * List all the applications that associated with the token
     *
     * @param query                 query information
     * @return                      an instance of ApplicationList with
     *                              pagination included
     * @throws IOException          if there are some JSON-related operation errors
     * @throws AcrosureException    if the server returns error(s)
     */
    public ApplicationList list(ApplicationQuery query) throws IOException, AcrosureException {
        ObjectNode requestPayload = mapper.valueToTree(query);

        ObjectNode responseData = (ObjectNode) httpClient.call(METHOD_GROUP, Methods.LIST.toString(), requestPayload);

        return mapper.treeToValue(responseData, ApplicationList.class);
    }

    /**
     * Create an application
     *
     * @param productId             product ID, such as prod_motor etc.
     * @return                      a created application
     * @throws IOException          if there are some JSON-related operation errors
     * @throws AcrosureException    if the server returns error(s)
     */
    public Application create(String productId) throws IOException, AcrosureException {
        ApplicationCreateForm applicationCreateForm = new ApplicationCreateForm();

        applicationCreateForm.setProductId(productId);

        return this.create(applicationCreateForm);
    }

    /**
     * Create an application
     *
     * @param applicationCreateForm an instance of ApplicationCreateForm
     * @return                      a created application
     * @throws IOException          if there are some JSON-related operation errors
     * @throws AcrosureException    if the server returns error(s)
     */
    public Application create(ApplicationCreateForm applicationCreateForm) throws IOException, AcrosureException {
        ObjectNode requestPayload = mapper.valueToTree(applicationCreateForm);
        ObjectNode responseData = (ObjectNode) httpClient.call(METHOD_GROUP, Methods.CREATE.toString(), requestPayload);

        return mapper.treeToValue(responseData.get("data"), Application.class);
    }

    /**
     * Update an existing application
     *
     * @param application           an application got from either #create or #get
     * @return                      the updated application, points to the same instance as the argument
     * @throws IOException          if there are some JSON-related operation errors
     * @throws AcrosureException    if the server returns error(s)
     */
    public Application update(Application application) throws IOException, AcrosureException {
        ApplicationUpdateForm applicationUpdateForm = mapper.convertValue(application, ApplicationUpdateForm.class);
        ObjectNode requestPayload = mapper.valueToTree(applicationUpdateForm);
        ObjectNode responseData = (ObjectNode) httpClient.call(METHOD_GROUP, Methods.UPDATE.toString(), requestPayload);

        Application origin = mapper.treeToValue(responseData.get("data"), Application.class);

        return application.copy(origin);
    }

    /**
     * Get an insurance package the application selected
     *
     * @param application           an instance of resource.Application
     * @return                      an instance of resource.Package
     * @throws IOException          if there are some JSON-related operation errors
     * @throws AcrosureException    if the server returns error(s)
     */
    public Package getPackage(Application application) throws IOException, AcrosureException {
        ObjectNode requestPayload = mapper.createObjectNode();

        requestPayload.put("application_id", application.getId());
        ObjectNode responseData = (ObjectNode) httpClient.call(METHOD_GROUP, Methods.GET_PACKAGE.toString(), requestPayload);

        return mapper.treeToValue(responseData.get("data"), Package.class);
    }

    /**
     * Get insurance packages which are compatible with the application
     *
     * @param application           an instance of resource.Application
     * @return                      array of resource.Package
     * @throws IOException          if there are some JSON-related operation errors
     * @throws AcrosureException    if the server returns error(s)
     */
    public Package[] getPackages(Application application) throws IOException, AcrosureException {
        ObjectNode requestPayload = mapper.createObjectNode();

        requestPayload.put("application_id", application.getId());
        ObjectNode responseData = (ObjectNode) httpClient.call(METHOD_GROUP, Methods.GET_PACKAGES.toString(), requestPayload);

        return mapper.treeToValue(responseData.get("data"), Package[].class);
    }

    /**
     * Select insurance package for an application
     *
     * @param application           an instance of resource.Application
     * @param mPackage              an instance of resource.Package got from #getPackages
     * @return                      the same application with an insurance package associated
     * @throws IOException          if there are some JSON-related operation errors
     * @throws AcrosureException    if the server returns error(s)
     */
    public Application selectPackage(Application application, Package mPackage) throws  IOException, AcrosureException {
        ObjectNode requestPayload = mapper.createObjectNode();

        requestPayload.put("application_id", application.getId());
        requestPayload.put("package_code", mPackage.getPackageCode());
        ObjectNode responseData = (ObjectNode) httpClient.call(METHOD_GROUP, Methods.SELECT_PACKAGE.toString(), requestPayload);

        Application origin = mapper.treeToValue(responseData.get("data"), Application.class);

        return application.copy(origin);
    }

    /**
     * Submit an application
     *
     * @param application           an instance of resource.Application
     * @return                      the submitted application
     * @throws IOException          if there are some JSON-related operation errors
     * @throws AcrosureException    if the server returns error(s)
     */
    public Application submit(Application application) throws IOException, AcrosureException {
        ObjectNode requestPayload = mapper.createObjectNode();

        requestPayload.put("application_id", application.getId());
        ObjectNode responseData = (ObjectNode) httpClient.call(METHOD_GROUP, Methods.SUBMIT.toString(), requestPayload);

        Application origin = mapper.treeToValue(responseData.get("data"), Application.class);

        return application.copy(origin);
    }

    /**
     * Confirm an application
     *
     * @param application           an instance of resource.Application
     * @return                      array of policies associated to the application
     * @throws IOException          if there are some JSON-related operation errors
     * @throws AcrosureException    if the server returns error(s)
     */
    public Policy[] confirm(Application application) throws IOException, AcrosureException {
        ObjectNode requestPayload = (ObjectNode) mapper.readTree("{}");

        requestPayload.put("application_id", application.getId());
        ObjectNode responseData = (ObjectNode) httpClient.call(METHOD_GROUP, Methods.CONFIRM.toString(), requestPayload);

        Application origin = get(application.getId());
        application.copy(origin);

        return mapper.treeToValue(responseData.get("data"), Policy[].class);
    }

    private enum Methods {
        GET("get"),
        LIST("list"),
        GET_PACKAGE("get-package"),
        GET_PACKAGES("get-packages"),
        SELECT_PACKAGE("select-package"),
        CREATE("create"),
        UPDATE("update"),
        SUBMIT("submit"),
        CONFIRM("confirm");

        private final String method;

        Methods(String method) {
            this.method = method;
        }

        @Override
        public String toString() {
            return method;
        }
    }
}