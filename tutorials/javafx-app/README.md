Basic Basilisk JavaFX/Java project
----------------------------------

You have just created a basic Basilisk application.
The project has the following file structure

    .
    ├── build.gradle
    ├── basilisk-app
    │   ├── conf
    │   ├── controllers
    │   ├── i18n
    │   ├── lifecycle
    │   ├── models
    │   ├── resources
    │   ├── services
    │   └── views
    └── src
        ├── main
        │   ├── java
        │   └── resources
        └── test
            ├── java
            └── resources

Simply add your source files to `src/main/java`, your test cases to
`src/test/java` and then you will be able to build your project with

    $ ./gradlew build
    $ ./gradlew test
    $ ./gradlew run
    $ ./gradlew launchIPhoneSimulator

Don't forget to add any extra JAR dependencies to `build.gradle`!

