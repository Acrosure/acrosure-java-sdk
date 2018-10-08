import com.acrosure.*;
import com.acrosure.Application;
import com.acrosure.Package;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class CARIntegrationTest {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());

        File basicDataFile = new File("/home/kohpai/IdeaProjects/acrosure-java-sdk/src/test/java/basic_data.json");
        File additionalDataFile = new File("/home/kohpai/IdeaProjects/acrosure-java-sdk/src/test/java/additional_data.json");
        File policiesFile = new File("/home/kohpai/IdeaProjects/acrosure-java-sdk/src/test/java/policies.json");

        Acrosure client = new Acrosure("tokn_sample_secret", "https://localhost:8000");
        Acrosure adminClient = new Acrosure("sandbox_tokn_ghlKOqtR5iM1bQXh", "http://localhost:8000");

        try {
            ObjectNode basicData = (ObjectNode) mapper.readTree(basicDataFile);
            ObjectNode additionalData = (ObjectNode) mapper.readTree(additionalDataFile);
            ArrayNode arrayPolicies = (ArrayNode) mapper.readTree(policiesFile);

            Policy[] requestPolicies = new Policy[arrayPolicies.size()];
            for (int i = 0; i < arrayPolicies.size(); ++i) {
                requestPolicies[i] = mapper.treeToValue(arrayPolicies.get(i), Policy.class);
            }

            ApplicationCreateForm applicationCreateForm = new ApplicationCreateForm();
            applicationCreateForm.setProductId("prod_contractor");
            applicationCreateForm.setBasicData(basicData);

            Application app = client.applications().create(applicationCreateForm);
            System.out.println("After creating application...");
            System.out.println(app);
            System.out.println(app.getStatus() == ApplicationStatus.PACKAGE_REQUIRED);

            Package[] packages = client.applications().getPackages(app.getId());
            System.out.println("\nAfter getting packages...");
            System.out.println(Arrays.toString(packages));

            client.applications().selectPackage(app, packages[0].getPackageCode());
            System.out.println("\nAfter selecting a package...");
            System.out.println(app);
            System.out.println(app.getStatus() == ApplicationStatus.DATA_REQUIRED);

            app.setAdditionalData(additionalData);
            client.applications().update(app);
            System.out.println("\nAfter updating application...");
            System.out.println(app);
            System.out.println(app.getStatus() == ApplicationStatus.DATA_REQUIRED);

            app.getAdditionalData().put("project_name", "โครงการ อโครบิวดิ้ง");
            ObjectNode projectSite = app.getAdditionalData().putObject("project_site");
            projectSite.put("subdistrict", "จอมพล");
            projectSite.put("district", "จตุจักร");
            projectSite.put("province", "กรุงเทพมหานคร");
            client.applications().update(app);
            System.out.println("\nAfter updating application...");
            System.out.println(app);
            System.out.println(app.getStatus() == ApplicationStatus.READY);

            Application app2 = client.applications().get(app.getId());
            System.out.println("\nAfter getting application...");
            System.out.println(app2);

            client.applications().submit(app);
            System.out.println("\nAfter submitting application...");
            System.out.println(app);
            System.out.println(app.getStatus() == ApplicationStatus.SUBMITTED);

            Policy[] policies = adminClient.applications().approve(app, requestPolicies);
            System.out.println("\nAfter approving application...");
            System.out.println(Arrays.toString(policies));
            System.out.println(app);
            System.out.println(app.getStatus() == ApplicationStatus.COMPLETED);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (AcrosureException e) {
            System.out.println(e.getMessage() + ", " + e.getStatusCode());
            e.printStackTrace();
        }
    }
}