package net.kaupenjoe.powerfulparkour.block.entity;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.kaupenjoe.powerfulparkour.block.custom.EffectBlock;
import net.kaupenjoe.powerfulparkour.client.EffectBlockScreenHandler;
import net.kaupenjoe.powerfulparkour.networking.ModMessages;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class EffectBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory {
    private MobEffect effect = MobEffects.MOVEMENT_SPEED;
    public int id = 1;
    public int duration = 200;
    public int effectLevel = 0;

    public EffectBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.EFFECT_BLOCK, blockPos, blockState);
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Effect Block");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        FriendlyByteBuf data = PacketByteBufs.create();
        data.writeBlockPos(getBlockPos());
        data.writeInt(id);
        data.writeInt(duration);
        data.writeInt(effectLevel);
        ServerPlayNetworking.send(((ServerPlayer) player), ModMessages.EFFECT_DATA_SYNC_ID, data);
        
        return new EffectBlockScreenHandler(i, inventory, this);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.putInt("effect.id", id);
        nbt.putInt("effect.duration", duration);
        nbt.putInt("effect.effectLevel", effectLevel);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        id = nbt.getInt("effect.id");
        duration = nbt.getInt("effect.duration");
        effectLevel = nbt.getInt("effect.effectLevel");

        // ParkourBlockMod.LOGGER.info("effect.id " + id);
        // ParkourBlockMod.LOGGER.info("effect.duration " + duration);
        // ParkourBlockMod.LOGGER.info("effect.effectLevel " + effectLevel);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayer player, FriendlyByteBuf buf) {
        buf.writeBlockPos(this.worldPosition);
    }

    public void setEffect(int id) {
        this.effect = MobEffect.byId(id);
        this.id = id;
        ParkourBlockMod.LOGGER.debug("Set Effect to " + effect);

        if(!level.isClientSide()) {
            level.setBlock(this.worldPosition, this.getBlockState().setValue(EffectBlock.EFFECT_DISPLAY, id), 3);
        }
    }

    public MobEffect getEffect() {
        // ParkourBlockMod.LOGGER.info("Getting Effect " + effect);
        // ParkourBlockMod.LOGGER.info("Getting Effect id " + id);
        return MobEffect.byId(id);
    }
}
