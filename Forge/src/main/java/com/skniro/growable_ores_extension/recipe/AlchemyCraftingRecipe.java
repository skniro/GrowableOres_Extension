package com.skniro.growable_ores_extension.recipe;


import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;


public class AlchemyCraftingRecipe implements Recipe<AlchemyCraftingRecipeInput> {
    final ItemStackTemplate output;
    final Ingredient ingredient;
    @Nullable
    private PlacementInfo ingredientPlacement;


    public AlchemyCraftingRecipe(Ingredient ingredients, ItemStackTemplate output) {
        this.output = output;
        this.ingredient = ingredients;
    }

    public Ingredient ingredient() {
        return this.ingredient;
    }

    public ItemStackTemplate output() {
        return this.output;
    }

    @Override
    public boolean matches(AlchemyCraftingRecipeInput input, Level world) {
        if (world.isClientSide()) {
            return false;
        }
        return this.ingredient.test(input.getItem(1));
    }

    @Override
    public ItemStack assemble(AlchemyCraftingRecipeInput input) {
        return output.create();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public RecipeSerializer<? extends Recipe<AlchemyCraftingRecipeInput>> getSerializer() {
        return AlchemyRecipeType.Cane_Converter_SERIALIZER.get();
    }

    @Override
    public RecipeType<? extends Recipe<AlchemyCraftingRecipeInput>> getType() {
        return AlchemyRecipeType.Cane_Converter_TYPE.get();
    }

    @Override
    public PlacementInfo placementInfo() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = PlacementInfo.create(this.ingredient);
        }

        return this.ingredientPlacement;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }

    public static final MapCodec<AlchemyCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.fieldOf("ingredient").forGetter((recipe) -> {
                return recipe.ingredient;
            }),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter((recipe) -> {
                return recipe.output;
            })
    ).apply(inst, AlchemyCraftingRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, AlchemyCraftingRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC, AlchemyCraftingRecipe::ingredient,
                    ItemStackTemplate.STREAM_CODEC, AlchemyCraftingRecipe::output,
                    AlchemyCraftingRecipe::new);

    public static final RecipeSerializer<AlchemyCraftingRecipe> SERIALIZER =
            new RecipeSerializer<>(CODEC, STREAM_CODEC);


    public MapCodec<AlchemyCraftingRecipe> codec() {
        return CODEC;
    }

    public StreamCodec<RegistryFriendlyByteBuf, AlchemyCraftingRecipe> streamCodec() {
        return STREAM_CODEC;
    }
}










