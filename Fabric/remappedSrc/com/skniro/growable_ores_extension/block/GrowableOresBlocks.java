package com.skniro.growable_ores_extension.block;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import com.skniro.growable_ores_extension.util.GrowableOresItemGroups;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class GrowableOresBlocks {
    public static final Block GrowableOres_Block =registerBlock("growableores_block",new Alchemyblock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)), GrowableOresItemGroups.Growable_Ores_Group);

    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.tryBuild(GrowableOresExtension.MOD_ID, name), block);
    }
    private static Block registerBlock(String name, Block block, ResourceKey<CreativeModeTab> tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.tryBuild(GrowableOresExtension.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block, ResourceKey<CreativeModeTab> tab) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.tryBuild(GrowableOresExtension.MOD_ID, name),
                new BlockItem(block, new Item.Properties()));
    }

    public static void registerGrowableOresBlocks() {
        GrowableOresExtension.LOGGER.info("Registering GrowableOres Blocks for " + GrowableOresExtension.MOD_ID);
    }
}
