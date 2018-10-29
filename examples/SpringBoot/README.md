# Web Service Example using Spring Boot

This example will show you how to use [Acrosure Java SDK](https://github.com/Acrosure/acrosure-java-sdk/)
to implement a simple web service via [Spring Boot](http://spring.io/projects/spring-boot)
framework.

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
	[group: 'com.acrosure', name: 'acrosure-java-sdk', version: '0.4.0'],
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
    <version>0.4.0</version>
</dependency>
```

## Setting Up

* Open [IntelliJ IDEA](https://www.jetbrains.com/idea/) or your favorite IDEs such as Eclipse, NetBeans, etc.
* Open this directory (`acrosure-java-sdk/examples/SpringBoot`) as IntelliJ IDEA project
* In case of other IDEs, you can import the directory as well.
* Rename `/src/main/resources/config.properties.sample` to `/src/main/resources/config.properties`
* Edit the necessary fields in `config.properties` file: `secret_token` and `public_token`.
* You can run the project right from the IDE or you can compile it to JAR file and run it.