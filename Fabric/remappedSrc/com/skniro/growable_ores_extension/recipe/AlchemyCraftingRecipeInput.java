package com.skniro.growable_ores_extension.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public class AlchemyCraftingRecipeInput implements RecipeInput {
    private final ItemStack input;

    public AlchemyCraftingRecipeInput(ItemStack input) {
        this.input = input;
    }

    @Override
    public ItemStack getItem(int slot) {
        return input;
    }

    @Override
    public int size() {
        return 1;
    }
}