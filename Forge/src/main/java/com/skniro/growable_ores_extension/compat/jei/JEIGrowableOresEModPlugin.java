package com.skniro.growable_ores_extension.compat.jei;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import com.skniro.growable_ores_extension.client.gui.screen.ingame.AlchemyBlockScreen;
import com.skniro.growable_ores_extension.recipe.AlchemyCraftingRecipe;
import com.skniro.growable_ores_extension.recipe.AlchemyRecipeType;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import java.util.List;

@JeiPlugin
public class JEIGrowableOresEModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(GrowableOresExtension.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new AlchemyCraftingCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        List<AlchemyCraftingRecipe> craftingRecipes = recipeManager.getAllRecipesFor(AlchemyRecipeType.Cane_Converter_TYPE.get());
        registration.addRecipes(AlchemyCraftingCategory.AlchemyCrafting_TYPE, craftingRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(AlchemyBlockScreen.class, 60, 30, 20, 30,
                AlchemyCraftingCategory.AlchemyCrafting_TYPE);
    }
}