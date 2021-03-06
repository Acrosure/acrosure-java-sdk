import static org.junit.jupiter.api.Assertions.*;

import com.acrosure.Acrosure;
import com.acrosure.AcrosureException;
import com.acrosure.form.DataGetForm;
import com.acrosure.resource.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DataIntegrationTest {
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
    void Get_WithDependencies_ReturnsData() {
        try {
            DataGetForm<String> form = new DataGetForm<>();
            form.setHandler("subdistrict");
            String[] dependencies = {"กรุงเทพมหานคร", "วังทองหลาง"};
            form.setDependencies(dependencies);
            Data[] data = client.data().get(form);

            System.out.println(data.length);

            assertTrue(data.length > 0);
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
    void Get_WithoutDependencies_ReturnsData() {
        try {
            DataGetForm<Integer> form = new DataGetForm<>();
            form.setHandler("province");
            Data[] data = client.data().get(form);

            System.out.println(data.length);

            assertTrue(data.length > 0);
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