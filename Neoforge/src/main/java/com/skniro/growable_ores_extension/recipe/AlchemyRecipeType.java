package com.skniro.growable_ores_extension.recipe;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public interface AlchemyRecipeType<T extends Recipe<?>> {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, GrowableOresExtension.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, GrowableOresExtension.MODID);

    public static final Supplier<RecipeSerializer<AlchemyCraftingRecipe>> Cane_Converter_SERIALIZER = SERIALIZERS.register( "cane_converter", AlchemyCraftingRecipe.Serializer::new);
    public static final Supplier<RecipeType<AlchemyCraftingRecipe>> Cane_Converter_TYPE = TYPES.register( "cane_converter", () -> new RecipeType<AlchemyCraftingRecipe>() {
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

