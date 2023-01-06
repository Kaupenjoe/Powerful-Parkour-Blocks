package net.kaupenjoe.powerfulparkour.networking.packets;

import net.kaupenjoe.powerfulparkour.block.entity.EffectBlockEntity;
import net.kaupenjoe.powerfulparkour.client.EffectBlockScreenHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EffectC2SSync {
    int effectId;
    int duration;
    int effectLevel;

    public EffectC2SSync(int effectId, int duration, int effectLevel) {
        this.effectId = effectId;
        this.duration = duration;
        this.effectLevel = effectLevel;
    }

    public EffectC2SSync(FriendlyByteBuf buf) {
        this.effectId = buf.readInt();
        this.duration = buf.readInt();
        this.effectLevel = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(effectId);
        buf.writeInt(duration);
        buf.writeInt(effectLevel);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
        // Here we are Server Side!
        ServerPlayer player = context.getSender();
        if(player.containerMenu instanceof EffectBlockScreenHandler menu) {
            ((EffectBlockEntity)menu.blockEntity).setEffect(effectId);
            ((EffectBlockEntity)menu.blockEntity).duration = duration;
            ((EffectBlockEntity)menu.blockEntity).effectLevel = effectLevel;
            }
        });

        return true;
    }
}
