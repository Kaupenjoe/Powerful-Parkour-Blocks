package net.kaupenjoe.greygoo.block;

import net.kaupenjoe.greygoo.GreyGooMod;
import net.kaupenjoe.greygoo.block.custom.*;
import net.kaupenjoe.greygoo.item.ModCreativeModeTab;
import net.kaupenjoe.greygoo.item.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, GreyGooMod.MOD_ID);

    public static final RegistryObject<Block> GREY_GOO = registerBlock("grey_goo",
            () -> new GreyGooBlock(BlockBehaviour.Properties.of(Material.DIRT)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.GREY_GOO_TAB,"grey_goo");
    public static final RegistryObject<Block> DARK_GREY_GOO = registerBlock("dark_grey_goo",
            () -> new DarkGreyGooBlock(BlockBehaviour.Properties.of(Material.DIRT)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.GREY_GOO_TAB,"dark_grey_goo");
    public static final RegistryObject<Block> PINK_GOO = registerBlock("pink_goo",
            () -> new PinkGooBlock(BlockBehaviour.Properties.of(Material.DIRT)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.GREY_GOO_TAB,"pink_goo");
    public static final RegistryObject<Block> PURPLE_GOO = registerBlock("purple_goo",
            () -> new PurpleGooBlock(BlockBehaviour.Properties.of(Material.DIRT)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.GREY_GOO_TAB,"purple_goo");

    public static final RegistryObject<Block> RANDOM_GREY_GOO = registerBlock("random_grey_goo",
            () -> new RandomGreyGooBlock(BlockBehaviour.Properties.of(Material.DIRT)
                    .strength(6f).requiresCorrectToolForDrops().randomTicks()), ModCreativeModeTab.GREY_GOO_TAB,"random_grey_goo");
    public static final RegistryObject<Block> RANDOM_DARK_GREY_GOO = registerBlock("random_dark_grey_goo",
            () -> new RandomDarkGreyGooBlock(BlockBehaviour.Properties.of(Material.DIRT)
                    .strength(6f).requiresCorrectToolForDrops().randomTicks()), ModCreativeModeTab.GREY_GOO_TAB,"random_dark_grey_goo");
    public static final RegistryObject<Block> RANDOM_PINK_GOO = registerBlock("random_pink_goo",
            () -> new RandomPinkGooBlock(BlockBehaviour.Properties.of(Material.DIRT)
                    .strength(6f).requiresCorrectToolForDrops().randomTicks()), ModCreativeModeTab.GREY_GOO_TAB,"random_pink_goo");
    public static final RegistryObject<Block> RANDOM_PURPLE_GOO = registerBlock("random_purple_goo",
            () -> new RandomPurpleGooBlock(BlockBehaviour.Properties.of(Material.DIRT)
                    .strength(6f).requiresCorrectToolForDrops().randomTicks()), ModCreativeModeTab.GREY_GOO_TAB,"random_purple_goo");

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block,
                                                                     CreativeModeTab tab, String tooltip) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab, tooltip);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab, String tooltip) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)){
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

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
