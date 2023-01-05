package net.kaupenjoe.greygoo.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.kaupenjoe.greygoo.GreyGooMod;
import net.kaupenjoe.greygoo.block.custom.*;
import net.kaupenjoe.greygoo.item.ModCreativeModeTab;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModBlocks {
    public static final Block GREY_GOO = registerBlock("grey_goo",
            new GreyGooBlock(BlockBehaviour.Properties.of(Material.DIRT)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.GREY_GOO_TAB, "grey_goo");
    public static final Block DARK_GREY_GOO = registerBlock("dark_grey_goo",
            new DarkGreyGooBlock(BlockBehaviour.Properties.of(Material.DIRT)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.GREY_GOO_TAB, "dark_grey_goo");
    public static final Block PINK_GOO = registerBlock("pink_goo",
            new PinkGooBlock(BlockBehaviour.Properties.of(Material.DIRT)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.GREY_GOO_TAB, "pink_goo");
    public static final Block PURPLE_GOO = registerBlock("purple_goo",
            new PurpleGooBlock(BlockBehaviour.Properties.of(Material.DIRT)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.GREY_GOO_TAB, "purple_goo");

    public static final Block RANDOM_GREY_GOO = registerBlock("random_grey_goo",
            new RandomGreyGooBlock(BlockBehaviour.Properties.of(Material.DIRT)
                    .strength(6f).requiresCorrectToolForDrops().randomTicks()), ModCreativeModeTab.GREY_GOO_TAB, "random_grey_goo");
    public static final Block RANDOM_DARK_GREY_GOO = registerBlock("random_dark_grey_goo",
           new RandomDarkGreyGooBlock(BlockBehaviour.Properties.of(Material.DIRT)
                   .strength(6f).requiresCorrectToolForDrops().randomTicks()), ModCreativeModeTab.GREY_GOO_TAB, "random_dark_grey_goo");
    public static final Block RANDOM_PINK_GOO = registerBlock("random_pink_goo",
           new RandomPinkGooBlock(BlockBehaviour.Properties.of(Material.DIRT)
                   .strength(6f).requiresCorrectToolForDrops().randomTicks()), ModCreativeModeTab.GREY_GOO_TAB, "random_pink_goo");
    public static final Block RANDOM_PURPLE_GOO = registerBlock("random_purple_goo",
           new RandomPurpleGooBlock(BlockBehaviour.Properties.of(Material.DIRT)
                   .strength(6f).requiresCorrectToolForDrops().randomTicks()), ModCreativeModeTab.GREY_GOO_TAB, "random_purple_goo");

    private static Block registerBlock(String name, Block block, CreativeModeTab tab, String tooltip) {
        registerBlockItem(name, block, tab, tooltip);
        return Registry.register(Registry.BLOCK, new ResourceLocation(GreyGooMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, CreativeModeTab tab, String tooltip) {
        return Registry.register(Registry.ITEM, new ResourceLocation(GreyGooMod.MOD_ID, name),
            new BlockItem(block, new FabricItemSettings().group(tab)){
                @Override
                public void appendHoverText(ItemStack itemStack, @Nullable Level level,
                                            List<Component> list, TooltipFlag tooltipFlag) {
                    list.add(new TranslatableComponent("tooltip.greygoo.warning"));

                    if(Screen.hasShiftDown()) {
                        list.add(new TranslatableComponent("tooltip.greygoo." + tooltip));
                    } else {
                        list.add(new TranslatableComponent("tooltip.greygoo.shift"));
                    }

                    super.appendHoverText(itemStack, level, list, tooltipFlag);
                }
            });
    }

    public static void registerModBlocks() {
        System.out.println("Registering ModBlocks for " + GreyGooMod.MOD_ID);
    }
}
