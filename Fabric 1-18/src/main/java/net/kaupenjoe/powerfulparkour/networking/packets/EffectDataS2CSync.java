package net.kaupenjoe.powerfulparkour.networking.packets;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.kaupenjoe.powerfulparkour.block.entity.EffectBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class EffectDataS2CSync {
    public static void receive(Minecraft client, ClientPacketListener handler,
                               FriendlyByteBuf buf, PacketSender responseSender) {
        // Here we are Client Side!
        BlockPos position = buf.readBlockPos();
        if(client.level.getBlockEntity(position) instanceof EffectBlockEntity blockEntity) {
            blockEntity.setEffect(buf.readInt());
            blockEntity.duration = buf.readInt();
            blockEntity.effectLevel = buf.readInt();
        }
    }
}
