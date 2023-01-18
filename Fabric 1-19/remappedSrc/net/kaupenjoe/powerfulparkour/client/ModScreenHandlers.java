package net.kaupenjoe.powerfulparkour.client;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static ScreenHandlerType<EffectBlockScreenHandler> EFFECT_SCREEN =
            new ExtendedScreenHandlerType<>(EffectBlockScreenHandler::new);

    public static void registerAllScreenHandlers() {
        Registry.register(Registry.MENU, new Identifier(ParkourBlockMod.MOD_ID, "effect_screen"),
                EFFECT_SCREEN);
    }
}
