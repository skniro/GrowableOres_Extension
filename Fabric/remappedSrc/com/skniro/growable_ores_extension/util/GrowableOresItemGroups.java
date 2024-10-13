package com.skniro.growable_ores_extension.util;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import com.skniro.growable_ores_extension.block.GrowableOresBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public class GrowableOresItemGroups {
    public static final ResourceKey<CreativeModeTab> Growable_Ores_Group = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.tryBuild(GrowableOresExtension.MOD_ID, "test_group"));

    public static void vanilla_item() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Growable_Ores_Group, FabricItemGroup.builder()
                .icon(() -> new ItemStack(GrowableOresBlocks.GrowableOres_Block))
                .displayName(Component.translatable("itemGroup.growable_ores.test_group"))
                .build()); // build() no longer registers by itself

    }
}
