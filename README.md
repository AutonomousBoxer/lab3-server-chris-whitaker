# lab3-server-chris-whitaker

Author: `Chris Whitaker`

Quarkus **gRPC** server implementing a simple `StudentInfo` service.  
Supports two RPCs:
- `addStudent(Student)`: stores a student and returns a generated id.
- `getStudent(StudentID)`: returns the student or `NOT_FOUND` if missing.

## Setup Environment

- Java 17+
  - [Eclipse Temurin JDK 17 (Adoptium)](https://adoptium.net/temurin/releases/?version=17)
  - [Oracle JDK 17 Downloads](https://www.oracle.com/java/technologies/downloads/)
- Maven 3.9+
  - [maven.apache.org/install.html](https://maven.apache.org/install.html)

**Verify installs**

```bash
java -version
mvn -version
```

## How to Run the Code

Run the application in dev mode using:

```shell script
./mvnw clean compile
```

```shell script
./mvnw quarkus:dev
```
## File Structure

    src/
        main/
            java/edu/franklin/lab3server/chriswhitaker/StudentInfoImpl.java
            proto/student.proto
            resources/application.properties
    target/
        generated-sourcees/grpc/... (generated stubs; do not edit)

## Known Issues
- Repository is in-memory and data is lost on restart
- If your IDE can't find `StudentInfoGrpc`, make sure `target/generated-sources/grpc` is marked as a _Generated 
  Sources Root_ 
- Then Run:
```shell script
./mvnw clean compile
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/lab3-server-chris-whitaker-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it

## Provided Code

### gRPC

Create your first gRPC service

[Related guide section...](https://quarkus.io/guides/grpc-getting-started)

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
