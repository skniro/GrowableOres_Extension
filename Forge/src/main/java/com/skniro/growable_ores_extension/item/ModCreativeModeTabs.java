package com.skniro.growable_ores_extension.item;

import com.skniro.growable_ores_extension.GrowableOres;
import com.skniro.growable_ores_extension.block.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GrowableOres.MODID);

    public static final RegistryObject<CreativeModeTab> Growable_Ores_Group = CREATIVE_MODE_TABS.register("test_group",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack())
                    .title(Component.translatable("itemGroup.growable_ores.test_group"))
                    .displayItems((pParameters, pOutput) -> {
                    })
                    .build());





    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
