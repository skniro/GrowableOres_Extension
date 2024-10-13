package com.skniro.growable_ores_extension.screen;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class AlchemyScreenHandlerType <T extends ScreenHandler>{
    public static final ScreenHandlerType<AlchemyBlockScreenHandler> ALCHEMY =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(GrowableOresExtension.MOD_ID, "cane_converter_screen_handler"),
                    new ExtendedScreenHandlerType<>(AlchemyBlockScreenHandler::new));

    public static void registeralchemyscreenhandlertype () {

    }
}
