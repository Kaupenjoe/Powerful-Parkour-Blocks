package net.kaupenjoe.powerfulparkour.networking.packets;

import net.kaupenjoe.powerfulparkour.block.entity.EffectBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EffectDataS2CSync {
    BlockPos position;
    int effectId;
    int duration;
    int effectLevel;

    public EffectDataS2CSync(BlockPos pos, int effectId, int duration, int effectLevel) {
        this.position = pos;
        this.effectId = effectId;
        this.duration = duration;
        this.effectLevel = effectLevel;
    }

    public EffectDataS2CSync(FriendlyByteBuf buf) {
        this.position = buf.readBlockPos();
        this.effectId = buf.readInt();
        this.duration = buf.readInt();
        this.effectLevel = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(position);
        buf.writeInt(effectId);
        buf.writeInt(duration);
        buf.writeInt(effectLevel);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // Here we are Client Side!
            if(Minecraft.getInstance().level.getBlockEntity(position) instanceof EffectBlockEntity blockEntity) {
                blockEntity.setEffect(effectId);
                blockEntity.duration = duration;
                blockEntity.effectLevel = effectLevel;
            }
        });
        return true;
    }
}
