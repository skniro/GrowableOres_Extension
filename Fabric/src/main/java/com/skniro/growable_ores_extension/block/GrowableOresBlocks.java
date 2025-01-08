package com.skniro.growable_ores_extension.block;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class GrowableOresBlocks {
    public static final Block GrowableOres_Block =registerBlock("growableores_block",new Alchemyblock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(3.0F, 3.0F)), ItemGroup.DECORATIONS);

    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registry.BLOCK, Identifier.of(GrowableOresExtension.MOD_ID, name), block);
    }
    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, Identifier.of(GrowableOresExtension.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        return Registry.register(Registry.ITEM, Identifier.of(GrowableOresExtension.MOD_ID, name),
                new BlockItem(block, new Item.Settings().group(tab)));
    }

    public static void registerGrowableOresBlocks() {
        GrowableOresExtension.LOGGER.info("Registering GrowableOres Blocks for " + GrowableOresExtension.MOD_ID);
    }
}
