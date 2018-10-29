# Android Example

This example will show you how to [Acrosure Java SDK](https://github.com/Acrosure/acrosure-java-sdk/)
to implement a simple Android application.

## Dependencies

* [jackson-databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)
_(tested with version 2.9.6)_
* [okhttp](https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp)
_(tested with version 3.10.0)_

## Installation

To install the SDK and its dependencies, you can download the JAR files
directly from Maven Central Repository (https://search.maven.org/ or other
mirrors). Or you can use Gradle.

### Manual installation

Download all the JAR files from the [above links](#Dependencies), then download
the SDK JAR file from this [link](https://mvnrepository.com/artifact/com.acrosure/acrosure-java-sdk).

After you get all JAR files (3 of them), you can just simply import it to your
Java (or Android) projects using your favorite IDE, such as Eclipse, NetBeans,
or IntelliJ IDEA.

### Gradle

Add the following lines to `dependencies` block inside your `build.gradle` file

```Java
implementation (
    [group: 'com.acrosure', name: 'acrosure-java-sdk', version: '0.4.0'],
    [group: 'com.squareup.okhttp3', name: 'okhttp', version: '3.10.0'],
    [group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.9.6'],
)
```

## Setting Up

* Open [Android Studio](https://developer.android.com/studio/)
* Open this directory (`acrosure-java-sdk/examples/Android`) as Android Studio project
* Rename `/app/src/main/res/raw/config.properties.sample` to `/app/src/main/res/raw/config.properties`
* Edit the necessary fields in `config.properties` file: `secret_token` and `public_token`.
* You can run the project right from the IDE as emulator or on your device.