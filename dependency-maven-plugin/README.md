
## How is this project created?

This project is created using **quickstart archetype** and is modified to be published as 
a [maven plugin](https://maven.apache.org/guides/plugin/guide-java-plugin-development.html).

## How to use this plugin?

In the application **pom.xml** file add the following **plugin** in the *build* configuration,
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.suriya</groupId>
            <artifactId>dependency-maven-plugin</artifactId>
            <version>0.0.6-SNAPSHOT</version>
            <configuration>
                <fileName>my-json</fileName>
                <filePath>src/main/resources</filePath>
                <beautify>true</beautify>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### Configuration

|-|-|
|Name|Description|Default
|fileName|Name of the file|
|filePath|Path to which the file needs to exported to|
|beautify||


Run the plugin using,
```cmd
mvn json:json
```

## Java support

This release version of this only supported in **Java 11** or *greater*.