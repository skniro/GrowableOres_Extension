package com.skniro.growable_ores_extension.block.entity;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import com.skniro.growable_ores_extension.block.GrowableOresBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class AlchemyBlockEntityType {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, GrowableOresExtension.MODID);


    public static final RegistryObject<BlockEntityType<Alchemyblockentity>> ALCHEMY_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("alchemy_block", () -> BlockEntityType.Builder.of(
                    Alchemyblockentity::new, GrowableOresBlocks.GrowableOres_Block.get()).build(null));

    public static void registerBlockEntityType(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
