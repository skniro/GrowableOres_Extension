package com.skniro.growable_ores_extension;


import com.skniro.growable_ores_extension.block.entity.AlchemyBlockEntityType;
import com.skniro.growable_ores_extension.item.GrowableOresItems;
import com.skniro.growable_ores_extension.recipe.AlchemyRecipeType;
import com.skniro.growable_ores_extension.screen.AlchemyScreenHandlerType;


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

}
