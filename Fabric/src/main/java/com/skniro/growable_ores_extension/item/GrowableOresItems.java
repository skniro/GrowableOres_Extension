package com.skniro.growable_ores_extension.item;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class GrowableOresItems {
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(GrowableOresExtension.MOD_ID, name), item);
    }

    public static void shield_item(){
      GrowableOresExtension.LOGGER.debug("register shield item.");
    }
}
