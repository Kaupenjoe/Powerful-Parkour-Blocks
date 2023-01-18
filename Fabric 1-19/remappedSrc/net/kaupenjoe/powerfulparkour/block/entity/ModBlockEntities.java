package net.kaupenjoe.powerfulparkour.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.kaupenjoe.powerfulparkour.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static BlockEntityType<EffectBlockEntity> EFFECT_BLOCK;

    public static void registerBlockEntities() {
        EFFECT_BLOCK = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(ParkourBlockMod.MOD_ID, "effect_block_entity"),
                FabricBlockEntityTypeBuilder.create(EffectBlockEntity::new,
                        ModBlocks.EFFECT_BLOCK).build(null));
    }
}
