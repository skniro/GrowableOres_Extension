package com.skniro.growable_ores_extension.block;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import com.skniro.growable_ores_extension.util.GrowableOresItemGroups;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class GrowableOresBlocks {
    public static final Block GrowableOres_Block =registerBlock("growableores_block",new Alchemyblock(AbstractBlock.Settings.create().requiresTool().strength(3.0F, 3.0F)), GrowableOresItemGroups.Growable_Ores_Group);

    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(GrowableOresExtension.MOD_ID, name), block);
    }
    private static Block registerBlock(String name, Block block, RegistryKey<ItemGroup> tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registries.BLOCK, Identifier.of(GrowableOresExtension.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block, RegistryKey<ItemGroup> tab) {
        return Registry.register(Registries.ITEM, Identifier.of(GrowableOresExtension.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerGrowableOresBlocks() {
        GrowableOresExtension.LOGGER.info("Registering GrowableOres Blocks for " + GrowableOresExtension.MOD_ID);
    }
}
