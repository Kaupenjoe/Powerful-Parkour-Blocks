package net.kaupenjoe.powerfulparkour.item;

import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.kaupenjoe.powerfulparkour.block.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ParkourBlockMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTab {
    public static CreativeModeTab PARKOUR_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        ModCreativeModeTab.PARKOUR_TAB = event.registerCreativeModeTab(new ResourceLocation(ParkourBlockMod.MOD_ID, "effect_tab"),
                builder ->  builder.icon(() -> new ItemStack(ModBlocks.EFFECT_BLOCK.get())).title(Component.literal("Parkour Block Tab")).build());
    }
}