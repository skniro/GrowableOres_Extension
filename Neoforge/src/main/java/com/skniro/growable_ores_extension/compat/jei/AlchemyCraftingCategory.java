package com.skniro.growable_ores_extension.compat.jei;
import com.skniro.growable_ores_extension.GrowableOresExtension;
import com.skniro.growable_ores_extension.block.GrowableOresBlocks;
import com.skniro.growable_ores_extension.recipe.AlchemyCraftingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class AlchemyCraftingCategory implements IRecipeCategory<AlchemyCraftingRecipe> {
    public static final ResourceLocation UID = ResourceLocation.tryBuild(GrowableOresExtension.MODID, "cane_converter");
    public static final ResourceLocation TEXTURE = ResourceLocation.tryBuild(GrowableOresExtension.MODID,
            "textures/gui/container/cane_converter.png");

    private final IDrawable background;
    private final IDrawable icon;

    public AlchemyCraftingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 175, 82);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(GrowableOresBlocks.GrowableOres_Block.get()));
    }

    public static final IRecipeType<AlchemyCraftingRecipe> AlchemyCrafting_TYPE =
            IRecipeType.create(UID, AlchemyCraftingRecipe.class);

    @Override
    public IRecipeType<AlchemyCraftingRecipe> getRecipeType() {
        return AlchemyCrafting_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("gui.growableores.cane_converter");
    }

    @Override
    public int getWidth() {
        return this.background.getWidth();
    }

    @Override
    public int getHeight() {
        return this.background.getHeight();
    }

    @Override
    public void draw(AlchemyCraftingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        this.background.draw(guiGraphics);
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AlchemyCraftingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 52, 34).add(recipe.ingredient());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 100, 34).add(recipe.output());
    }
}