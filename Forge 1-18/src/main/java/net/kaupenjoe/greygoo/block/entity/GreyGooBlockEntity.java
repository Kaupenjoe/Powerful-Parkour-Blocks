package net.kaupenjoe.greygoo.block.entity;

import net.kaupenjoe.greygoo.GreyGooMod;
import net.kaupenjoe.greygoo.block.ModBlocks;
import net.kaupenjoe.greygoo.util.GooUtils;
import net.kaupenjoe.greygoo.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class GreyGooBlockEntity extends BlockEntity {
    public GreyGooBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GREY_GOO.get(), pos, state);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, GreyGooBlockEntity entity) {
        if(!level.isClientSide() && level.getGameRules().getBoolean(GreyGooMod.GOO_SPREAD)) {
            Random randomSource = entity.getLevel().getRandom();
            if(randomSource.nextFloat() >= GooUtils.getSpreadChance((level.getGameRules().getInt(GreyGooMod.GOO_SPREAD_CHANCE)))) {
                for (BlockPos pos : GooUtils.getPositionsNextTo(blockPos)) {
                    if(!level.getBlockState(pos).is(ModTags.Blocks.GREY_GOO_NON_EATABLES)) {
                        level.setBlock(pos, ModBlocks.GREY_GOO.get().defaultBlockState(),2);
                    } else {
                        if(level.getBlockState(pos).getBlock() == Blocks.IRON_ORE ||
                                level.getBlockState(pos).getBlock() == Blocks.DEEPSLATE_IRON_ORE) {
                            ItemEntity itemEntity = new ItemEntity(level, blockPos.getX(), blockPos.getY(),
                                    blockPos.getZ(), new ItemStack(Items.IRON_NUGGET, randomSource.nextInt(3)));
                            level.addFreshEntity(itemEntity);

                            level.setBlock(pos, ModBlocks.GREY_GOO.get().defaultBlockState(),2);
                        }
                    }
                }

                level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 2);
            }
        }
    }
}
