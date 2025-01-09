package com.skniro.growable_ores_extension.client;

import com.skniro.growable_ores_extension.block.entity.AlchemyBlockEntityType;
import com.skniro.growable_ores_extension.block.renderer.AlchemyblockentityRenderer;
import com.skniro.growable_ores_extension.client.gui.screen.ingame.AlchemyBlockScreen;
import com.skniro.growable_ores_extension.screen.AlchemyScreenHandlerType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;


@Environment(EnvType.CLIENT)
public class GrowableOresClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(AlchemyScreenHandlerType.ALCHEMY, AlchemyBlockScreen::new);
        BlockEntityRendererRegistry.INSTANCE.register(AlchemyBlockEntityType.ALCHEMY_BLOCK_ENTITY, AlchemyblockentityRenderer::new);
    }

}
