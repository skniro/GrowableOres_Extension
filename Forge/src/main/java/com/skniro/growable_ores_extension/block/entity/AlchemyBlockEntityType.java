package com.skniro.growable_ores_extension.block.entity;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import com.skniro.growable_ores_extension.block.GrowableOresBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;


public class AlchemyBlockEntityType {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, GrowableOresExtension.MODID);


    public static final RegistryObject<BlockEntityType<Alchemyblockentity>> ALCHEMY_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("alchemy_block", () -> new BlockEntityType<>(
                    Alchemyblockentity::new, Set.of(GrowableOresBlocks.GrowableOres_Block.get())));

    public static void registerBlockEntityType(BusGroup eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
