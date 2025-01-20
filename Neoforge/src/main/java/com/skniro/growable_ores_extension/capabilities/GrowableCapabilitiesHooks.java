package com.skniro.growable_ores_extension.capabilities;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import com.skniro.growable_ores_extension.block.entity.AlchemyBlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;

@EventBusSubscriber(modid = GrowableOresExtension.MODID, bus = EventBusSubscriber.Bus.MOD)
public class GrowableCapabilitiesHooks {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, AlchemyBlockEntityType.ALCHEMY_BLOCK_ENTITY.get(), (sidedContainer, side) -> {
            return side == null ? new InvWrapper(sidedContainer) : new SidedInvWrapper(sidedContainer, side);
        });
    }
}
