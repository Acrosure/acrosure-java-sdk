import com.acrosure.Acrosure;
import com.acrosure.AcrosureException;
import com.acrosure.Application;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String json = "{\"policy_unit\":\"D\",\"insurer_list\":[],\"policy_date\":\"2018-06-20T07:16:00.071Z\","+
                "\"expiry_date\":\"2018-06-30T07:16:02.461Z\",\"countries\":[\"UNITED ARAB EMIRATES\"]}";
        Acrosure client = new Acrosure("tokn_sample_public");

        JSONObject obj = (JSONObject) JSONValue.parse(json);

        try {
            Application app = client.applications().create("prod_ta", obj);
            System.out.println("After creation...");
            System.out.println(app.toString());
            System.out.println(app.data().toJSONString());

            ((JSONArray) app.data().get("insurer_list")).add(JSONValue.parse(
                    "{\"card_type\":\"I\",\"first_name\":\"SRIKOTE \",\"last_name\":\"NAEWCHAMPA\"," +
                            "\"address\":{\"address_no\":\"315\",\"moo\":\"11\",\"village\":\"\",\"lane\":\"\"," +
                            "\"street\":\"KLANG AWUT\",\"postal_code\":\"34000\",\"province\":\"Ubon Ratchathani\"," +
                            "\"district\":\"Mueangubonratchathani\",\"subdistrict\":\"Khamyai\"},\"title\":\"MR.\"," +
                            "\"id_card\":\"1349900696510\",\"birthdate\":\"1995-04-05T07:18:44.543Z\"," +
                            "\"email\":\"srikote@kmi.tl\",\"phone\":\"0868702109\",\"nominee\":null}"));

            app.data().putAll((Map) JSONValue.parse(
                    "{\"customer_title\":\"MR.\",\"customer_first_name\":\"SRIKOTE \"," +
                            "\"customer_last_name\":\"NAEWCHAMPA\",\"card_type\":\"I\"," +
                            "\"id_card\":\"1349900696510\",\"email\":\"srikote@kmi.tl\",\"phone\":\"0868702109\"," +
                            "\"company_name\":\"SRIKOTE \"}"));

            client.applications().update(app);
            System.out.println("\nAfter updating...");
            System.out.println(app.toString());
            System.out.println(app.data().toJSONString());

            Application app2 = client.applications().get(app.getId());
            System.out.println("\nAfter getting...");
            System.out.println(app2.toString());
            System.out.println(app2.data().toJSONString());

            System.out.println("\nGet packages...");
            System.out.println(client.applications().getPackages(app.getId()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AcrosureException e) {
            System.out.println(e.getMessage() + ", " + e.getStatusCode());
            e.printStackTrace();
        }
    }
}
