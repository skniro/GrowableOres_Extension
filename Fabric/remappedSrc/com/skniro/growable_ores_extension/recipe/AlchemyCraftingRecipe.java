package com.skniro.growable_ores_extension.recipe;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import java.util.List;


public class AlchemyCraftingRecipe implements Recipe<SimpleContainer> {
    private final ItemStack output;
    private final List<Ingredient> recipeItems;

    public AlchemyCraftingRecipe(List<Ingredient> recipeItems,ItemStack output) {
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleContainer inventory, Level world) {
        if (world.isClientSide()) {
            return false;
        }
        return recipeItems.get(0).test(inventory.getItem(1));
    }

    @Override
    public ItemStack craft(SimpleContainer inventory, RegistryAccess registryManager) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryManager) {
        return output;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.createWithCapacity(this.recipeItems.size());
        list.addAll(recipeItems);
        return list;
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
        public static final Codec<AlchemyCraftingRecipe> CODEC = RecordCodecBuilder.create(in -> in.group(
                validateAmount(Ingredient.DISALLOW_EMPTY_CODEC, 9).fieldOf("ingredient").forGetter(AlchemyCraftingRecipe::getIngredients),
                ItemStack.RECIPE_RESULT_CODEC.fieldOf("result").forGetter(r -> r.output)
        ).apply(in, AlchemyCraftingRecipe::new));

        private static Codec<List<Ingredient>> validateAmount(Codec<Ingredient> delegate, int max) {
            return ExtraCodecs.validate(ExtraCodecs.validate(
                    delegate.listOf(), list -> list.size() > max ? DataResult.error(() -> "Recipe has too many ingredients!") : DataResult.success(list)
            ), list -> list.isEmpty() ? DataResult.error(() -> "Recipe has no ingredients!") : DataResult.success(list));
        }

        @Override
        public Codec<AlchemyCraftingRecipe> codec() {
            return CODEC;
        }

        @Override
        public AlchemyCraftingRecipe fromNetwork(FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new AlchemyCraftingRecipe(inputs, output);
        }

        @Override
        public void write(FriendlyByteBuf buf, AlchemyCraftingRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buf);
            }

            buf.writeItem(recipe.getResultItem(null));
        }
    }
}




