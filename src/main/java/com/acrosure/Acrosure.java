package com.acrosure;

public class Acrosure {
    private final ApplicationManager applicationManager;
    private final ProductManager productManager;
    private final PolicyManager policyManager;
    private final TeamManager teamManager;
    private final DataManager dataManager;

    public Acrosure(String token) {
        this(token, "https://api.acrosure.com");
    }

    public Acrosure(String token, String host) {
        OkHttpClient client = new OkHttpClient(token, host);
        applicationManager = new ApplicationManager(client);
        productManager = new ProductManager(client);
        policyManager = new PolicyManager(client);
        teamManager = new TeamManager(client);
        dataManager = new DataManager(client);
    }

    public ApplicationManager application() {
        return applicationManager;
    }

    public ProductManager product() {
        return productManager;
    }

    public PolicyManager policy() {
        return policyManager;
    }

    public TeamManager team() {
        return teamManager;
    }

    public DataManager data() {
        return dataManager;
    }
}
