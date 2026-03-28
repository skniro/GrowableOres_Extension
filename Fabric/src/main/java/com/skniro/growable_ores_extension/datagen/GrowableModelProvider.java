package com.skniro.growable_ores_extension.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;

public class GrowableModelProvider extends FabricModelProvider {
    public GrowableModelProvider(FabricPackOutput dataGenerator){
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {


    }
    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {

    }
}
