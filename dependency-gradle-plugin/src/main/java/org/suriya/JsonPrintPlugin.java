package org.suriya;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.dsl.ArtifactHandler;
import org.gradle.api.artifacts.dsl.DependencyHandler;

public class JsonPrintPlugin implements Plugin<Project> {
    @Override
    public void apply(Project targetProject) {
        ArtifactHandler artifactHandler = targetProject.getArtifacts();
        DependencyHandler dependencyHandler = targetProject.getDependencies();
    }
}
