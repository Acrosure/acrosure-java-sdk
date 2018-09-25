import com.acrosure.*;
import com.acrosure.Application;
import com.acrosure.Package;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class TravelInternationalIntegrationTest {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());

        URL basicDataPath = TravelInternationalIntegrationTest.class.getClassLoader().getResource("basic_data.json");
        URL additionalDataPath = TravelInternationalIntegrationTest.class.getClassLoader().getResource("additional_data.json");

        Acrosure client = new Acrosure("tokn_sample_secret");

        try {
            ObjectNode basicData = (ObjectNode) mapper.readTree(basicDataPath);
            ObjectNode additionalData = (ObjectNode) mapper.readTree(additionalDataPath);

            Application app = client.applications().create("prod_contractor", basicData, null, null);
            System.out.println("After creating application...");
            System.out.println(app);

            app.setAdditionalData(additionalData);
            client.applications().update(app);
            System.out.println("\nAfter updating application...");
            System.out.println(app);

            app.getAdditionalData().put("project_name", "โครงการ อโครบิวดิ้ง");
            ObjectNode projectSite = app.getAdditionalData().putObject("project_site");
            projectSite.put("subdistrict", "จอมพล");
            projectSite.put("district", "จตุจักร");
            projectSite.put("province", "กรุงเทพมหานคร");
            client.applications().update(app);
            System.out.println("\nAfter updating application...");
            System.out.println(app);

            Application app2 = client.applications().get(app.getId());
            System.out.println("\nAfter getting application...");
            System.out.println(app2);

            Package[] packages = client.applications().getPackages(app.getId());
            System.out.println("\nAfter getting packages...");
            System.out.println(Arrays.toString(packages));

            app.setPackageCode(packages[0].getPackageCode());
            client.applications().update(app);
            System.out.println("\nAfter selecting a package...");
            System.out.println(app);

            Policy[] policies = client.applications().confirm(app);
            System.out.println("\nAfter confirming application...");
            System.out.println(Arrays.toString(policies));
            System.out.println(app);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (AcrosureException e) {
            System.out.println(e.getMessage() + ", " + e.getStatusCode());
            e.printStackTrace();
        }
    }
}