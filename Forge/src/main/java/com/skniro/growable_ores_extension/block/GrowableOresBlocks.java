package com.skniro.growable_ores_extension.block;

import com.skniro.growable_ores_extension.GrowableOresExtension;
import com.skniro.growable_ores_extension.item.MapleItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

public class GrowableOresBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, GrowableOresExtension.MOD_ID);

    public static final RegistryObject<Block> GrowableOres_Block =registerBlock("growableores_block",
            Alchemyblock::new, (BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));




    public static <B extends Block> RegistryObject<Block> register(String name, Function<BlockBehaviour.Properties, ? extends B> func, BlockBehaviour.Properties props) {
        return BLOCKS.register(name, () -> {
            return (Block)func.apply(props.setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(GrowableOresExtension.MOD_ID, name))));
        });
    }

    private static <B extends Block> RegistryObject<Block> registerBlockWithoutItem(String name, Function<BlockBehaviour.Properties, ? extends B> block, BlockBehaviour.Properties properties) {
        RegistryObject<Block> register = register(name, block, properties.setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(GrowableOresExtension.MOD_ID, name))));
        return register;
    }

    private static <B extends Block> RegistryObject<Block> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends B> block, BlockBehaviour.Properties properties) {
        RegistryObject<Block> bRegistryObject = registerBlockWithoutItem(name, block, properties);
        registerBlockItem(name, bRegistryObject);
        return bRegistryObject;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, Supplier<T> block) {
        return MapleItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().useBlockDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(GrowableOresExtension.MOD_ID, name)))));
    }

    public static void registerBlocks(BusGroup eventBus) {
        BLOCKS.register(eventBus);
    }
}
