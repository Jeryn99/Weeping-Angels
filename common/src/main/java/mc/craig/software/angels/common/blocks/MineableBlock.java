package mc.craig.software.angels.common.blocks;

import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.DropExperienceBlock;

public class MineableBlock extends DropExperienceBlock {
    public MineableBlock(Properties properties, IntProvider intProvider) {
        super(properties, intProvider);
    }

    public MineableBlock(Properties properties) {
        super(properties, ConstantInt.of(0));
    }
}
