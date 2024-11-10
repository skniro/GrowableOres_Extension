package com.skniro.growable_ores_extension;


import com.skniro.growable_ores_extension.block.GrowableOresBlocks;
import com.skniro.growable_ores_extension.block.entity.AlchemyBlockEntityType;
import com.skniro.growable_ores_extension.item.GrowableOresItems;
import com.skniro.growable_ores_extension.recipe.AlchemyRecipeType;
import com.skniro.growable_ores_extension.screen.AlchemyScreenHandlerType;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;


public class ModContent {


    public static void registerItem(){
        GrowableOresItems.shield_item();
    }
    public static void registerBlock(){
        GrowableOresBlocks.registerGrowableOresBlocks();
        AlchemyRecipeType.registerRecipes();
        AlchemyBlockEntityType.registerMapleBlockEntityType();
        AlchemyScreenHandlerType.registeralchemyscreenhandlertype();
    }
    public static void CreativeTab() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(content -> {
            content.accept(GrowableOresBlocks.GrowableOres_Block);

        });
    }
}
