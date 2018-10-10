import static org.junit.jupiter.api.Assertions.*;

import com.acrosure.Acrosure;
import com.acrosure.AcrosureException;
import com.acrosure.PolicyQuery;
import com.acrosure.resource.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PolicyIntegrationTest {
    private Acrosure client;
    private ObjectMapper mapper;

    @BeforeAll
    void init() {
        client = new Acrosure("tokn_sample_secret", "https://api.phantompage.com");
//        client = new Acrosure("sandbox_tokn_1X9gTzB1R80MAq0F", "http://localhost:8000");
        mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());
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