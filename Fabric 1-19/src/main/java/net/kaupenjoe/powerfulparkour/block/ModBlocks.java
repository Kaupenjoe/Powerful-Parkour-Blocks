package net.kaupenjoe.powerfulparkour.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.kaupenjoe.powerfulparkour.block.custom.EffectBlock;
import net.kaupenjoe.powerfulparkour.item.ModCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class ModBlocks {
    public static final Block EFFECT_BLOCK = registerBlock("effect_block",
            new EffectBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.PARKOUR_TAB);

    private static Block registerBlock(String name, Block block, CreativeModeTab tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new ResourceLocation(ParkourBlockMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, CreativeModeTab tab) {
        return Registry.register(Registry.ITEM, new ResourceLocation(ParkourBlockMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    public static void registerModBlocks() {
        System.out.println("Registering ModBlocks for " + ParkourBlockMod.MOD_ID);
    }
}
