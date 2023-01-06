package net.kaupenjoe.powerfulparkour;

import net.fabricmc.api.ModInitializer;
import net.kaupenjoe.powerfulparkour.block.ModBlocks;
import net.kaupenjoe.powerfulparkour.block.entity.ModBlockEntities;
import net.kaupenjoe.powerfulparkour.client.ModScreenHandlers;
import net.kaupenjoe.powerfulparkour.item.ModItems;
import net.kaupenjoe.powerfulparkour.networking.ModMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParkourBlockMod implements ModInitializer {
	public static final String MOD_ID = "kjs_parkour_block_mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModMessages.register();

		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerAllScreenHandlers();
	}
}
