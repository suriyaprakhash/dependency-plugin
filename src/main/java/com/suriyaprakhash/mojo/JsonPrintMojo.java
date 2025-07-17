package com.suriyaprakhash.mojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.suriyaprakhash.data.Dependency;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mojo(name = "json-print",
        defaultPhase = LifecyclePhase.INITIALIZE,
        requiresDependencyCollection = ResolutionScope.COMPILE_PLUS_RUNTIME,
        requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class JsonPrintMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject mavenProject;

    public void execute() throws MojoExecutionException
    {
        getLog().info( "The following artifacts are part of the either compile time or " +
                "runtime dependency:");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        Set<Artifact> artifacts = mavenProject.getArtifacts();
        List<Dependency> dependencies = artifacts.stream().map(streamedArtifact -> {
            Artifact artifact = ((Artifact)streamedArtifact);
            Dependency dependency = new Dependency();
            dependency.groupId = artifact.getGroupId();
            dependency.artifactId = artifact.getArtifactId();
            dependency.version = artifact.getVersion();
            return dependency;
        }).collect(Collectors.toList());

        try {
            getLog().info("dependencies json : " + objectMapper.writeValueAsString(dependencies));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
