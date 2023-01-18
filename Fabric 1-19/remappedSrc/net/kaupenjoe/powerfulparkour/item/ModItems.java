package net.kaupenjoe.powerfulparkour.item;

import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(ParkourBlockMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        System.out.println("Registering Mod Items for " + ParkourBlockMod.MOD_ID);
    }
}
