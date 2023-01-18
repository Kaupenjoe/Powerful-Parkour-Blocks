package net.kaupenjoe.powerfulparkour.networking.packets;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.kaupenjoe.powerfulparkour.block.entity.EffectBlockEntity;
import net.kaupenjoe.powerfulparkour.client.EffectBlockScreenHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class EffectC2SSync {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        // Here we are Server Side!
        if(player.currentScreenHandler != null && player.currentScreenHandler instanceof EffectBlockScreenHandler menu) {
            ((EffectBlockEntity)menu.blockEntity).setEffect(buf.readInt());
            ((EffectBlockEntity)menu.blockEntity).duration = buf.readInt();
            ((EffectBlockEntity)menu.blockEntity).effectLevel = buf.readInt();
        }
    }
}
