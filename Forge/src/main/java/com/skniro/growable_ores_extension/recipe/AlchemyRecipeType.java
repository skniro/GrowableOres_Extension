package com.skniro.growable_ores_extension.recipe;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public interface AlchemyRecipeType<T extends Recipe<?>> {
    public static final RecipeSerializer<AlchemyCraftingRecipe> Cane_Converter_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(GrowableOresExtension.MOD_ID, "cane_converter"), new AlchemyCraftingRecipe.Serializer());
    public static final RecipeType<AlchemyCraftingRecipe> Cane_Converter_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(GrowableOresExtension.MOD_ID, "cane_converter"), new RecipeType<>() {
                @Override
                public String toString() {
                    return "cane_converter";
                }
            });
    public static void registerRecipes() {
        GrowableOresExtension.LOGGER.info("Registering Custom Recipes for " + GrowableOresExtension.MOD_ID);
    }
}

