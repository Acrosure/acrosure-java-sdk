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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductIntegrationTest {
    private Acrosure client;

    @BeforeAll
    void init() {
        client = new Acrosure("tokn_sample_secret", "https://api.phantompage.com");
//        client = new Acrosure("sandbox_tokn_1X9gTzB1R80MAq0F", "http://localhost:8000");
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