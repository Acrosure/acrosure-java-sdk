package com.acrosure;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

public class AcrosureTest {

    @Test
    public void VerifySignature_StringSignatureAndStringData_CheckIntegrityOfReceivedSignature() {
        Properties prop = new Properties();
        InputStream configStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");

        try {
            prop.load(configStream);
            String token = prop.getProperty("secret_token");
            Acrosure client = new Acrosure(token);

            String receivedSignature = "1b0a6f0c0986671819cd19c38e201719b0531e72dfba312ca121190ea662a97b";
            String data = "{\"data\":\"อโครชัว!\"}";

            assertTrue(client.verifySignature(receivedSignature, data));

        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void VerifySignature_StringSignatureAndBytesData_CheckIntegrityOfReceivedSignature() {
        Properties prop = new Properties();
        InputStream configStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");

        try {
            prop.load(configStream);
            String token = prop.getProperty("secret_token");
            Acrosure client = new Acrosure(token);

            String receivedSignature = "1b0a6f0c0986671819cd19c38e201719b0531e72dfba312ca121190ea662a97b";
            String data = "{\"data\":\"อโครชัว!\"}";


            assertTrue(client.verifySignature(receivedSignature, data.getBytes()));
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void VerifySignature_BytesSignatureAndStringData_CheckIntegrityOfReceivedSignature() {
        Properties prop = new Properties();
        InputStream configStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");

        try {
            prop.load(configStream);
            String token = prop.getProperty("secret_token");
            Acrosure client = new Acrosure(token);

            String receivedSignature = "1b0a6f0c0986671819cd19c38e201719b0531e72dfba312ca121190ea662a97b";
            String data = "{\"data\":\"อโครชัว!\"}";

            assertTrue(client.verifySignature(receivedSignature.getBytes(), data));

        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void VerifySignature_BytesSignatureAndBytesData_CheckIntegrityOfReceivedSignature() {
        Properties prop = new Properties();
        InputStream configStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");

        try {
            prop.load(configStream);
            String token = prop.getProperty("secret_token");
            Acrosure client = new Acrosure(token);

            String receivedSignature = "1b0a6f0c0986671819cd19c38e201719b0531e72dfba312ca121190ea662a97b";
            String data = "{\"data\":\"อโครชัว!\"}";

            assertTrue(client.verifySignature(receivedSignature.getBytes(), data.getBytes()));
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            fail();
        }
    }
}