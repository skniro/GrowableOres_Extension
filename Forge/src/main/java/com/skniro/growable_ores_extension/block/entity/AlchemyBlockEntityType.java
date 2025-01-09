package com.skniro.growable_ores_extension.block.entity;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import com.skniro.growable_ores_extension.block.GrowableOresBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class AlchemyBlockEntityType {

    public static final DeferredRegister<TileEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, GrowableOresExtension.MODID);


    public static final RegistryObject<TileEntityType<Alchemyblockentity>> ALCHEMY_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("alchemy_block", () -> TileEntityType.Builder.create(
                    Alchemyblockentity::new, GrowableOresBlocks.GrowableOres_Block.get()).build(null));

    public static void registerBlockEntityType(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
