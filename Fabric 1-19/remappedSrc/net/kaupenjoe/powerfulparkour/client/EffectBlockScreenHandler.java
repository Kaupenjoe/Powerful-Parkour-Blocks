package net.kaupenjoe.powerfulparkour.client;

import net.kaupenjoe.powerfulparkour.block.ModBlocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;

public class EffectBlockScreenHandler extends ScreenHandler {
    public final BlockEntity blockEntity;

    public EffectBlockScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, inventory.player.world.getBlockEntity(buf.readBlockPos()));
    }

    public EffectBlockScreenHandler(int syncId, PlayerInventory inventory, BlockEntity entity) {
        super(ModScreenHandlers.EFFECT_SCREEN, syncId);
        this.blockEntity = entity;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int i) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(ScreenHandlerContext.create(player.world, blockEntity.getPos()),
                player, ModBlocks.EFFECT_BLOCK);
    }
}
