package com.skniro.growable_ores_extension.screen;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AlchemyScreenHandlerType <T extends AbstractContainerMenu>{
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(BuiltInRegistries.MENU, GrowableOresExtension.MODID);

    public static final Supplier<MenuType<AlchemyBlockScreenHandler>> ALCHEMY =
            registerMenuType( "cane_converter_screen_handler", AlchemyBlockScreenHandler::new);

    private static <T extends AbstractContainerMenu> Supplier<MenuType<T>> registerMenuType(String name,
                                                                                                  IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void registeralchemyscreenhandlertype (IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
