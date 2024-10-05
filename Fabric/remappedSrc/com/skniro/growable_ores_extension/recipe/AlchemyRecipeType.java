package com.skniro.growable_ores_extension.recipe;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public interface AlchemyRecipeType<T extends Recipe<?>> {
    public static final RecipeSerializer<AlchemyCraftingRecipe> Cane_Converter_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(GrowableOresExtension.MOD_ID, "cane_converter"), new AlchemyCraftingRecipe.Serializer());
    public static final RecipeType<AlchemyCraftingRecipe> Cane_Converter_TYPE = Registry.register(
            BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(GrowableOresExtension.MOD_ID, "cane_converter"), new RecipeType<>() {
                @Override
                public String toString() {
                    return "cane_converter";
                }
            });
    public static void registerRecipes() {
        GrowableOresExtension.LOGGER.info("Registering Custom Recipes for " + GrowableOresExtension.MOD_ID);
    }
}

