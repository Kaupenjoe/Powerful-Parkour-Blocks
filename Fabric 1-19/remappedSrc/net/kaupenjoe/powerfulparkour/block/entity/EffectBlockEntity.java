package net.kaupenjoe.powerfulparkour.block.entity;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.kaupenjoe.powerfulparkour.block.custom.EffectBlock;
import net.kaupenjoe.powerfulparkour.client.EffectBlockScreenHandler;
import net.kaupenjoe.powerfulparkour.networking.ModMessages;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class EffectBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory {
    private StatusEffect effect = StatusEffects.SPEED;
    public int id = 1;
    public int duration = 200;
    public int effectLevel = 0;

    public EffectBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.EFFECT_BLOCK, blockPos, blockState);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Effect Block");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int i, PlayerInventory inventory, PlayerEntity player) {
        PacketByteBuf data = PacketByteBufs.create();
        data.writeBlockPos(getPos());
        data.writeInt(id);
        data.writeInt(duration);
        data.writeInt(effectLevel);
        ServerPlayNetworking.send(((ServerPlayerEntity) player), ModMessages.EFFECT_DATA_SYNC_ID, data);
        
        return new EffectBlockScreenHandler(i, inventory, this);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putInt("effect.id", id);
        nbt.putInt("effect.duration", duration);
        nbt.putInt("effect.effectLevel", effectLevel);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        id = nbt.getInt("effect.id");
        duration = nbt.getInt("effect.duration");
        effectLevel = nbt.getInt("effect.effectLevel");

        // ParkourBlockMod.LOGGER.info("effect.id " + id);
        // ParkourBlockMod.LOGGER.info("effect.duration " + duration);
        // ParkourBlockMod.LOGGER.info("effect.effectLevel " + effectLevel);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    public void setEffect(int id) {
        this.effect = StatusEffect.byRawId(id);
        this.id = id;
        ParkourBlockMod.LOGGER.debug("Set Effect to " + effect);

        if(!world.isClient()) {
            world.setBlockState(this.pos, this.getCachedState().with(EffectBlock.EFFECT_DISPLAY, id), 3);
        }
    }

    public StatusEffect getEffect() {
        // ParkourBlockMod.LOGGER.info("Getting Effect " + effect);
        // ParkourBlockMod.LOGGER.info("Getting Effect id " + id);
        return StatusEffect.byRawId(id);
    }
}
