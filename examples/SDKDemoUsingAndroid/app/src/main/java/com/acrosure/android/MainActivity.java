package com.acrosure.android;

import android.app.Activity;
import android.app.Application;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.acrosure.Acrosure;
import com.acrosure.AcrosureException;
import com.acrosure.form.ApplicationCreateForm;
import com.acrosure.resource.Package;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class MainActivity extends Activity {
    TextView output;
    com.acrosure.resource.Application application;
    boolean running;
    boolean done;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = findViewById(R.id.textView);
        application = null;
    }

    public void setResponseText(View view) {
        if (running) {
            if (done) {
                output.setText(application.getId());
            } else {
                output.setText("The process is being run.\n Please check back in few seconds");
            }
            return;
        }

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                backgroundTask();
            }
        });

        running = true;
        output.setText("Started!!!");
    }

    private void backgroundTask() {
        ObjectMapper mapper = new ObjectMapper();

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

        try {
            ObjectNode basicData = (ObjectNode) mapper.readTree(stringBasicData);
            ObjectNode additionalData = (ObjectNode) mapper.readTree(stringAdditionalData);
            ApplicationCreateForm form = new ApplicationCreateForm();
            form.setProductId("prod_motor");
            form.setBasicData(basicData);
            form.setAdditionalData(additionalData);
            String token = Helper.getConfigValue(this, "secret_token");
            String host = Helper.getConfigValue(this, "remote_host");
            Acrosure client = new Acrosure(token, host);
            Log.v("YOYOYO", "Before request");
            application = client.application().create(form);
            Log.v("YOYOYO", "After create request");
            Package[] packages = client.application().getPackages(application);
            Log.v("YOYOYO", "After get-package request");
            client.application().selectPackage(application, packages[0]);
            Log.v("YOYOYO", "After select-package request");
            done = true;
        } catch (IOException | AcrosureException e) {
            Log.d("MY_DEBUG", e.getMessage(), e.getCause());
            e.printStackTrace();
        }
    }
}
