package com.skniro.growable_ores_extension.recipe;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public interface AlchemyRecipeType {
    public static final DeferredRegister<IRecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, GrowableOresExtension.MODID);
    public static final RegistryObject<IRecipeSerializer<AlchemyCraftingRecipe>> Cane_Converter_SERIALIZER = SERIALIZERS.register( "cane_converter", AlchemyCraftingRecipe.Serializer::new);


    public static void registerRecipes(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}

