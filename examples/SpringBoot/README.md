# Web Service Example using Spring Boot

This example will show you how to use [Acrosure Java SDK](https://github.com/Acrosure/acrosure-java-sdk/)
to implement a simple web service via [Spring Boot](http://spring.io/projects/spring-boot)
framework.

## Dependencies

* [jackson-databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)
_(tested with version 2.9.6)_
* [okhttp](https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp)
_(tested with version 3.10.0)_

## Setting Up

* Open [IntelliJ IDEA](https://www.jetbrains.com/idea/) or your favorite IDEs such as Eclipse, NetBeans, etc.
* Open this directory (`acrosure-java-sdk/examples/SpringBoot`) as IntelliJ project
* In case of other IDEs, you can import the directory as well.
* Rename `/src/main/resources/config.properties.sample` to `/src/main/resources/config.properties`
* Edit the necessary fields in `config.properties` file: `secret_token` and `public_token`.
* You can run the project right from the IDE or you can compile it to JAR file and run it.