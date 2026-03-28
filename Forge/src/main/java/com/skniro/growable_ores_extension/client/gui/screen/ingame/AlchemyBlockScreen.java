package com.skniro.growable_ores_extension.client.gui.screen.ingame;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import com.skniro.growable_ores_extension.screen.AlchemyBlockScreenHandler;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class AlchemyBlockScreen extends AbstractContainerScreen<AlchemyBlockScreenHandler> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(GrowableOresExtension.MOD_ID, "textures/gui/container/cane_converter.png");

    public AlchemyBlockScreen(AlchemyBlockScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleLabelX = (imageWidth - font.width(title)) / 2;
    }

    @Override
    public void extractBackground(final GuiGraphicsExtractor context, final int mouseX, final int mouseY, final float delta) {
        //RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        //RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        context.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, 0, 0, imageWidth, imageHeight,256,256);

        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(GuiGraphicsExtractor context, int x, int y) {
        if(menu.isCrafting()) {
            context.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x + 73, y + 34, 176, 12, menu.getScaledProgress(),45,256,256);
        }
        /*if(handler.hasFuel()){
            drawTexture(matrices, x + 18, y + 33 + 14 - handler.getScaledFuelProgress(), 176,
                    14 - handler.getScaledFuelProgress(), 14, handler.getScaledFuelProgress());
        }*/
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor context , int mouseX, int mouseY, float delta) {
        super.extractRenderState(context, mouseX, mouseY, delta);
        extractTooltip(context, mouseX, mouseY);
    }
}

