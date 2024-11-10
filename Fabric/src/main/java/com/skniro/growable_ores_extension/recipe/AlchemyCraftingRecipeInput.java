package com.skniro.growable_ores_extension.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public class AlchemyCraftingRecipeInput implements RecipeInput {
    private final ItemStack input;

    public AlchemyCraftingRecipeInput(ItemStack input) {
        this.input = input;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return input;
    }

    @Override
    public int size() {
        return 1;
    }
}