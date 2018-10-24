# Acrosure Java SDK

The official Acrosure SDK for Java.

## Documents (Java API)

You can download the document (Java API) directly from Maven Central Repository. The name will be in a form like this: `acrosure-java-sdk-x.x.x-javadoc.jar`, where `x.x.x` is the version number.


## Dependencies

* [jackson-databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind) _(tested with version 2.9.6)_
* [okhttp](https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp) _(tested with version 3.10.0)_

## Installation

To install the SDK and its dependencies, you can download the JAR files directly from Maven Central Repository (https://search.maven.org/ or other mirrors). Or you can use your favorite Java dependency management tools such as Maven or Gradle.

In case of Gradle, add the following lines to your `build.gradle` file

```Java
compile(
	[group: 'com.squareup.okhttp3', name: 'okhttp', version: '3.10.0'],
	[group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.9.6'],
	[group: 'com.acrosure', name: 'acrosure-java-sdk', version: '0.3.0'],
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