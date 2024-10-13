package com.skniro.growable_ores_extension.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class GrowableOresDataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.createPack().addProvider(GrowableModelProvider::new);
        fabricDataGenerator.createPack().addProvider(GrowableTagGenerator::new);
    }
}
