package com.skniro.growable_ores_extension.screen;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AlchemyScreenHandlerType{
    public static final DeferredRegister<ContainerType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, GrowableOresExtension.MODID);

    public static final RegistryObject<ContainerType<AlchemyBlockScreenHandler>> ALCHEMY =
            registerMenuType( "cane_converter_screen_handler", AlchemyBlockScreenHandler::new);

    private static <T extends Container> RegistryObject<ContainerType<T>> registerMenuType(String name,
                                                                                           IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeContainerType.create(factory));
    }

    public static void registeralchemyscreenhandlertype (IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
