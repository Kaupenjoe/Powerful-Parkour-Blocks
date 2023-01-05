package net.kaupenjoe.powerfulparkour.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.kaupenjoe.powerfulparkour.ParkourBlockMod;
import net.kaupenjoe.powerfulparkour.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
    public static BlockEntityType<EffectBlockEntity> EFFECT_BLOCK;

    public static void registerBlockEntities() {
        EFFECT_BLOCK = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new ResourceLocation(ParkourBlockMod.MOD_ID, "effect_block_entity"),
                FabricBlockEntityTypeBuilder.create(EffectBlockEntity::new,
                        ModBlocks.EFFECT_BLOCK).build(null));
    }
}
