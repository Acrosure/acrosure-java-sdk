import static org.junit.jupiter.api.Assertions.*;

import com.acrosure.Acrosure;
import com.acrosure.AcrosureException;
import com.acrosure.form.ApplicationCreateForm;
import com.acrosure.form.ApplicationQuery;
import com.acrosure.resource.*;
import com.acrosure.resource.Package;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MotorIntegrationTest {
    private Acrosure client;
    private ObjectMapper mapper;
    private URL basicDataUrl;
    private URL AdditionalDataUrl;
    private Application application;
    private Package[] packages;

    @BeforeAll
    void init() {
        Properties prop = new Properties();
        InputStream configStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");

        mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());

        try {
            prop.load(configStream);
            String token = prop.getProperty("secret_token");
//            String host = prop.getProperty("remote_host");
//            String token = "sandbox_tokn_mFkNBPpRbnEMPh2u";
//            String host = prop.getProperty("local_host");
            client = new Acrosure(token);
            application = client.application().create("prod_motor");
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        } catch (AcrosureException e) {
            System.out.println(e.getMessage() + ", " + e.getStatusCode());
            e.printStackTrace();
            fail();
        }

        basicDataUrl = this.getClass().getClassLoader().getResource("motor_basic_data.json");
        AdditionalDataUrl = this.getClass().getClassLoader().getResource("motor_additional_data.json");
    }

    @Test
    void Create_WithOnlyProductId_ReturnsInitialApplication() {
        try {
            application = client.application().create("prod_motor");
            assertSame(application.getStatus(), ApplicationStatus.INITIAL);
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
    void Create_WithCompleteBasicData_AdvancesStatusToPackageRequired() {
        ApplicationCreateForm applicationCreateForm = new ApplicationCreateForm();
        applicationCreateForm.setProductId("prod_motor");

        try {
            ObjectNode basicData = (ObjectNode) mapper.readTree(basicDataUrl);
            applicationCreateForm.setBasicData(basicData);
            application = client.application().create(applicationCreateForm);

            assertSame(application.getStatus(), ApplicationStatus.PACKAGE_REQUIRED);
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
    void Get_ByDefault_ReturnsApplication() {
        try {
            String id = application.getId();
            application = client.application().get(id);

            assertEquals(application.getId(), id);
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
    void List_ByDefault_ReturnsApplicationsWithPagination() {
        try {
            ApplicationQuery query = new ApplicationQuery();
            query.setProductID("prod_motor");
            ApplicationList applicationList = client.application().list(query);

            System.out.println(applicationList.getPagination().getTotal());
            System.out.println(Arrays.toString(applicationList.getData()));

            assertTrue(applicationList.getData().length > 0);
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
    void GetPackage_ByDefault_ReturnsPackage() {
        try {
            if (application.getPackageCode().isEmpty()) {
                ApplicationCreateForm applicationCreateForm = new ApplicationCreateForm();
                applicationCreateForm.setProductId("prod_motor");
                ObjectNode basicData = (ObjectNode) mapper.readTree(basicDataUrl);
                ArrayNode nodes = basicData.putArray("test");
                applicationCreateForm.setBasicData(basicData);
                application = client.application().create(applicationCreateForm);
                packages = client.application().getPackages(application);
                client.application().selectPackage(application, packages[0]);
            }

            Package aPackage = client.application().getPackage(application);

            assertAll("Package",
                    () -> assertEquals(packages[0].getPackageCode(), aPackage.getPackageCode()),
                    () -> assertEquals(aPackage.getPackageCode(), application.getPackageCode()),
                    () -> assertEquals(application.getPackageCode(), application.getPackageData().getPackageCode()));
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
    void GetPackages_ByDefault_ReturnsPackages() {
        try {
            if (application.getStatus() == ApplicationStatus.INITIAL) {
                ApplicationCreateForm applicationCreateForm = new ApplicationCreateForm();
                applicationCreateForm.setProductId("prod_motor");
                ObjectNode basicData = (ObjectNode) mapper.readTree(basicDataUrl);
                applicationCreateForm.setBasicData(basicData);
                application = client.application().create(applicationCreateForm);
            }
            packages = client.application().getPackages(application);

            assertTrue(packages.length > 0);
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
    void SelectPackage_ByDefault_ReturnsApplicationWithPackage() {
        try {
            if (application.getStatus() != ApplicationStatus.PACKAGE_REQUIRED) {
                ApplicationCreateForm applicationCreateForm = new ApplicationCreateForm();
                applicationCreateForm.setProductId("prod_motor");
                ObjectNode basicData = (ObjectNode) mapper.readTree(basicDataUrl);
                applicationCreateForm.setBasicData(basicData);
                application = client.application().create(applicationCreateForm);
                packages = client.application().getPackages(application);
            }

            client.application().selectPackage(application, packages[0]);

            assertAll("application",
                    () -> assertEquals(application.getPackageCode(), packages[0].getPackageCode(),
                            "Package code is not empty"),
                    () -> assertNotNull(application.getPackageData(),
                            "Package data is not null"),
                    () -> assertSame(ApplicationStatus.DATA_REQUIRED, application.getStatus())
            );
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
    void Update_WithAdditionalData_AdvancesStatusToReady() {
        try {
            if (application.getStatus() != ApplicationStatus.DATA_REQUIRED) {
                ApplicationCreateForm applicationCreateForm = new ApplicationCreateForm();
                applicationCreateForm.setProductId("prod_motor");
                ObjectNode basicData = (ObjectNode) mapper.readTree(basicDataUrl);
                applicationCreateForm.setBasicData(basicData);
                application = client.application().create(applicationCreateForm);
                packages = client.application().getPackages(application);
                client.application().selectPackage(application, packages[0]);
            }

            ObjectNode additionalData = (ObjectNode) mapper.readTree(AdditionalDataUrl);
            additionalData.put("chassis_no", generateChassisNo());
            application.setAdditionalData(additionalData);
            client.application().update(application);

            assertSame(ApplicationStatus.READY, application.getStatus());

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
    void Confirm_ByDefault_ReturnsPolicies() {
        try {
            if (application.getStatus() != ApplicationStatus.READY) {
                ApplicationCreateForm applicationCreateForm = new ApplicationCreateForm();
                applicationCreateForm.setProductId("prod_motor");
                ObjectNode basicData = (ObjectNode) mapper.readTree(basicDataUrl);
                applicationCreateForm.setBasicData(basicData);
                application = client.application().create(applicationCreateForm);
                packages = client.application().getPackages(application);
                client.application().selectPackage(application, packages[0]);
                ObjectNode additionalData = (ObjectNode) mapper.readTree(AdditionalDataUrl);
                additionalData.put("chassis_no", generateChassisNo());
                application.setAdditionalData(additionalData);
                client.application().update(application);
            }

            Policy[] policies = client.application().confirm(application);
            System.out.println(Arrays.toString(policies));

            assertTrue(policies.length > 0);

        } catch (IOException e) {
            e.printStackTrace();
            fail();
        } catch (AcrosureException e) {
            System.out.println(e.getMessage() + ", " + e.getStatusCode());
            e.printStackTrace();
            fail();
        }
    }

    static String generateChassisNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        return "TT" + sdf.format(date);
    }
}