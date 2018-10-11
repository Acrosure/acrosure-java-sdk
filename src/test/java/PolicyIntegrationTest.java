import static org.junit.jupiter.api.Assertions.*;

import com.acrosure.Acrosure;
import com.acrosure.AcrosureException;
import com.acrosure.form.PolicyQuery;
import com.acrosure.resource.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PolicyIntegrationTest {
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

    @Test
    void Get_ByDefault_ReturnsPolicy() {
        try {
            Policy policy = client.policy().get("sandbox_plcy_IZ8dkDQWtILPUr72");

            assertEquals(policy.getId(), "sandbox_plcy_IZ8dkDQWtILPUr72");
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
            PolicyQuery query = new PolicyQuery();
            PolicyList policyList = client.policy().list(query);

            System.out.println(policyList.getPagination().getTotal());

            assertTrue(policyList.getData().length > 0);
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