package com.skniro.growable_ores_extension.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

public class GrowableTagGenerator extends FabricTagProvider.BlockTagProvider {
   public GrowableTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
   }
   @Override
   protected void configure(HolderLookup.Provider arg) {


   }
}