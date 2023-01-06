package net.kaupenjoe.powerfulparkour.client;

import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ParkourBlockMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModScreenHandlers.EFFECT_SCREEN.get(), EffectBlockScreen::new);
        });
    }
}