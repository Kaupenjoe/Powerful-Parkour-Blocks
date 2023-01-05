package net.kaupenjoe.greygoo.block.entity;

import net.kaupenjoe.greygoo.GreyGooMod;
import net.kaupenjoe.greygoo.block.ModBlocks;
import net.kaupenjoe.greygoo.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class PinkGooBlockEntity extends BlockEntity {
    public PinkGooBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PINK_GOO.get(), pos, state);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, PinkGooBlockEntity entity) {
        if(!level.isClientSide() && level.getGameRules().getBoolean(GreyGooMod.GOO_SPREAD)) {
            Random randomSource = entity.getLevel().getRandom();
            if(randomSource.nextFloat() >= getSpreadChance((level.getGameRules().getInt(GreyGooMod.GOO_SPREAD_CHANCE)))) {
                for (BlockPos pos : getPositionsNextTo(blockPos)) {
                    if(level.getBlockState(pos).is(ModTags.Blocks.PINK_GOO_EATABLES)) {
                        level.setBlockAndUpdate(pos, ModBlocks.PINK_GOO.get().defaultBlockState());
                    }
                }

                level.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
            }
        }
    }

    private static float getSpreadChance(int spreadChanceRule) {
        return 1.0f - (1.0f / (float) (spreadChanceRule - 5));
    }

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
