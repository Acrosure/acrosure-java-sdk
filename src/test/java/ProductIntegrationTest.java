import static org.junit.jupiter.api.Assertions.*;

import com.acrosure.Acrosure;
import com.acrosure.AcrosureException;
import com.acrosure.resource.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductIntegrationTest {
    private Acrosure client;

    @BeforeAll
    void init() {
        Properties prop = new Properties();
        InputStream configStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        try {
            prop.load(configStream);
            String token = prop.getProperty("secret_token");
            String host = prop.getProperty("remote_host");
            client = new Acrosure(token, host);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = { "prod_ta", "prod_ta_domestic", "prod_motor", "prod_fire", "prod_contractor" })
    void Get_ByDefault_ReturnsProduct(String id) {
        try {
            Product product = client.product().get(id);

            assertEquals(product.getId(), id);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        } catch (AcrosureException e) {
            System.out.println(e.getMessage() + ", " + e.getStatusCode());
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void List_ByDefault_ReturnsProducts() {
        try {
            Product[] products = client.product().list();

            System.out.println(products.length);

            assertTrue(products.length > 0);
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