package com.acrosure;

public class Acrosure {
    private final ApplicationManager applicationManager;
    private final Payment2C2PManager payment2C2PManager;
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
        payment2C2PManager = new Payment2C2PManager(client);
        productManager = new ProductManager(client);
        policyManager = new PolicyManager(client);
        teamManager = new TeamManager(client);
        dataManager = new DataManager(client);
    }

    public ApplicationManager application() {
        return applicationManager;
    }

    public Payment2C2PManager payment2C2P() {
        return payment2C2PManager;
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
