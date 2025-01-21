package com.skniro.growable_ores_extension.compat.jei;
import com.skniro.growable_ores_extension.GrowableOresExtension;
import com.skniro.growable_ores_extension.block.GrowableOresBlocks;
import com.skniro.growable_ores_extension.recipe.AlchemyCraftingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class AlchemyCraftingCategory implements IRecipeCategory<AlchemyCraftingRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(GrowableOresExtension.MODID, "cane_converter");
    public static final ResourceLocation TEXTURE = new ResourceLocation(GrowableOresExtension.MODID,
            "textures/gui/container/cane_converter.png");
    public static final RecipeType<AlchemyCraftingRecipe> AlchemyCrafting_TYPE =
            new RecipeType<>(UID, AlchemyCraftingRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;

    public AlchemyCraftingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 175, 82);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(GrowableOresBlocks.GrowableOres_Block.get()));
    }

    @Override
    public RecipeType<AlchemyCraftingRecipe> getRecipeType() {
        return AlchemyCrafting_TYPE;
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("gui.growableores.cane_converter");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AlchemyCraftingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 52, 34).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 100, 34).addItemStack(recipe.getResultItem());
    }

    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends AlchemyCraftingRecipe> getRecipeClass() {
        return AlchemyCraftingRecipe.class;
    }

}