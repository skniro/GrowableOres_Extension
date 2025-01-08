package com.skniro.growable_ores_extension.recipe;

import com.skniro.growable_ores_extension.GrowableOresExtension;
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
    public static final RegistryObject<RecipeSerializer<AlchemyCraftingRecipe>> Cane_Converter_SERIALIZER = SERIALIZERS.register( "cane_converter", AlchemyCraftingRecipe.Serializer::new);


    public static void registerRecipes(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}

