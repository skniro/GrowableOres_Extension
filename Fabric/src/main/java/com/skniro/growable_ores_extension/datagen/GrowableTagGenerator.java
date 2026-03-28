package com.skniro.growable_ores_extension.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

public class GrowableTagGenerator extends FabricTagsProvider.BlockTagsProvider {
   public GrowableTagGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
   }
   @Override
   protected void addTags(HolderLookup.Provider arg) {


   }
}