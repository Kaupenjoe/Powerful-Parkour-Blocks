package net.kaupenjoe.powerfulparkour.networking.packets;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.kaupenjoe.powerfulparkour.block.entity.EffectBlockEntity;
import net.kaupenjoe.powerfulparkour.client.EffectBlockScreenHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.effect.MobEffect;

public class EffectC2SSync {
    public static void receive(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler,
                               FriendlyByteBuf buf, PacketSender responseSender) {
        // Here we are Server Side!
        if(player.containerMenu != null && player.containerMenu instanceof EffectBlockScreenHandler menu) {
            ((EffectBlockEntity)menu.blockEntity).setEffect(buf.readInt());
            ((EffectBlockEntity)menu.blockEntity).duration = buf.readInt();
            ((EffectBlockEntity)menu.blockEntity).effectLevel = buf.readInt();
        }
    }
}
