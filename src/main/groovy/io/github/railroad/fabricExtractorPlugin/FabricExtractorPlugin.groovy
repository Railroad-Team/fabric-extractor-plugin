package io.github.railroad.fabricExtractorPlugin

import io.github.railroad.fabricExtractorPlugin.model.FabricExtractorModelBuilder
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.tooling.provider.model.ToolingModelBuilderRegistry

import javax.inject.Inject

class FabricExtractorPlugin implements Plugin<Project> {
    private final ToolingModelBuilderRegistry registry

    @Inject
    FabricExtractorPlugin(ToolingModelBuilderRegistry registry) {
        this.registry = registry
    }

    @Override
    void apply(Project project) {
        registry.register(new FabricExtractorModelBuilder())
    }
}
