package net.kaupenjoe.greygoo;

import com.mojang.logging.LogUtils;
import net.kaupenjoe.greygoo.block.ModBlocks;
import net.kaupenjoe.greygoo.block.entity.ModBlockEntities;
import net.kaupenjoe.greygoo.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GreyGooMod.MOD_ID)
public class GreyGooMod {
    public static final String MOD_ID = "greygoo";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static GameRules.Key<GameRules.BooleanValue> GOO_SPREAD;
    public static GameRules.Key<GameRules.IntegerValue> GOO_SPREAD_CHANCE;

    public GreyGooMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModBlockEntities.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            GOO_SPREAD = GameRules.register("gooSpread", GameRules.Category.UPDATES, GameRules.BooleanValue.create(true));
            GOO_SPREAD_CHANCE = GameRules.register("gooSpreadChance", GameRules.Category.UPDATES, GameRules.IntegerValue.create(50));
        });
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
