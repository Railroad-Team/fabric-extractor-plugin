package dev.railroadide.fabricExtractorPlugin.model;

import java.io.Serial;
import java.io.Serializable;

public record FabricExtractorModelImpl(String minecraftVersion,
                                       String mappingsVersion,
                                       String loaderVersion,
                                       String fabricApiVersion,
                                       String loomVersion) implements FabricExtractorModel, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}

