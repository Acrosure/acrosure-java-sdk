package com.acrosure;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

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
    private static final String MAC_ALGORITHM = "HMACSHA256";
    private Mac mac;
    private final SecretKey key;
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
        key = new SecretKeySpec(token.getBytes(), MAC_ALGORITHM);

        try {
            mac = Mac.getInstance(MAC_ALGORITHM);
            mac.init(key);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            mac = null;
            System.err.println(e.getMessage());
        }
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

    /**
     * Verifies received signature and data against the stored secret token
     *
     * @param signature                 received signature
     * @param data                      received data
     * @return                          <code>true</code> if the signature matches the one derived
     *                                  from the data. Otherwise, <code>false</code>.
     * @throws NoSuchAlgorithmException this shouldn't happen
     */
    public boolean verifySignature(String signature, String data) throws NoSuchAlgorithmException {
        if (mac == null)
            throw new NoSuchAlgorithmException(MAC_ALGORITHM + " couldn't be found");

        byte[] bytesDerivedSignature = mac.doFinal(data.getBytes());
        String derivedSignature = toHexString(bytesDerivedSignature);

        return derivedSignature.equals(signature);
    }

    /**
     * Verifies received signature and data against the stored secret token
     *
     * @param signature                 received signature
     * @param data                      received data
     * @return                          <code>true</code> if the signature matches the one derived
     *                                  from the data. Otherwise, <code>false</code>.
     * @throws NoSuchAlgorithmException this shouldn't happen
     */
    public boolean verifySignature(byte[] signature, String data) throws NoSuchAlgorithmException {
        return verifySignature(new String(signature), data);
    }

    /**
     * Verifies received signature and data against the stored secret token
     *
     * @param signature                 received signature
     * @param data                      received data
     * @return                          <code>true</code> if the signature matches the one derived
     *                                  from the data. Otherwise, <code>false</code>.
     * @throws NoSuchAlgorithmException this shouldn't happen
     */
    public boolean verifySignature(String signature, byte[] data) throws NoSuchAlgorithmException {
        return verifySignature(signature, new String(data));
    }

    /**
     * Verifies received signature and data against the stored secret token
     *
     * @param signature                 received signature
     * @param data                      received data
     * @return                          <code>true</code> if the signature matches the one derived
     *                                  from the data. Otherwise, <code>false</code>.
     * @throws NoSuchAlgorithmException this shouldn't happen
     */
    public boolean verifySignature(byte[] signature, byte[] data) throws NoSuchAlgorithmException {
        return verifySignature(new String(signature), new String(data));
    }

    private static String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();

        for (byte b : bytes) {
            formatter.format("%02x", b);
        }

        return formatter.toString();
    }
}
