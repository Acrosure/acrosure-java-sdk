import static org.junit.jupiter.api.Assertions.*;

import com.acrosure.Acrosure;
import com.acrosure.AcrosureException;
import com.acrosure.form.ApplicationCreateForm;
import com.acrosure.form.ApplicationQuery;
import com.acrosure.resource.ApplicationList;
import com.acrosure.resource.Package;
import com.acrosure.resource.Application;
import com.acrosure.resource.ApplicationStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.net.URL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CARIntegrationTest {
    private Acrosure client;
    private ObjectMapper mapper;
    private URL basicDataUrl;
    private URL AdditionalDataUrl;
    private Application application;
    private Package[] packages;

    @BeforeAll
    void init() {
        client = new Acrosure("tokn_sample_secret", "https://api.phantompage.com");
//        client = new Acrosure("sandbox_tokn_1X9gTzB1R80MAq0F", "http://localhost:8000");
        mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());

        try {
            application = client.application().create("prod_contractor");
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        } catch (AcrosureException e) {
            System.out.println(e.getMessage() + ", " + e.getStatusCode());
            e.printStackTrace();
            fail();
        }

        basicDataUrl = this.getClass().getClassLoader().getResource("basic_data.json");
        AdditionalDataUrl = this.getClass().getClassLoader().getResource("additional_data.json");
    }

    @Test
    void Create_WithOnlyProductId_ReturnsInitialApplication() {
        try {
            application = client.application().create("prod_contractor");
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
        applicationCreateForm.setProductId("prod_contractor");

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
            ApplicationList applicationList = client.application().list(query);

            System.out.println(applicationList.getPagination().getTotal());

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
                applicationCreateForm.setProductId("prod_contractor");
                ObjectNode basicData = (ObjectNode) mapper.readTree(basicDataUrl);
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
                applicationCreateForm.setProductId("prod_contractor");
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
                applicationCreateForm.setProductId("prod_contractor");
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
                applicationCreateForm.setProductId("prod_contractor");
                ObjectNode basicData = (ObjectNode) mapper.readTree(basicDataUrl);
                applicationCreateForm.setBasicData(basicData);
                application = client.application().create(applicationCreateForm);
                packages = client.application().getPackages(application);
                client.application().selectPackage(application, packages[0]);
            }

            ObjectNode additionalData = (ObjectNode) mapper.readTree(AdditionalDataUrl);
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
    void Submit_ByDefault_AdvancesStatusToSubmitted() {
        try {
            if (application.getStatus() != ApplicationStatus.READY) {
                ApplicationCreateForm applicationCreateForm = new ApplicationCreateForm();
                applicationCreateForm.setProductId("prod_contractor");
                ObjectNode basicData = (ObjectNode) mapper.readTree(basicDataUrl);
                applicationCreateForm.setBasicData(basicData);
                application = client.application().create(applicationCreateForm);
                packages = client.application().getPackages(application);
                client.application().selectPackage(application, packages[0]);
                ObjectNode additionalData = (ObjectNode) mapper.readTree(AdditionalDataUrl);
                application.setAdditionalData(additionalData);
                client.application().update(application);
            }

            client.application().submit(application);

            assertSame(ApplicationStatus.SUBMITTED, application.getStatus());

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