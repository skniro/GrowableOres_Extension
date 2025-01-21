package com.skniro.growable_ores_extension.compat.rei;

import com.skniro.growable_ores_extension.block.GrowableOresBlocks;
import com.skniro.growable_ores_extension.client.gui.screen.ingame.AlchemyBlockScreen;
import com.skniro.growable_ores_extension.recipe.AlchemyCraftingRecipe;
import com.skniro.growable_ores_extension.recipe.AlchemyRecipeType;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;

public class GrowableOresEREIClientPlugin implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new AlchemyCraftingCategory());
        registry.addWorkstations(AlchemyCraftingCategory.UID, EntryStacks.of(GrowableOresBlocks.GrowableOres_Block));
    }
    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(AlchemyCraftingRecipe.class, AlchemyRecipeType.Cane_Converter_TYPE,
                AlchemyCraftingDisplay::new);
    }
    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(75, 30, 20, 30), AlchemyBlockScreen.class,
                AlchemyCraftingCategory.UID);
    }
}