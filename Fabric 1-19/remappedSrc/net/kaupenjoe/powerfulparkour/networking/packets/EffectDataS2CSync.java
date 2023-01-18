package net.kaupenjoe.powerfulparkour.networking.packets;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.kaupenjoe.powerfulparkour.block.entity.EffectBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;

public class EffectDataS2CSync {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        // Here we are Client Side!
        BlockPos position = buf.readBlockPos();
        if(client.world.getBlockEntity(position) instanceof EffectBlockEntity blockEntity) {
            blockEntity.setEffect(buf.readInt());
            blockEntity.duration = buf.readInt();
            blockEntity.effectLevel = buf.readInt();
        }
    }
}
