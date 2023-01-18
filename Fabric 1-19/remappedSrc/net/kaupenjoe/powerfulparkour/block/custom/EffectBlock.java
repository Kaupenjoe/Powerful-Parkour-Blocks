package net.kaupenjoe.powerfulparkour.block.custom;

import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.kaupenjoe.powerfulparkour.block.entity.EffectBlockEntity;
import net.kaupenjoe.powerfulparkour.client.EffectBlockScreen;
import net.kaupenjoe.powerfulparkour.client.TestScreen;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EffectBlock extends BlockWithEntity {
    public static final IntProperty EFFECT_DISPLAY = IntProperty.of("effect", 1, 33);

    public EffectBlock(Settings properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext blockPlaceContext) {
        return getDefaultState().with(EFFECT_DISPLAY, 1);
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState blockState, World level, BlockPos blockPos, PlayerEntity player,
                                 Hand interactionHand, BlockHitResult blockHitResult) {
        if(!level.isClient()) {
            NamedScreenHandlerFactory menuProvider = ((EffectBlockEntity) level.getBlockEntity(blockPos));
            if(menuProvider != null) {
                player.openHandledScreen(menuProvider);
            } else {
                ParkourBlockMod.LOGGER.info("NULL");
            }
        }

        return super.onUse(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    public void onSteppedOn(World level, BlockPos blockPos, BlockState state, Entity entity) {
        if(entity instanceof LivingEntity livingEntity && !level.isClient()) {
            EffectBlockEntity blockEntity = ((EffectBlockEntity) level.getBlockEntity(blockPos));
            livingEntity.addStatusEffect(new StatusEffectInstance(blockEntity.getEffect(), blockEntity.duration, blockEntity.effectLevel));
        }

        super.onSteppedOn(level, blockPos, state, entity);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new EffectBlockEntity(blockPos, blockState);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(EFFECT_DISPLAY);
    }
}
