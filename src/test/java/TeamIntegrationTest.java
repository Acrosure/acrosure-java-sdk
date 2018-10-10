import static org.junit.jupiter.api.Assertions.*;

import com.acrosure.Acrosure;
import com.acrosure.AcrosureException;
import com.acrosure.resource.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TeamIntegrationTest {
    private Acrosure client;

    @BeforeAll
    void init() {
        client = new Acrosure("tokn_sample_secret", "https://api.phantompage.com");
//        client = new Acrosure("sandbox_tokn_1X9gTzB1R80MAq0F", "http://localhost:8000");
    }

    @Test
    void GetInfo_ByDefault_ReturnsTeamWhichUserBelongTo() {
        try {
            Team team = client.team().getInfo();

            assertTrue(team != null);
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