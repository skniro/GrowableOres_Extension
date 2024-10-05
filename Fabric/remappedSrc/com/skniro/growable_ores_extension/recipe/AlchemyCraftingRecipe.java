package com.skniro.growable_ores_extension.recipe;


import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.recipe.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;



    public record AlchemyCraftingRecipe(Ingredient inputItem, ItemStack output) implements Recipe<AlchemyCraftingRecipeInput> {
        @Override
        public NonNullList<Ingredient> getIngredients() {
            NonNullList<Ingredient> list = NonNullList.create();
            list.add(this.inputItem);
            return list;
        }
        @Override
        public boolean matches(AlchemyCraftingRecipeInput input, Level world) {
            if(world.isClientSide()) {
                return false;
            }
            return inputItem.test(input.getItem(1));
        }
        @Override
        public ItemStack craft(AlchemyCraftingRecipeInput input, HolderLookup.Provider lookup) {
            return output.copy();
        }
        @Override
        public boolean canCraftInDimensions(int width, int height) {
            return true;
        }
        @Override
        public ItemStack getResultItem(HolderLookup.Provider registriesLookup) {
            return output;
        }
        @Override
        public RecipeSerializer<?> getSerializer() {
            return AlchemyRecipeType.Cane_Converter_SERIALIZER;
        }
        @Override
        public RecipeType<?> getType() {
            return AlchemyRecipeType.Cane_Converter_TYPE;
        }
        public static class Serializer implements RecipeSerializer<AlchemyCraftingRecipe> {
            public static final MapCodec<AlchemyCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                    Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(AlchemyCraftingRecipe::inputItem),
                    ItemStack.CODEC.fieldOf("result").forGetter(AlchemyCraftingRecipe::output)
            ).apply(inst, AlchemyCraftingRecipe::new));
            public static final StreamCodec<RegistryFriendlyByteBuf, AlchemyCraftingRecipe> STREAM_CODEC =
                    StreamCodec.composite(
                            Ingredient.CONTENTS_STREAM_CODEC, AlchemyCraftingRecipe::inputItem,
                            ItemStack.STREAM_CODEC, AlchemyCraftingRecipe::output,
                            AlchemyCraftingRecipe::new);
            @Override
            public MapCodec<AlchemyCraftingRecipe> codec() {
                return CODEC;
            }
            @Override
            public StreamCodec<RegistryFriendlyByteBuf, AlchemyCraftingRecipe> streamCodec() {
                return STREAM_CODEC;
            }
        }
    }




