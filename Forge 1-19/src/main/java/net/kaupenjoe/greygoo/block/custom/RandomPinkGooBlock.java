package net.kaupenjoe.greygoo.block.custom;

import net.kaupenjoe.greygoo.GreyGooMod;
import net.kaupenjoe.greygoo.block.ModBlocks;
import net.kaupenjoe.greygoo.util.GooUtils;
import net.kaupenjoe.greygoo.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class RandomPinkGooBlock extends Block {
    public RandomPinkGooBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos blockPos, RandomSource randomSource) {
        if(!level.isClientSide() && level.getGameRules().getBoolean(GreyGooMod.GOO_SPREAD)) {
            for (BlockPos pos : GooUtils.getPositionsNextTo(blockPos)) {
                if(level.getBlockState(pos).is(ModTags.Blocks.PINK_GOO_EATABLES)) {
                    level.setBlockAndUpdate(pos, ModBlocks.PINK_GOO.get().defaultBlockState());
                }
            }

            level.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
        }

        super.randomTick(state, level, blockPos, randomSource);
    }
}
