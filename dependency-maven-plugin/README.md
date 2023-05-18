
## How is this project created?

This project is created using **quickstart archetype** and is modified to be published as 
a [maven plugin](https://maven.apache.org/guides/plugin/guide-java-plugin-development.html).

## Java support

This release version of this only supported in **Java 11** or *greater*.

## How to use this plugin?

In the application **pom.xml** file add the following **plugin** in the *build* configuration,
```xml
<plugins>
    <plugin>
        <groupId>org.suriya</groupId>
        <artifactId>dependency-maven-plugin</artifactId>
        <version>0.0.9-SNAPSHOT</version>
        <configuration>
            <fileName>my-dependency-json</fileName>
            <filePath>src/main/resources</filePath>
            <beautify>true</beautify>
        </configuration>
        <executions>
            <execution>
                <id>json-export-while-compile</id>
                <phase>compile</phase>
                <goals>
                    <goal>json-export</goal>
                </goals>
            </execution>
            <execution>
                <id>json-export-while-install</id>
                <phase>install</phase>
                <goals>
                    <goal>json-export</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
</plugins>
```

Run the plugin using,
```cmd
mvn json:json
```

### Configuration

|Name|Description| Default           |
|-|-|-------------------|
|fileName| Name of the file                          | dependency        |
|filePath| Path to which the file needs to exported to | src/main/resource |
|beautify| Beautifies the json while saving          | false             |

### Execution

The [execution in the example](#how-to-use-this-plugin) says to run the **json-export** goal during the phases, *mvn compile* and *mvn install*. 

###

```cmd
mvn release:prepare
```

```
mvn release:perform
```
