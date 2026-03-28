package com.skniro.growable_ores_extension.block.entity;

import com.mojang.datafixers.types.Type;
import com.skniro.growable_ores_extension.GrowableOresExtension;
import com.skniro.growable_ores_extension.block.GrowableOresBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;


public class AlchemyBlockEntityType {
    public static final BlockEntityType<Alchemyblockentity> ALCHEMY_BLOCK_ENTITY;

    static {
        ALCHEMY_BLOCK_ENTITY = create("alchemy_block", FabricBlockEntityTypeBuilder.create(Alchemyblockentity::new, GrowableOresBlocks.GrowableOres_Block));

    }

    private static <T extends BlockEntity> BlockEntityType create(String id, FabricBlockEntityTypeBuilder<T> builder) {
        Type<?> type = Util.fetchChoiceType(References.BLOCK_ENTITY, id);
        return (BlockEntityType) Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(GrowableOresExtension.MOD_ID,id), builder.build(null));
    }

    public static void registerMapleBlockEntityType() {
        GrowableOresExtension.LOGGER.debug("Registering MapleBlockEntityType for " + GrowableOresExtension.MOD_ID);
    }

}
