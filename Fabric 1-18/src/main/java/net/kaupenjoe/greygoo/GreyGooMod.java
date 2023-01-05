package net.kaupenjoe.greygoo;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.kaupenjoe.greygoo.block.ModBlocks;
import net.kaupenjoe.greygoo.block.entity.ModBlockEntities;
import net.kaupenjoe.greygoo.item.ModItems;
import net.minecraft.world.level.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreyGooMod implements ModInitializer {
	public static final String MOD_ID = "greygoo";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final GameRules.Key<GameRules.BooleanValue> GOO_SPREAD =
			GameRuleRegistry.register("gooSpread", GameRules.Category.UPDATES,
					GameRuleFactory.createBooleanRule(true));
	public static final GameRules.Key<GameRules.IntegerValue> GOO_SPREAD_CHANCE =
			GameRuleRegistry.register("gooSpreadChance", GameRules.Category.UPDATES,
					GameRuleFactory.createIntRule(50));

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModBlockEntities.registerBlockEntities();
	}
}
