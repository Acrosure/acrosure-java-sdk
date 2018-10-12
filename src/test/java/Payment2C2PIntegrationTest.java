import static org.junit.jupiter.api.Assertions.*;

import com.acrosure.Acrosure;
import com.acrosure.AcrosureException;
import com.acrosure.resource.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Payment2C2PIntegrationTest {
    private Acrosure client;

    @BeforeAll
    void init() {
        Properties prop = new Properties();
        InputStream configStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        try {
            prop.load(configStream);
            String token = prop.getProperty("public_token");
            String host = prop.getProperty("remote_host");
            client = new Acrosure(token, host);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void GetHash_ByDefault_ReturnsPaymentFormData() {
        try {
            PaymentFormData formData = client.payment2C2P().getHash(
                    "sandbox_appl_eq6M2LBfm8n1nV2d",
                    "example.com");

            System.out.println(formData);

            assertNotNull(formData);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        } catch (AcrosureException e) {
            System.out.println(e.getMessage() + ", " + e.getStatusCode());
            e.printStackTrace();
            fail();
        }
    }
}