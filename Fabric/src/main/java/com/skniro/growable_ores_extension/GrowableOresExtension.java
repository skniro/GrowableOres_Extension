package com.skniro.growable_ores_extension;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class GrowableOresExtension implements ModInitializer {
    public static final String MOD_ID = "growable_ores_extension";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModContent.registerItem();
        ModContent.registerBlock();
    }
}
