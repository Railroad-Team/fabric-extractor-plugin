package dev.railroadide.fabricExtractorPlugin

import dev.railroadide.fabricExtractorPlugin.model.FabricExtractorModelBuilder
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
