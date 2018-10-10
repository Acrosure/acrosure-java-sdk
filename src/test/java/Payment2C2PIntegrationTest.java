import static org.junit.jupiter.api.Assertions.*;

import com.acrosure.Acrosure;
import com.acrosure.AcrosureException;
import com.acrosure.form.DataGetForm;
import com.acrosure.resource.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Payment2C2PIntegrationTest {
    private Acrosure client;

    @BeforeAll
    void init() {
        client = new Acrosure("tokn_sample_secret", "https://api.phantompage.com");
//        client = new Acrosure("sandbox_tokn_1X9gTzB1R80MAq0F", "http://localhost:8000");
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