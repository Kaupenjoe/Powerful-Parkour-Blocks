package net.kaupenjoe.powerfulparkour.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.kaupenjoe.powerfulparkour.networking.packets.EffectC2SSync;
import net.kaupenjoe.powerfulparkour.networking.packets.EffectDataS2CSync;
import net.minecraft.resources.ResourceLocation;

public class ModMessages {
    public static final ResourceLocation EFFECT_SYNC_ID = new ResourceLocation(ParkourBlockMod.MOD_ID, "effect_sync");
    public static final ResourceLocation EFFECT_DATA_SYNC_ID = new ResourceLocation(ParkourBlockMod.MOD_ID, "effect_data_sync");

    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(EFFECT_SYNC_ID, EffectC2SSync::receive);
    }

    public static void registerClient() {
        ClientPlayNetworking.registerGlobalReceiver(EFFECT_DATA_SYNC_ID, EffectDataS2CSync::receive);
    }
}
