package com.skniro.growable_ores_extension.recipe;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public interface AlchemyRecipeType<T extends Recipe<?>> {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, GrowableOresExtension.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, GrowableOresExtension.MODID);

    public static final RegistryObject<RecipeSerializer<AlchemyCraftingRecipe>> Cane_Converter_SERIALIZER = SERIALIZERS.register( "cane_converter", AlchemyCraftingRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<AlchemyCraftingRecipe>> Cane_Converter_TYPE = TYPES.register( "cane_converter", () -> new RecipeType<AlchemyCraftingRecipe>() {
                @Override
                public String toString() {
                    return "cane_converter";
                }
            });

    public static void registerRecipes(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}

