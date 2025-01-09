package com.skniro.growable_ores_extension;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GrowableOresExtension implements ModInitializer {
    public static final String MOD_ID = "growable_ores_extension";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModContent.registerItem();
        ModContent.registerBlock();
    }
}
