package com.acrosure.demo;

import com.acrosure.Acrosure;
import com.acrosure.AcrosureException;
import com.acrosure.form.ApplicationCreateForm;
import com.acrosure.resource.Application;
import com.acrosure.resource.Package;
import com.acrosure.resource.Policy;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@RestController
public class MainController {
    private Application application;
    private Acrosure client;
    private ObjectMapper mapper;

    public MainController() {
        Properties prop = new Properties();
        InputStream configStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");

        application = null;
        mapper = new ObjectMapper();

        try {
            prop.load(configStream);
            String token = prop.getProperty("secret_token");
            String host = prop.getProperty("remote_host");
            client = new Acrosure(token, host);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/")
    public String home() {
        return "<html>" +
                "<body>" +
                "<form action=\"/all\">\n" +
                "<input type=\"submit\" value=\"Test\" />\n" +
                "</form>" +
                "</body>" +
                "</html>";
    }

    @RequestMapping("/all")
    public Policy[] all() throws IOException, AcrosureException {
        create();
        selectPackage();
        update();
        Policy[] policies = confirm();

        return policies;
    }

    @RequestMapping("/create")
    public Application create() throws IOException, AcrosureException {
        String stringBasicData = "{\n" +
                "  \"effective_date\": \"2018-12-11\",\n" +
                "  \"vehicle_type\": \"110\",\n" +
                "  \"register_year\": 2017,\n" +
                "  \"brand_name\": \"HONDA\",\n" +
                "  \"model_name\": \"CITY\",\n" +
                "  \"spec_name\": \"1.5\",\n" +
                "  \"insurance_class\": \"FIRSTCLASS\",\n" +
                "  \"include_compulsory_policy\": false,\n" +
                "  \"include_voluntary_policy\": true,\n" +
                "  \"named_driver\": {\n" +
                "    \"total_named_drivers\": 1,\n" +
                "    \"birthdate1\": \"1988-10-14\"\n" +
                "  }\n" +
                "}";

        ObjectNode basicData = (ObjectNode) mapper.readTree(stringBasicData);
        ApplicationCreateForm form = new ApplicationCreateForm();
        form.setProductId("prod_motor");
        form.setBasicData(basicData);
        application = client.application().create(form);
        return application;
    }

    @RequestMapping("/select-package")
    public Application selectPackage() throws IOException, AcrosureException {
        Package[] packages = client.application().getPackages(application);
        client.application().selectPackage(application, packages[0]);
        return application;
    }

    @RequestMapping("/update")
    public Application update() throws IOException, AcrosureException {
        String stringAdditionalData = "{\n" +
                "  \"insured_information\": {\n" +
                "    \"type\": \"1\",\n" +
                "    \"title\": \"นาย\",\n" +
                "    \"first_name\": \"มานะ\",\n" +
                "    \"last_name\": \"มุ่งมั่น\",\n" +
                "    \"organization_type\": \"\",\n" +
                "    \"organization_name\": \"\",\n" +
                "    \"organization_no\": \"\",\n" +
                "    \"gender\": \"\",\n" +
                "    \"id_card\": \"1489900087857\",\n" +
                "    \"occupation_code\": \"99\",\n" +
                "    \"occupation_name\": \"พนักงานบริษัท\",\n" +
                "    \"branch_name\": \"\",\n" +
                "    \"address\": {\n" +
                "      \"address_no\": \"1\",\n" +
                "      \"moo\": \"2\",\n" +
                "      \"village\": \"วิลเลจ 3\",\n" +
                "      \"lane\": \"ลาดพร้าว 4\",\n" +
                "      \"street\": \"ลาดพร้าว\",\n" +
                "      \"minor_district\": \"\",\n" +
                "      \"subdistrict\": \"จอมพล\",\n" +
                "      \"district\": \"จตุจักร\",\n" +
                "      \"province\": \"กรุงเทพมหานคร\",\n" +
                "      \"postal_code\": \"10900\"\n" +
                "    },\n" +
                "    \"phone\": \"0861234567\",\n" +
                "    \"email\": \"customer@example.com\",\n" +
                "    \"beneficiary_name\": \"\"\n" +
                "  },\n" +
                "  \"motor_information\": {\n" +
                "    \"red_plate\": false,\n" +
                "    \"license_no\": \"กม9999\",\n" +
                "    \"license_province\": \"กรุงเทพมหานคร\",\n" +
                "    \"chassis_no\": \"TT20180330000000\",\n" +
                "    \"engine_no\": \"B20B3WP11644\",\n" +
                "    \"body_type\": \"\",\n" +
                "    \"color\": \"\",\n" +
                "    \"gear_type\": \"\",\n" +
                "    \"cc\": 1500,\n" +
                "    \"total_seats\": 0,\n" +
                "    \"weight\": 0,\n" +
                "    \"accessories_information\": \"\"\n" +
                "  },\n" +
                "  \"named_driver_information\": {\n" +
                "    \"driver1\": {\n" +
                "      \"title\": \"นาย\",\n" +
                "      \"first_name\": \"มานะ\",\n" +
                "      \"last_name\": \"มุ่งมั่น\",\n" +
                "      \"gender\": \"\",\n" +
                "      \"id_card\": \"1489900087857\",\n" +
                "      \"occupation_code\": \"\",\n" +
                "      \"occupation_name\": \"\",\n" +
                "      \"driver_card\": {\n" +
                "        \"license_no\": \"12345678\",\n" +
                "        \"license_type\": \"\",\n" +
                "        \"issuer\": \"\",\n" +
                "        \"issued_date\": \"0001-01-01\",\n" +
                "        \"expired_date\": \"0001-01-01\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"driver2\": null\n" +
                "  }\n" +
                "}";
        ObjectNode additionalData = (ObjectNode) mapper.readTree(stringAdditionalData);
        application.setAdditionalData(additionalData);
        client.application().update(application);
        return application;
    }

    @RequestMapping("/confirm")
    public Policy[] confirm() throws IOException, AcrosureException {
        return client.application().confirm(application);
    }
}
