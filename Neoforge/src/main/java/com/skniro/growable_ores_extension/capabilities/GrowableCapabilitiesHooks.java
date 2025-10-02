package com.skniro.growable_ores_extension.capabilities;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import com.skniro.growable_ores_extension.block.entity.AlchemyBlockEntityType;
import net.minecraft.world.WorldlyContainer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.transfer.item.WorldlyContainerWrapper;

@EventBusSubscriber(modid = GrowableOresExtension.MODID)
public class GrowableCapabilitiesHooks {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.Item.BLOCK, AlchemyBlockEntityType.ALCHEMY_BLOCK_ENTITY.get(), (sidedContainer, side) -> {
            WorldlyContainer worldly = (WorldlyContainer) sidedContainer;
            return new WorldlyContainerWrapper(worldly, side);
        });
    }
}
