package org.suriya.mojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.suriya.data.Dependency;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mojo(name = "json-export",
        defaultPhase = LifecyclePhase.INITIALIZE,
        requiresDependencyCollection = ResolutionScope.COMPILE_PLUS_RUNTIME,
        requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class JsonExportMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject mavenProject;

    @Parameter(property = "fileName", defaultValue = "dependency")
    private String fileName;

    @Parameter(property = "filePath" )
    private String filePath;

    @Parameter(property = "beautify", defaultValue = "false" )
    private boolean beautify;


    public void execute() throws MojoExecutionException
    {
        String jsonFile = filePath + File.separator + fileName + ".json";

//        project.getResources().stream().forEach(resource -> {
//            getLog().info( "resource : " + resource);
//        });

//        project.getDependencyArtifacts().stream().forEach(dependency -> {
//            getLog().info( "dependencies : " + dependency);
//        });
//
//        project.getDependencies().stream().forEach(dependency -> {
//            getLog().info( "dependencies1 : " + ((Dependency)dependency).toString());
//        });
//

        mavenProject.getArtifacts().stream().forEach(artifact -> {
            getLog().info( "artifact : " + ((Artifact)artifact).toString());
        });

        Set<Artifact> artifacts = mavenProject.getArtifacts();
        List<Dependency> dependencies = artifacts.stream().map(streamedArtifact -> {
            Dependency dependency = new Dependency();
            Artifact artifact = ((Artifact)streamedArtifact);
            dependency.groupId = artifact.getGroupId();
            dependency.artifactId = artifact.getArtifactId();
            dependency.version = artifact.getVersion();
            return dependency;
        }).collect(Collectors.toList());

        ObjectMapper objectMapper = new ObjectMapper();
        if (beautify) {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        }

//        getLog().info("dependencies : " + dependencies);

        try {
//            getLog().info("dependencies json : " + objectMapper.writeValueAsString(dependencies));
            BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
            writer.write(objectMapper.writeValueAsString(dependencies));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public void grabTransitiveDependencies() {
//        try {
//            ArtifactFilter artifactFilter = new ScopeArtifactFilter(null);
//
//            DependencyNode rootNode = treeBuilder.buildDependencyTree(project,
//                    localRepository, artifactFactory, artifactMetadataSource,
//                    artifactFilter, artifactCollector);
//
//            CollectingDependencyNodeVisitor visitor =
//                    new CollectingDependencyNodeVisitor();
//
//            rootNode.accept(visitor);
//
//            List<DependencyNode> nodes = visitor.getNodes();
//            for (DependencyNode dependencyNode : nodes) {
//                int state = dependencyNode.getState();
//                Artifact artifact = dependencyNode.getArtifact();
//                if(state == DependencyNode.INCLUDED) {
//                    //...
//                }
//            }
//        } catch (DependencyTreeBuilderException e) {
//            // TODO handle exception
//            e.printStackTrace();
//        }
//    }
}
