package com.skniro.growable_ores_extension.screen;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public class AlchemyScreenHandlerType <T extends AbstractContainerMenu>{
    public static final MenuType<AlchemyBlockScreenHandler> ALCHEMY =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.tryBuild(GrowableOresExtension.MOD_ID, "cane_converter_screen_handler"),
                    new ExtendedScreenHandlerType<>(AlchemyBlockScreenHandler::new));

    public static void registeralchemyscreenhandlertype () {

    }
}
