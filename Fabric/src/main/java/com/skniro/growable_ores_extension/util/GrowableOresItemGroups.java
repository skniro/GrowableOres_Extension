package com.skniro.growable_ores_extension.util;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import com.skniro.growable_ores_extension.block.GrowableOresBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class GrowableOresItemGroups {
    public static final RegistryKey<ItemGroup> Growable_Ores_Group = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(GrowableOresExtension.MOD_ID, "test_group"));

    public static void vanilla_item() {
        Registry.register(Registries.ITEM_GROUP, Growable_Ores_Group, FabricItemGroup.builder()
                .icon(() -> new ItemStack(GrowableOresBlocks.GrowableOres_Block))
                .displayName(Text.translatable("itemGroup.growable_ores.test_group"))
                .build()); // build() no longer registers by itself

    }
}
