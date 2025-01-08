package com.skniro.growable_ores_extension.client;

import com.google.common.collect.Maps;
import com.skniro.growable_ores_extension.block.entity.AlchemyBlockEntityType;
import com.skniro.growable_ores_extension.block.renderer.AlchemyblockentityRenderer;
import com.skniro.growable_ores_extension.client.gui.screen.ingame.AlchemyBlockScreen;
import com.skniro.growable_ores_extension.screen.AlchemyScreenHandlerType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;

import java.util.Map;


@Environment(EnvType.CLIENT)
public class GrowableOresClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(AlchemyScreenHandlerType.ALCHEMY, AlchemyBlockScreen::new);
        BlockEntityRendererRegistry.INSTANCE.register(AlchemyBlockEntityType.ALCHEMY_BLOCK_ENTITY, AlchemyblockentityRenderer::new);
    }

    private static final Map<BlockEntityType<?>, BlockEntityRendererFactory<?>> FACTORIES = Maps.newHashMap();
    private static <T extends BlockEntity> void register(BlockEntityType<? extends T> type, BlockEntityRendererFactory<T> factory) {
        FACTORIES.put(type, factory);
    }

}
