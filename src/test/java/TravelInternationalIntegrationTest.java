import com.acrosure.*;
import com.acrosure.Application;
import com.acrosure.InsurancePackage;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class TravelInternationalIntegrationTest {

    public static void main(String[] args) {
        String json = "{\"policy_unit\":\"D\",\"insurer_list\":[],\"policy_date\":\"2018-06-20T07:16:00.071Z\","+
                "\"expiry_date\":\"2018-06-30T07:16:02.461Z\",\"countries\":[\"UNITED ARAB EMIRATES\"]}";
//        Acrosure client = new Acrosure("tokn_sample_public");
        Acrosure client = new Acrosure("tokn_sample_secret");

        JSONObject obj = new JSONObject(json);

        try {
            Application app = client.applications().create("prod_ta", obj);
            System.out.println("After creating application...");
            System.out.println(app);

            app.data().getJSONArray("insurer_list").put(new JSONObject(
                    "{\"card_type\":\"I\",\"first_name\":\"SRIKOTE \",\"last_name\":\"NAEWCHAMPA\"," +
                            "\"address\":{\"address_no\":\"315\",\"moo\":\"11\",\"village\":\"\",\"lane\":\"\"," +
                            "\"street\":\"KLANG AWUT\",\"postal_code\":\"34000\",\"province\":\"Ubon Ratchathani\"," +
                            "\"district\":\"Mueangubonratchathani\",\"subdistrict\":\"Khamyai\"},\"title\":\"MR.\"," +
                            "\"id_card\":\"1349900696510\",\"birthdate\":\"1995-04-05T07:18:44.543Z\"," +
                            "\"email\":\"srikote@kmi.tl\",\"phone\":\"0868702109\",\"nominee\":null}"));

            JSONObject appending = new JSONObject(
                    "{\"customer_title\":\"MR.\",\"customer_first_name\":\"SRIKOTE \"," +
                            "\"customer_last_name\":\"NAEWCHAMPA\",\"card_type\":\"I\"," +
                            "\"id_card\":\"1349900696510\",\"email\":\"srikote@kmi.tl\",\"phone\":\"0868702109\"," +
                            "\"company_name\":\"SRIKOTE \"}");

            for (Iterator<String> it = appending.keys(); it.hasNext(); ) {
                String key = it.next();
                app.data().put(key, appending.getString(key));
            }

            client.applications().update(app);
            System.out.println("\nAfter updating application...");
            System.out.println(app);

            Application app2 = client.applications().get(app.id());
            System.out.println("\nAfter getting application...");
            System.out.println(app2);

            ArrayList<InsurancePackage> insurancePackages = client.applications().getPackages(app.id());
            System.out.println("\nAfter getting packages...");
            System.out.println(insurancePackages);

            app.setPackage(insurancePackages.get(1));
            client.applications().update(app);
            System.out.println("\nAfter updating application...");
            System.out.println(app);

            ArrayList<Policy> policies = client.applications().confirm(app);
            System.out.println("\nAfter confirming application...");
            System.out.println(policies);
            System.out.println(app);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } catch (AcrosureException e) {
            System.out.println(e.getMessage() + ", " + e.getStatusCode());
            e.printStackTrace();
        }
    }
}