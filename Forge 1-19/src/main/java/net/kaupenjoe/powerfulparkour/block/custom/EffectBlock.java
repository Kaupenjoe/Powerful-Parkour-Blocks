package net.kaupenjoe.powerfulparkour.block.custom;

import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.kaupenjoe.powerfulparkour.block.entity.EffectBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class EffectBlock extends BaseEntityBlock {
    public static final IntegerProperty EFFECT_DISPLAY = IntegerProperty.create("effect", 1, 33);

    public EffectBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return defaultBlockState().setValue(EFFECT_DISPLAY, 1);
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player,
                                 InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if(!level.isClientSide()) {
            MenuProvider menuProvider = ((EffectBlockEntity) level.getBlockEntity(blockPos));
            if(menuProvider != null) {
                NetworkHooks.openScreen(((ServerPlayer)player), menuProvider, blockPos);
            } else {
                ParkourBlockMod.LOGGER.debug("NULL");
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState state, Entity entity) {
        if(entity instanceof LivingEntity livingEntity && !level.isClientSide()) {
            EffectBlockEntity blockEntity = ((EffectBlockEntity) level.getBlockEntity(blockPos));
            livingEntity.addEffect(new MobEffectInstance(blockEntity.getEffect(), blockEntity.duration, blockEntity.effectLevel));
        }

        super.stepOn(level, blockPos, state, entity);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new EffectBlockEntity(blockPos, blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(EFFECT_DISPLAY);
    }
}
