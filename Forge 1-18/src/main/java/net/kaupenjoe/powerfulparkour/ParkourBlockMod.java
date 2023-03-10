package net.kaupenjoe.powerfulparkour;

import net.kaupenjoe.powerfulparkour.block.ModBlocks;
import net.kaupenjoe.powerfulparkour.block.entity.ModBlockEntities;
import net.kaupenjoe.powerfulparkour.client.ModScreenHandlers;
import net.kaupenjoe.powerfulparkour.item.ModItems;
import net.kaupenjoe.powerfulparkour.networking.ModMessages;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ParkourBlockMod.MOD_ID)
public class ParkourBlockMod {
	public static final String MOD_ID = "kjs_parkour_block_mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public ParkourBlockMod() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModItems.register(modEventBus);
		ModBlocks.register(modEventBus);

		ModMessages.register();

		ModBlockEntities.register(modEventBus);
		ModScreenHandlers.register(modEventBus);

		modEventBus.addListener(this::commonSetup);
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			ModMessages.register();
		});
	}
}
