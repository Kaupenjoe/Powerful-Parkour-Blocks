package net.kaupenjoe.greygoo.block.entity;

import net.kaupenjoe.greygoo.GreyGooMod;
import net.kaupenjoe.greygoo.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DarkGreyGooBlockEntity extends BlockEntity {
    public DarkGreyGooBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DARK_GREY_GOO.get(), pos, state);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, DarkGreyGooBlockEntity entity) {
        if(!level.isClientSide() && level.getGameRules().getBoolean(GreyGooMod.GOO_SPREAD)) {
            RandomSource randomSource = entity.getLevel().getRandom();
            if(randomSource.nextFloat() >= getSpreadChance((level.getGameRules().getInt(GreyGooMod.GOO_SPREAD_CHANCE)))) {
                for (BlockPos pos : getPositionsNextTo(blockPos)) {
                    if(level.getBlockState(pos).getBlock() != ModBlocks.DARK_GREY_GOO.get()
                            && level.getBlockState(pos).getBlock() != Blocks.BARRIER
                            && level.getBlockState(pos).getBlock() != Blocks.IRON_BLOCK) {
                        level.setBlockAndUpdate(pos, ModBlocks.DARK_GREY_GOO.get().defaultBlockState());
                    }
                }

                level.setBlockAndUpdate(blockPos, Blocks.BARRIER.defaultBlockState());
            }
        }
    }

    private static float getSpreadChance(int spreadChanceRule) {
        return 1.0f - (1.0f / (float) spreadChanceRule);
    }

    private static BlockPos[] getPositionsNextTo(BlockPos position) {
        return new BlockPos[] { position.above(), position.below(), position.east(),
                position.west(), position.north(), position.south()};
    }
}
