package net.kaupenjoe.greygoo.datagen;

import net.kaupenjoe.greygoo.GreyGooMod;
import net.kaupenjoe.greygoo.block.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlocksStateProvider extends BlockStateProvider {
    public ModBlocksStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, GreyGooMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.GREY_GOO.get());
        simpleBlock(ModBlocks.DARK_GREY_GOO.get());
        simpleBlock(ModBlocks.PINK_GOO.get());
        simpleBlock(ModBlocks.PURPLE_GOO.get());

        simpleBlock(ModBlocks.RANDOM_GREY_GOO.get());
        simpleBlock(ModBlocks.RANDOM_DARK_GREY_GOO.get());
        simpleBlock(ModBlocks.RANDOM_PINK_GOO.get());
        simpleBlock(ModBlocks.RANDOM_PURPLE_GOO.get());


        simpleBlockItem(ModBlocks.GREY_GOO.get(), cubeAll(ModBlocks.GREY_GOO.get()));
        simpleBlockItem(ModBlocks.DARK_GREY_GOO.get(), cubeAll(ModBlocks.DARK_GREY_GOO.get()));
        simpleBlockItem(ModBlocks.PINK_GOO.get(), cubeAll(ModBlocks.PINK_GOO.get()));
        simpleBlockItem(ModBlocks.PURPLE_GOO.get(), cubeAll(ModBlocks.PURPLE_GOO.get()));

        simpleBlockItem(ModBlocks.RANDOM_GREY_GOO.get(), cubeAll(ModBlocks.RANDOM_GREY_GOO.get()));
        simpleBlockItem(ModBlocks.RANDOM_DARK_GREY_GOO.get(), cubeAll(ModBlocks.RANDOM_DARK_GREY_GOO.get()));
        simpleBlockItem(ModBlocks.RANDOM_PINK_GOO.get(), cubeAll(ModBlocks.RANDOM_PINK_GOO.get()));
        simpleBlockItem(ModBlocks.RANDOM_PURPLE_GOO.get(), cubeAll(ModBlocks.RANDOM_PURPLE_GOO.get()));
    }
}
