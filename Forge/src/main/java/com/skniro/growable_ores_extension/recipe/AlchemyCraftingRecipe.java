package com.skniro.growable_ores_extension.recipe;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;



public class AlchemyCraftingRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final List<Ingredient> recipeItems;

    public AlchemyCraftingRecipe(List<Ingredient> recipeItems,ItemStack output, ResourceLocation id) {
        this.output = output;
        this.recipeItems = recipeItems;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer inventory, Level world) {
        if (world.isClientSide()) {
            return false;
        }
        return recipeItems.get(0).test(inventory.getItem(1));
    }



    @Override
    public ItemStack assemble(SimpleContainer inventory, RegistryAccess registryManager) {
        return output.copy();
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
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return AlchemyRecipeType.Cane_Converter_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return AlchemyRecipeType.Cane_Converter_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<AlchemyCraftingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        @Override
        public AlchemyCraftingRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(jsonObject, "ingredient");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new AlchemyCraftingRecipe(inputs, output, resourceLocation);
        }

        @Override
        public @Nullable AlchemyCraftingRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(friendlyByteBuf.readInt(), Ingredient.EMPTY);
            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(friendlyByteBuf));
            }
            ItemStack output = friendlyByteBuf.readItem();
            return new AlchemyCraftingRecipe(inputs, output, resourceLocation);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, AlchemyCraftingRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buf);
            }

            buf.writeItem(recipe.getResultItem(null));
        }
    }
}




