package net.kaupenjoe.powerfulparkour.block.custom;

import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TestBlock extends Block {
    protected static final VoxelShape COLLISION_SHAPE = Block.box(0.0, 1.0, 0.0, 16.0, 15.5, 16.0);
    protected static final VoxelShape OUTLINE_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);

    public TestBlock(Properties properties) {
        super(properties);
    }

    public VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return COLLISION_SHAPE;
    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return OUTLINE_SHAPE;
    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        if(entity.blockPosition().equals(blockPos.below(1)) ||
                entity.blockPosition().equals(blockPos.below(2)) ||
                entity.blockPosition().equals(blockPos.below(3)) ) {
            ParkourBlockMod.LOGGER.info("COIN!");
        }

        super.entityInside(blockState, level, blockPos, entity);
    }
}
