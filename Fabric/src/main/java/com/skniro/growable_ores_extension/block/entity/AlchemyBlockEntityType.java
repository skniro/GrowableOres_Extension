package com.skniro.growable_ores_extension.block.entity;

import com.mojang.datafixers.types.Type;
import com.skniro.growable_ores_extension.GrowableOresExtension;
import com.skniro.growable_ores_extension.block.GrowableOresBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;


public class AlchemyBlockEntityType {
    public static final BlockEntityType<Alchemyblockentity> ALCHEMY_BLOCK_ENTITY;

    static {
        ALCHEMY_BLOCK_ENTITY = create("alchemy_block", FabricBlockEntityTypeBuilder.create(Alchemyblockentity::new, GrowableOresBlocks.GrowableOres_Block));

    }

    private static <T extends BlockEntity> BlockEntityType<T> create(String id, FabricBlockEntityTypeBuilder<T> builder) {
        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
        return (BlockEntityType) Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(GrowableOresExtension.MOD_ID,id), builder.build(null));
    }

    public static void registerMapleBlockEntityType() {
        GrowableOresExtension.LOGGER.debug("Registering MapleBlockEntityType for " + GrowableOresExtension.MOD_ID);
    }

}
