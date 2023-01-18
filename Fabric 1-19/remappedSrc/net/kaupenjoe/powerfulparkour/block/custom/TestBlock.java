package net.kaupenjoe.powerfulparkour.block.custom;

import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class TestBlock extends Block {
    protected static final VoxelShape COLLISION_SHAPE = Block.createCuboidShape(0.0, 1.0, 0.0, 16.0, 15.5, 16.0);
    protected static final VoxelShape OUTLINE_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);

    public TestBlock(Settings properties) {
        super(properties);
    }

    public VoxelShape getCollisionShape(BlockState blockState, BlockView blockGetter, BlockPos blockPos, ShapeContext collisionContext) {
        return COLLISION_SHAPE;
    }

    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockGetter, BlockPos blockPos, ShapeContext collisionContext) {
        return OUTLINE_SHAPE;
    }

    @Override
    public void onEntityCollision(BlockState blockState, World level, BlockPos blockPos, Entity entity) {
        if(entity.getBlockPos().equals(blockPos.down(1)) ||
                entity.getBlockPos().equals(blockPos.down(2)) ||
                entity.getBlockPos().equals(blockPos.down(3)) ) {
            ParkourBlockMod.LOGGER.info("COIN!");
        }

        super.onEntityCollision(blockState, level, blockPos, entity);
    }
}
