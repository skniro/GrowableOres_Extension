package com.skniro.growable_ores_extension.recipe;


import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class AlchemyCraftingRecipe implements Recipe<AlchemyCraftingRecipeInput> {
    final ItemStack output;
    final Ingredient ingredient;
    @Nullable
    private IngredientPlacement ingredientPlacement;


    public AlchemyCraftingRecipe(Ingredient ingredients, ItemStack output) {
        this.output = output;
        this.ingredient = ingredients;
    }

    public Ingredient ingredient() {
        return this.ingredient;
    }

    public ItemStack output() {
        return this.output;
    }

    @Override
    public boolean matches(AlchemyCraftingRecipeInput input, World world) {
        if (world.isClient()) {
            return false;
        }
        return this.ingredient.test(input.getStackInSlot(1));
    }

    @Override
    public ItemStack craft(AlchemyCraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<? extends Recipe<AlchemyCraftingRecipeInput>> getSerializer() {
        return AlchemyRecipeType.Cane_Converter_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<AlchemyCraftingRecipeInput>> getType() {
        return AlchemyRecipeType.Cane_Converter_TYPE;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = IngredientPlacement.forSingleSlot(this.ingredient);
        }

        return this.ingredientPlacement;
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return null;
    }

    public static class Serializer implements RecipeSerializer<AlchemyCraftingRecipe> {
        public static final MapCodec<AlchemyCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter((recipe) -> {
                    return recipe.ingredient;
                }),
                ItemStack.CODEC.fieldOf("result").forGetter((recipe) -> {
                    return recipe.output;
                })
        ).apply(inst, AlchemyCraftingRecipe::new));

        public static final PacketCodec<RegistryByteBuf, AlchemyCraftingRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, AlchemyCraftingRecipe::ingredient,
                        ItemStack.PACKET_CODEC, AlchemyCraftingRecipe::output,
                        AlchemyCraftingRecipe::new);

        public Serializer() {
        }

        public MapCodec<AlchemyCraftingRecipe> codec() {
            return CODEC;
        }

        public PacketCodec<RegistryByteBuf, AlchemyCraftingRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}









