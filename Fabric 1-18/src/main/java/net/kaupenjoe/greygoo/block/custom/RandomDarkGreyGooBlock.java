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

public class RandomDarkGreyGooBlock extends Block {
    public RandomDarkGreyGooBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos blockPos, Random randomSource) {
        if(!level.isClientSide() && level.getGameRules().getBoolean(GreyGooMod.GOO_SPREAD)) {
            for (BlockPos pos : GooUtils.getPositionsNextTo(blockPos)) {
                if(level.getBlockState(pos).getBlock() != ModBlocks.RANDOM_DARK_GREY_GOO
                        && level.getBlockState(pos).getBlock() != Blocks.BARRIER
                        && level.getBlockState(pos).getBlock() != Blocks.IRON_BLOCK) {
                    level.setBlockAndUpdate(pos, ModBlocks.RANDOM_DARK_GREY_GOO.defaultBlockState());
                }
            }

            level.setBlockAndUpdate(blockPos, Blocks.BARRIER.defaultBlockState());
        }
        super.randomTick(state, level, blockPos, randomSource);
    }
}
