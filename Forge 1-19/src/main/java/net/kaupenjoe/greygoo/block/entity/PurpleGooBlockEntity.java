package net.kaupenjoe.greygoo.block.entity;

import net.kaupenjoe.greygoo.GreyGooMod;
import net.kaupenjoe.greygoo.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PurpleGooBlockEntity extends BlockEntity {
    public PurpleGooBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PURPLE_GOO.get(), pos, state);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, PurpleGooBlockEntity entity) {
        if(!level.isClientSide() && level.getGameRules().getBoolean(GreyGooMod.GOO_SPREAD)) {
            RandomSource randomSource = entity.getLevel().getRandom();
            if(randomSource.nextFloat() >= getSpreadChance((level.getGameRules().getInt(GreyGooMod.GOO_SPREAD_CHANCE)))) {
                for (BlockPos pos : getPositionsNextTo(blockPos)) {
                    if(level.getBlockState(pos).getBlock() == Blocks.AIR) {
                        if(level.getBlockState(pos.above()).getBlock() == Blocks.AIR
                                && level.getBlockState(pos.below()).getBlock() != ModBlocks.PURPLE_GOO.get()
                                && level.getBlockState(pos.below()).getBlock() != ModBlocks.PINK_GOO.get()
                                && level.getBlockState(pos.below()).getBlock() != Blocks.AIR) {
                            level.setBlockAndUpdate(pos, ModBlocks.PURPLE_GOO.get().defaultBlockState());
                        }
                    }
                }
            }
        }
    }

    private static float getSpreadChance(int spreadChanceRule) {
        return 1.0f - (1.0f / (float) spreadChanceRule);
    }

    /*
    *
    public static void tick(Level level, BlockPos blockPos, BlockState blockState, GreyGooBlockEntity entity) {
        if(!level.isClientSide()) {
            RandomSource randomSource = entity.getLevel().getRandom();
            if(randomSource.nextFloat() >= 0.97f) {
                for (BlockPos pos : getPositionsNextTo(blockPos)) {
                    if(level.getBlockState(pos).getBlock() != ModBlocks.GREY_GOO.get() &&
                            level.getBlockState(pos).getBlock() != Blocks.BARRIER
                            && level.getBlockState(pos).getBlock() != Blocks.IRON_BLOCK) {
                        level.setBlockAndUpdate(pos, ModBlocks.GREY_GOO.get().defaultBlockState());
                    }
                }

                level.setBlockAndUpdate(blockPos, Blocks.BARRIER.defaultBlockState());
            }
        }
    }*/

    private static BlockPos[] getPositionsNextTo(BlockPos position) {
        return new BlockPos[] { position.above(), position.below(), position.east(),
                position.west(), position.north(), position.south(),
                position.offset(1, 1, 1),
                position.offset(-1, 1, 1),
                position.offset(-1, 1, -1),
                position.offset(1, 1, -1),

                position.offset(1, -1, 1),
                position.offset(-1, -1, 1),
                position.offset(-1, -1, -1),
                position.offset(1, -1, -1)
        };
    }
}
