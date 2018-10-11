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
class TeamIntegrationTest {
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