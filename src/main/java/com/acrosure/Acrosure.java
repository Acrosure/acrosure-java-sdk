package com.acrosure;

/**
 * Acrosure is the class that wraps the capabilities of Acrosure's
 * <a href="https://docs.acrosure.com/#getting-started">web APIs</a>. Each instance
 * of the class holds a specific API token and the host's URL. It also holds a
 * dedicated HTTP connection. Most of the time,
 * you have no need to change the host's URL; by default it will point to
 * <a href="https://api.acrosure.com/">https://api.acrosure.com/</a>. There are two types of
 * API tokens: public and secret tokens. Both can be used exchangeable in most circumstances.
 * Except for the case where you want to confirm an application, in that case,
 * secret tokens must be used.
 *
 * @author Acrosure Developer Team
 */
public class Acrosure {
    private final ApplicationManager applicationManager;
    private final Payment2C2PManager payment2C2PManager;
    private final ProductManager productManager;
    private final PolicyManager policyManager;
    private final TeamManager teamManager;
    private final DataManager dataManager;

    /**
     * Initiates an Acrosure client instance.
     *
     * @param token your API token, can be either public or secret one
     */
    public Acrosure(String token) {
        this(token, "https://api.acrosure.com");
    }

    /**
     * Initiates an Acrosure client instance.
     *
     * @param token your API token, can be either public or secret one
     * @param host  API host that all the requests will be delivered to.
     *              Most of the time, you have no need to change the host's URL;
     *              by default it will point to <a href="https://api.acrosure.com/">https://api.acrosure.com/</a>.
     */
    public Acrosure(String token, String host) {
        OkHttpClient client = new OkHttpClient(token, host);
        applicationManager = new ApplicationManager(client);
        payment2C2PManager = new Payment2C2PManager(client);
        productManager = new ProductManager(client);
        policyManager = new PolicyManager(client);
        teamManager = new TeamManager(client);
        dataManager = new DataManager(client);
    }

    /**
     * @return the instance of ApplicationManager
     */
    public ApplicationManager application() {
        return applicationManager;
    }

    /**
     * @return the instance of Payment2C2PManager
     */
    public Payment2C2PManager payment2C2P() {
        return payment2C2PManager;
    }

    /**
     * @return the instance of ProductManager
     */
    public ProductManager product() {
        return productManager;
    }

    /**
     * @return the instance of PolicyManager
     */
    public PolicyManager policy() {
        return policyManager;
    }

    /**
     * @return the instance of TeamManager
     */
    public TeamManager team() {
        return teamManager;
    }

    /**
     * @return the instance of DataManager
     */
    public DataManager data() {
        return dataManager;
    }
}
