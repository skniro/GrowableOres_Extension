package com.skniro.growable_ores_extension;

import com.skniro.growable_ores_extension.block.GrowableOresBlocks;
import com.skniro.growable_ores_extension.block.entity.AlchemyBlockEntityType;
import com.skniro.growable_ores_extension.block.renderer.AlchemyblockentityRenderer;
import com.skniro.growable_ores_extension.client.gui.screen.ingame.AlchemyBlockScreen;
import com.skniro.growable_ores_extension.item.MapleItems;
import com.skniro.growable_ores_extension.recipe.AlchemyRecipeType;
import com.skniro.growable_ores_extension.screen.AlchemyScreenHandlerType;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(GrowableOresExtension.MODID)
public class GrowableOresExtension {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "growable_ores_extension";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogManager.getLogger();


    public GrowableOresExtension() {
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GrowableConfig.GENERAL_SPEC, "growable_ores_config.toml");
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        // Register the Deferred Register to the mod event bus so blocks get registered


        AlchemyRecipeType.registerRecipes(modEventBus);
        AlchemyBlockEntityType.registerBlockEntityType(modEventBus);
        AlchemyScreenHandlerType.registeralchemyscreenhandlertype(modEventBus);
        GrowableOresBlocks.registerBlocks(modEventBus);
        MapleItems.registerModItems(modEventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ScreenManager.registerFactory(AlchemyScreenHandlerType.ALCHEMY.get(), AlchemyBlockScreen::new);
            ClientRegistry.bindTileEntityRenderer(AlchemyBlockEntityType.ALCHEMY_BLOCK_ENTITY.get(), AlchemyblockentityRenderer::new);
        }
    }



}
