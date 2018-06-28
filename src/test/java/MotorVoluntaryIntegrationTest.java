import com.acrosure.*;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class MotorVoluntaryIntegrationTest {
    public static void main(String[] args) {
        String formData = "{\"net_gross\":15843.49,\"spec_name\":\"S CNG AT\",\"brand_name\":\"HONDA\"," +
                "\"model_name\":\"CITY\",\"package_id\":\"110GROUP_5PREMIUM_REDSaveSafe220009999500000\"," +
                "\"sum_insured\":500000,\"vehicle_type\":\"110\",\"register_year\":2017," +
                "\"insurance_class\":\"FIRSTCLASS\",\"motor_information\":{\"age\":1,\"color\":\"ดำ\",\"cc\":1500," +
                "\"weight\":2000,\"body_type\":\"รถเก๋งสองตอน\",\"engine_no\":\"B20B3WP11644\",\"gear_type\":\"A\"," +
                "\"chassis_no\":\"{{chassis_no}}\",\"license_no\":\"กม9999\",\"total_seats\":4," +
                "\"license_province\":\"กรุงเทพมหานคร\"}," +
                "\"insured_information\":{\"age\":30,\"type\":\"1\",\"email\":\"panjmp@gmail.com\"," +
                "\"phone\":\"0999999999\",\"title\":\"นาย\",\"address\":{\"moo\":\"4\",\"lane\":\"\",\"alley\":\"\"," +
                "\"street\":\"\",\"village\":\"ดีพร้อม 3\",\"district\":\"เมืองนครสวรรค์\",\"province\":\"นครสวรรค์\"," +
                "\"address_no\":\"399/73\",\"postal_code\":\"60000\",\"subdistrict\":\"นครสวรรค์ตก\"}," +
                "\"id_card\":\"1489900087857\",\"birthdate\":\"1988-10-14\",\"card_type\":\"I\"," +
                "\"last_name\":\"มุ่งมั่น\",\"first_name\":\"มานะ\"},\"invoice_information\":{\"title\":\"นาย\"," +
                "\"address\":{\"moo\":\"4\",\"lane\":\"\",\"alley\":\"\",\"street\":\"\",\"village\":\"ดีพร้อม 3\"," +
                "\"district\":\"เมืองนครสวรรค์\",\"province\":\"นครสวรรค์\",\"address_no\":\"399/73\"," +
                "\"postal_code\":\"60000\",\"subdistrict\":\"นครสวรรค์ตก\"},\"last_name\":\"มุ่งมั่น\"," +
                "\"first_name\":\"มานะ\"},\"repair_provider_type\":\"Y\"," +
                "\"named_driver_information\":{\"driver1\":{\"title\":\"นาย\",\"id_card\":\"1489900087857\"," +
                "\"birthdate\":\"1988-10-14\",\"card_type\":\"I\",\"last_name\":\"มุ่งมั่น\",\"first_name\":\"มานะ\"," +
                "\"driver_card\":{\"license_no\":\"12345678\"}},\"total_named_drivers\":1}," +
                "\"include_compulsory_policy\":false}";

        Acrosure client = new Acrosure("tokn_sample_secret");

        JSONObject obj = new JSONObject(formData);

        try {
            Application app = client.applications().create("prod_motor", obj);
            System.out.println("After creating application...");
            System.out.println(app);
            System.out.println(app.errorFields());

//            app.data().getJSONArray("insurer_list").put(new JSONObject(
//                    "{\"card_type\":\"I\",\"first_name\":\"SRIKOTE \",\"last_name\":\"NAEWCHAMPA\"," +
//                            "\"address\":{\"address_no\":\"315\",\"moo\":\"11\",\"village\":\"\",\"lane\":\"\"," +
//                            "\"street\":\"KLANG AWUT\",\"postal_code\":\"34000\",\"province\":\"Ubon Ratchathani\"," +
//                            "\"district\":\"Mueangubonratchathani\",\"subdistrict\":\"Khamyai\"},\"title\":\"MR.\"," +
//                            "\"id_card\":\"1349900696510\",\"birthdate\":\"1995-04-05T07:18:44.543Z\"," +
//                            "\"email\":\"srikote@kmi.tl\",\"phone\":\"0868702109\",\"nominee\":null}"));
//
//            JSONObject appending = new JSONObject(
//                    "{\"customer_title\":\"MR.\",\"customer_first_name\":\"SRIKOTE \"," +
//                            "\"customer_last_name\":\"NAEWCHAMPA\",\"card_type\":\"I\"," +
//                            "\"id_card\":\"1349900696510\",\"email\":\"srikote@kmi.tl\",\"phone\":\"0868702109\"," +
//                            "\"company_name\":\"SRIKOTE \"}");
//
//            for (Iterator<String> it = appending.keys(); it.hasNext(); ) {
//                String key = it.next();
//                app.data().put(key, appending.getString(key));
//            }
//
//            client.applications().update(app);
//            System.out.println("\nAfter updating application...");
//            System.out.println(app);
//
//            Application app2 = client.applications().get(app.id());
//            System.out.println("\nAfter getting application...");
//            System.out.println(app2);
//
//            ArrayList<InsurancePackage> insurancePackages = client.applications().getPackages(app.id());
//            System.out.println("\nAfter getting packages...");
//            System.out.println(insurancePackages);
//
//            app.setPackage(insurancePackages.get(1));
//            client.applications().update(app);
//            System.out.println("\nAfter updating application...");
//            System.out.println(app);
//
//            ArrayList<Policy> policies = client.applications().confirm(app);
//            System.out.println("\nAfter confirming application...");
//            System.out.println(policies);
//            System.out.println(app);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } catch (AcrosureException e) {
            System.out.println(e.getMessage() + ", " + e.getStatusCode());
            e.printStackTrace();
        }
    }
}
