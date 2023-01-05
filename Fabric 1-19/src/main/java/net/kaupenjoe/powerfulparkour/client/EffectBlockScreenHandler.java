package net.kaupenjoe.powerfulparkour.client;

import net.kaupenjoe.powerfulparkour.block.ModBlocks;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class EffectBlockScreenHandler extends AbstractContainerMenu {
    public final BlockEntity blockEntity;

    public EffectBlockScreenHandler(int syncId, Inventory inventory, FriendlyByteBuf buf) {
        this(syncId, inventory, inventory.player.level.getBlockEntity(buf.readBlockPos()));
    }

    public EffectBlockScreenHandler(int syncId, Inventory inventory, BlockEntity entity) {
        super(ModScreenHandlers.EFFECT_SCREEN, syncId);
        this.blockEntity = entity;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(player.level, blockEntity.getBlockPos()),
                player, ModBlocks.EFFECT_BLOCK);
    }
}
