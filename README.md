# Acrosure Java SDK

The official Acrosure SDK for Java.

## Documents (Java API)

You can download the document (Java API) directly from Maven Central Repository
(https://search.maven.org/ or other mirrors). The name will be in a form similar to
this: `acrosure-java-sdk-x.x.x-javadoc.jar`, where `x.x.x` is the version
number.


## Dependencies

* [jackson-databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)
_(tested with version 2.9.6)_
* [okhttp](https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp)
_(tested with version 3.10.0)_

## Installation

To install the SDK and its dependencies, you can download the JAR files
directly from Maven Central Repository (https://search.maven.org/ or other
mirrors). Or you can use your favorite Java dependency management tools such
as Maven or Gradle.

### Manual installation

Download all the JAR files from the [above links](#Dependencies), then download
the SDK JAR file from this [link](https://mvnrepository.com/artifact/com.acrosure/acrosure-java-sdk).

After you get all JAR files (3 of them), you can just simply import it to your
Java (or Android) projects using your favorite IDE, such as Eclipse, NetBeans,
or IntelliJ IDEA.

### Gradle

Add the following lines to `dependencies` block inside your `build.gradle` file

```Java
compile(
	[group: 'com.squareup.okhttp3', name: 'okhttp', version: '3.10.0'],
	[group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.9.6'],
	[group: 'com.acrosure', name: 'acrosure-java-sdk', version: '0.3.0'],
)
```

### Maven

Add the following lines to `<dependencies>` block inside your `pom.xml` file

```xml
<dependency>
    <groupId>com.squareup.okhttp3</groupId>
    <artifactId>okhttp</artifactId>
    <version>3.10.0</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.9.6</version>
</dependency>
<dependency>
    <groupId>com.acrosure</groupId>
    <artifactId>acrosure-java-sdk</artifactId>
    <version>0.3.0</version>
</dependency>
```

### Gradle (Android)

Add the following lines to `dependencies` block inside your `build.gradle` file

```Java
implementation (
    [group: 'com.acrosure', name: 'acrosure-java-sdk', version: '0.3.0'],
    [group: 'com.squareup.okhttp3', name: 'okhttp', version: '3.10.0'],
    [group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.9.6'],
)
```

## Getting started

First, import and initiate an instance of Acrosure client

```Java
import com.acrosure.Acrosure;

// ...

Acrosure client = new Acrosure("<your-public-token>");
```

You can obtain the token from https://dashboard.acrosure.com/.

## Examples

There are two available examples:

* [Web service using Spring Boot]()
* [Android application]()

_Note that there are two types of tokens: public and secret. One should carefully consider which of the token to be used in an application._

## Basic Usage

### Exceptions

Most of the operations in this section will produce two types of exceptions: `IOException` and `AcrosureException`. `AcrosureException` is a wrapper class that holds the server-side specific errors.

### Application

#### Create

To create an application, you can pass the product ID to `Acrosure.applicatoin().create()` directly:

```Java
// Pass only product ID to create() method

import com.acrosure.resource.Application;

// ...

Application application = client().application().create("prod_motor");

// Application has built-in toString() method
System.out.println(application);
```

or you can compose `ApplicationCreateForm` and set all the neccessary fields before pass it to the same `create()` method:

```Java
// Pass an instance of ApplicationCreateForm to create() method

import com.acrosure.resource.Application;
import com.acrosure.form.ApplicationCreateForm;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;

// ...

// Initiate an instance of ObjectMapper
ObjectMapper mapper = new ObjectMapper();

// Compose ApplicationCreateForm
ApplicationCreateForm form = new ApplicationCreateForm();

ObjectNode basicData = mapper.createObjectNode();
basicData.put("register_year", "1992");

form.setBasicData(basicData);
form.setProductId("prod_motor")

Application application = client().application().create(form);

// Application has built-in toString() method
System.out.println(application);
```

In case of using `ApplicationCreateForm`, a created application can be in `READY` status. This is very handy if you'd like to spend only a round trip to complete an application.

#### Get

```Java
import com.acrosure.resource.Application;

// ...

Application application = client.application().get("application_id");

```

#### Update

```Java
import com.acrosure.resource.Application;

// Modify the application
application.getBasicData().put("spec","150")

// Update (you can think of this as a sync function)
client.application().update(application)
```

#### Get package(s)

To get all the packages compatible with an application:

```Java
import com.acrosure.resource.Package;

// ...

Package[] packages = client.application().getPackages(application)
```

To get the package an application is currently selecting:

```Java
import com.acrosure.resource.Package;

// ...

Package aPackage = client.application().getPackage(application)
```

#### Select package

```Java
import com.acrosure.resource.Package;

// ...

Package[] packages = client.application().getPackages(application)

// You can examine the packages before deciding which one should be used

client.application().selectPackage(application, packages[0])
```

#### Submit

```Java
client.application().submit(application)
```

#### List

```Java
import com.acrosure.form.ApplicationQuery;

// Set query
ApplicationQuery query = new ApplicationQuery();
query.setLimit(100);

client.application().list(query);
```

### Product

#### Get

```Java
import com.acrosure.resource.Product;

// ...

Product product = client.product().get("prod_motor");
```

#### List

```Java
import com.acrosure.resource.Product;

// ...

Product[] products = client.product().list();
```

### Policy

#### Get

```Java
import com.acrosure.resource.Policy;

// ...

Policy policy = client.policy().get("<policy-id>");
```

#### List

```Java
import com.acrosure.form.PolicyQuery;
import com.acrosure.resource.PolicyList;

// ...

PolicyQuery query = new PolicyQuery();
query.setLimit(100);

PolicyList policyList = client.policy().list(query);
```

### Payment (2C2P)

#### Get Hash

```Java
import com.acrosure.resource.PaymentFormData;

// ...

PaymentFormData formData = client.payment2C2P().getHash(
        "sandbox_appl_eq6M2LBfm8n1nV2d",
        "example.com");
```

### Data

#### Get (with dependencies)

```Java
import com.acrosure.form.DataGetForm;
import com.acrosure.resource.Data;

// ...

DataGetForm<String> form = new DataGetForm<>();
form.setHandler("subdistrict");

String[] dependencies = {"กรุงเทพมหานคร", "วังทองหลาง"};
form.setDependencies(dependencies);

Data[] data = client.data().get(form);
```

#### Get (without dependencies)

```Java
import com.acrosure.form.DataGetForm;
import com.acrosure.resource.Data;

// ...

DataGetForm<Integer> form = new DataGetForm<>();
form.setHandler("province");

Data[] data = client.data().get(form);
```

### Team

#### Get Info

```Java
import com.acrosure.resource.Team;

// ...

Team team = client.team().getInfo();
```