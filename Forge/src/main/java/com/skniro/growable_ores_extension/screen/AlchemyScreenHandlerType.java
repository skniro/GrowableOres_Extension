package com.skniro.growable_ores_extension.screen;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AlchemyScreenHandlerType <T extends AbstractContainerMenu>{
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, GrowableOresExtension.MODID);

    public static final RegistryObject<MenuType<AlchemyBlockScreenHandler>> ALCHEMY =
            registerMenuType( "cane_converter_screen_handler", AlchemyBlockScreenHandler::new);

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(String name,
                                                                                                  IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void registeralchemyscreenhandlertype (IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
