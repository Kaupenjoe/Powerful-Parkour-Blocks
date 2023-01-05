package net.kaupenjoe.greygoo.block.custom;

import net.kaupenjoe.greygoo.GreyGooMod;
import net.kaupenjoe.greygoo.block.ModBlocks;
import net.kaupenjoe.greygoo.util.GooUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class RandomPurpleGooBlock extends Block {
    public RandomPurpleGooBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos blockPos, Random randomSource) {
        if(!level.isClientSide() && level.getGameRules().getBoolean(GreyGooMod.GOO_SPREAD)) {
            for (BlockPos pos : GooUtils.getPositionsNextTo(blockPos)) {
                if(level.getBlockState(pos).getBlock() == Blocks.AIR) {
                    if(level.getBlockState(pos.above()).getBlock() == Blocks.AIR
                            && level.getBlockState(pos.below()).getBlock() != ModBlocks.RANDOM_PURPLE_GOO
                            && level.getBlockState(pos.below()).getBlock() != ModBlocks.RANDOM_PINK_GOO
                            && level.getBlockState(pos.below()).getBlock() != Blocks.AIR) {
                        level.setBlockAndUpdate(pos, ModBlocks.RANDOM_PURPLE_GOO.defaultBlockState());
                    }
                }
            }
        }

        super.randomTick(state, level, blockPos, randomSource);
    }
}
