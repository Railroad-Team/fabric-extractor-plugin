package dev.railroadide.fabricExtractorPlugin.model

import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.tooling.provider.model.ToolingModelBuilder

class FabricExtractorModelBuilder implements ToolingModelBuilder, Serializable {
    private static final long serialVersionUID = 1L

    @Override
    boolean canBuild(String modelName) {
        return modelName == FabricExtractorModel.name
    }

    static String findVersion(List<?> allDeps,
                              String group, String name, boolean includeClassifier = false) {
        def dep = allDeps.find { it.group == group && it.name == name }
        if (!dep)
            return null

        def version = dep.version
        if (includeClassifier && dep instanceof ExternalModuleDependency && dep.artifacts) {
            def artifact = dep.artifacts.find { it.classifier }
            if (artifact) {
                version += "-${artifact.classifier}"
            }
        }

        return version
    }

    @Override
    Object buildAll(String modelName, Project project) {
        def allDeps = project.configurations
                .collect { cfg -> cfg.dependencies }
                .flatten()
                .findAll { it instanceof ExternalModuleDependency }

        def minecraftVersion = findVersion(allDeps, 'com.mojang', 'minecraft')
        def yarnMappings = findVersion(allDeps, 'net.fabricmc', 'yarn', true)
        def fabricLoaderVersion = findVersion(allDeps, 'net.fabricmc', 'fabric-loader')
        def fabricApiVersion = findVersion(allDeps, 'net.fabricmc.fabric-api', 'fabric-api')

        def loomVersion = project.hasProperty('fabricLoomVersion') ?
                project.property('fabricLoomVersion').toString() :
                null

        return new FabricExtractorModelImpl(
                minecraftVersion,
                yarnMappings,
                fabricLoaderVersion,
                fabricApiVersion,
                loomVersion
        )
    }
}
