package net.kaupenjoe.greygoo.util;

import net.minecraft.core.BlockPos;

public class GooUtils {
    public static float getSpreadChance(int spreadChanceRule) {
        if(spreadChanceRule < 5) {
            spreadChanceRule = 5;
        }

        return 1.0f - (1.0f / (float) spreadChanceRule);
    }

    public static BlockPos[] getPositionsNextTo(BlockPos position) {
        return new BlockPos[] { position.above(), position.below(), position.east(),
                position.west(), position.north(), position.south()};
    }
}
