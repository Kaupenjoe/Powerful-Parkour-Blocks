package net.kaupenjoe.powerfulparkour.client;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;

public class ModScreenHandlers {
    public static MenuType<EffectBlockScreenHandler> EFFECT_SCREEN =
            new ExtendedScreenHandlerType<>(EffectBlockScreenHandler::new);

    public static void registerAllScreenHandlers() {
        Registry.register(Registry.MENU, new ResourceLocation(ParkourBlockMod.MOD_ID, "effect_screen"),
                EFFECT_SCREEN);
    }
}
