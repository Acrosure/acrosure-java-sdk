# Acrosure Java SDK

The official Acrosure SDK for Java.



## Getting started & usage

First, initiate an instance of a client

```Java
import com.acrosure.Acrosure;

// ...

Acrosure client = new Acrosure('Your-Public-Key', 'Your-Private-Key');
```

### Working with insurance applications

Start by initializing a JSONObject instance:

```Java
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

// ...

JSONObject obj = new JSONObject();

obj.put("policy_unit", "D");
obj.put("insurer_list", new JSONArray());
obj.put("policy_date", "2018-06-20T07:16:00.071Z");
obj.put("expiry_date", "2018-06-30T07:16:02.461Z");
obj.put("countries", new JSONArray());

// add a string into countries array
((JSONArray) obj.get("countries")).add("UNITED ARAB EMIRATES");
```

Then create an application

```Java
import com.acrosure.Application;

// ...

try {
	Application app = client.applications().create("PRODUCT_ID", obj);
} catch (IOException e) {
	e.printStackTrace();
}
```

To get and update an already existing application

```Java
Application app = client.applications().get("APPLICATION_ID");

// add a new field to the application
app.data().put("customer_title", "MR.");

// then update it
client.applications().update(app);
```
