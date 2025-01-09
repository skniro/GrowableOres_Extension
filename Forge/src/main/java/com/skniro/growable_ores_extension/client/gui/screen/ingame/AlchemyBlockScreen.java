package com.skniro.growable_ores_extension.client.gui.screen.ingame;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.skniro.growable_ores_extension.GrowableOresExtension;
import com.skniro.growable_ores_extension.screen.AlchemyBlockScreenHandler;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static net.minecraft.client.gui.AbstractGui.blit;

@OnlyIn(Dist.CLIENT)
public class AlchemyBlockScreen extends ContainerScreen<AlchemyBlockScreenHandler> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(GrowableOresExtension.MODID, "textures/gui/container/cane_converter.png");

    public AlchemyBlockScreen(AlchemyBlockScreenHandler handler, PlayerInventory inventory, ITextComponent title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack context, float delta, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        int x = (width - this.xSize) / 2;
        int y = (height - this.ySize) / 2;
        this.blit(context, x, y, 0, 0, this.xSize, this.ySize);

        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(MatrixStack context, int x, int y) {
        if(container.isCrafting()) {
            blit(context, x + 73, y + 34, 176, 12, container.getScaledProgress(),45);
        }
        /*if(handler.hasFuel()){
            drawTexture(matrices, x + 18, y + 33 + 14 - handler.getScaledFuelProgress(), 176,
                    14 - handler.getScaledFuelProgress(), 14, handler.getScaledFuelProgress());
        }*/
    }

    @Override
    public void render(MatrixStack context , int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        this.renderHoveredTooltip(context, mouseX, mouseY);
    }
}

