package net.kaupenjoe.powerfulparkour.item;

import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItems {


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new ResourceLocation(ParkourBlockMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        System.out.println("Registering Mod Items for " + ParkourBlockMod.MOD_ID);
    }
}
