package net.kaupenjoe.greygoo.block.custom;

import net.kaupenjoe.greygoo.GreyGooMod;
import net.kaupenjoe.greygoo.block.ModBlocks;
import net.kaupenjoe.greygoo.util.GooUtils;
import net.kaupenjoe.greygoo.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class RandomGreyGooBlock extends Block {
    public RandomGreyGooBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos blockPos, RandomSource randomSource) {
        if(!level.isClientSide() && level.getGameRules().getBoolean(GreyGooMod.GOO_SPREAD)) {
            for (BlockPos pos : GooUtils.getPositionsNextTo(blockPos)) {
                if(!level.getBlockState(pos).is(ModTags.Blocks.GREY_GOO_NON_EATABLES)) {
                    level.setBlock(pos, ModBlocks.RANDOM_GREY_GOO.get().defaultBlockState(),3);
                } else {
                    if(level.getBlockState(pos).getBlock() == Blocks.IRON_ORE ||
                            level.getBlockState(pos).getBlock() == Blocks.DEEPSLATE_IRON_ORE) {
                        ItemEntity itemEntity = new ItemEntity(level, blockPos.getX(), blockPos.getY(),
                                blockPos.getZ(), new ItemStack(Items.IRON_NUGGET, randomSource.nextIntBetweenInclusive(1, 3)));
                        level.addFreshEntity(itemEntity);

                        level.setBlock(pos, ModBlocks.RANDOM_GREY_GOO.get().defaultBlockState(),3);
                    }
                }
            }

            level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 3);
        }

        super.randomTick(state, level, blockPos, randomSource);
    }
}
