package com.skniro.growable_ores_extension.recipe;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.skniro.growable_ores_extension.GrowableOresExtension;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.List;


public class AlchemyCraftingRecipe implements IRecipe<IInventory> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public AlchemyCraftingRecipe(NonNullList<Ingredient> recipeItems, ItemStack output, ResourceLocation id) {
        this.output = output;
        this.recipeItems = recipeItems;
        this.id = id;
    }

    @Override
    public boolean matches(IInventory inventory, World world) {
        if (world.isRemote) {
            return false;
        }
        return recipeItems.get(0).test(inventory.getStackInSlot(1));
    }


    @Override
    public ItemStack getCraftingResult(IInventory iInventory) {
        return output.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output;
    }


    @Override
    public NonNullList<Ingredient> getIngredients() {
/*        NonNullList<Ingredient> list = NonNullList.createWithCapacity(this.recipeItems.size());
        list.addAll(recipeItems);*/
        return recipeItems;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return AlchemyRecipeType.Cane_Converter_SERIALIZER.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements IRecipeType<AlchemyCraftingRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "cane_converter";
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AlchemyCraftingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =  new ResourceLocation(GrowableOresExtension.MODID,"cane_converter");
        @Override
        public AlchemyCraftingRecipe read(ResourceLocation resourceLocation, JsonObject jsonObject) {
            ItemStack output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(jsonObject, "result"));
            JsonArray ingredients = JSONUtils.getJsonArray(jsonObject, "ingredient");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.deserialize(ingredients.get(i)));
            }

            return new AlchemyCraftingRecipe(inputs, output, resourceLocation);
        }

        @Override
        public @Nullable AlchemyCraftingRecipe read(ResourceLocation resourceLocation, PacketBuffer friendlyByteBuf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(friendlyByteBuf.readInt(), Ingredient.EMPTY);
            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.read(friendlyByteBuf));
            }
            ItemStack output = friendlyByteBuf.readItemStack();
            return new AlchemyCraftingRecipe(inputs, output, resourceLocation);
        }

        @Override
        public void write(PacketBuffer buf, AlchemyCraftingRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.write(buf);
            }

            buf.writeItemStack(recipe.getRecipeOutput());
        }

        @SuppressWarnings("unchecked") // Need this wrapper, because generics
        private static <G> Class<G> castClass(Class<?> cls) {
            return (Class<G>)cls;
        }
    }
}




