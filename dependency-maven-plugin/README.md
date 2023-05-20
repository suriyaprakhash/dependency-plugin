
## Java support

This release version of this only supported in **Java 11** or *greater*.

## How to use this plugin?

In the application **pom.xml** file add the following **plugin** in the *build* configuration,
```xml
<plugins>
    <plugin>
        <groupId>com.suriyaprakhash</groupId>
        <artifactId>dependency-maven-plugin</artifactId>
        <version>0.0.21</version>
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

## Deployment

### Building the artifact

The following command would create *md5* and *sha* checksum to be uploaded to the [maven central](https://central.sonatype.com)

```cmd
mvn install -DcreateChecksum=true
```

The above command would also sign the artifacts *jar, pom, javadoc and sources* using [gpg](https://central.sonatype.org/publish/requirements/gpg/) using the 
plugin and the **gpg** installation and would result in *asc* signed files.

### Publishing

The [build step](#building-the-artifact) should have produced the required files in .m2 to publish. Place these files into the 
following folder structure and make a zip out of it.

```
/com/suriyaprakhash/dependency-maven-plugin/0.0.21
```

Go to the [publishing section](https://central.sonatype.com/publishing) of the [maven central](https://central.sonatype.com/) and
publish.

**Deployment Name** com.suriyaprakhash:dependency-maven-plugin:0.0.21
**Description** The description of the artifact that shows information about the plugin
**Upload Your File** The zip file


## Miscellaneous Information

### How is this project created?

This project is created using **quickstart archetype** and is modified to be published as
a [maven plugin](https://maven.apache.org/guides/plugin/guide-java-plugin-development.html).

Deployment to [maven central](https://central.sonartype.org) would need the [gpg](https://central.sonatype.org/publish/requirements/gpg/) installed and
should [have the requirements](https://central.sonatype.org/publish/requirements/#create-a-ticket-with-sonatype) satisfied.

[Here is how to sign with gpg](https://central.sonatype.org/publish/publish-manual/#signing-components)

--[This](https://central.sonatype.org/publish/publish-guide/) has the info on how to get started with publishing and 
[Here is a sample **pom**](https://github.com/simpligility/ossrh-demo/tree/master) file.

```cmd
mvn gpg:sign-and-deploy-file
```

```cmd
mvn release:prepare
```

```
mvn release:perform
```

### Steps to manually sign



Use **Kleopatra GPG** or *command line* as in following to generate not the binary, it should be text (.asc) format. 

```cmd
gpg -ab dependency-maven-plugin-0.0.11.jar
```

[Zip file upload](https://central.sonatype.org/publish-ea/publish-ea-guide/#adding-a-webhook)

```cmd
mvn install -DcreateChecksum=true
```

```cmd
jar -cvf bundle.jar .
```

```cmd
gpg -ab myfile.java
```