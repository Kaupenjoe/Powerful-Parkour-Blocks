package net.kaupenjoe.powerfulparkour;

import net.fabricmc.api.ClientModInitializer;
import net.kaupenjoe.powerfulparkour.client.EffectBlockScreen;
import net.kaupenjoe.powerfulparkour.client.ModScreenHandlers;
import net.kaupenjoe.powerfulparkour.networking.ModMessages;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ParkourBlockClientMod implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HandledScreens.register(ModScreenHandlers.EFFECT_SCREEN, EffectBlockScreen::new);

		ModMessages.registerClient();
	}
}
