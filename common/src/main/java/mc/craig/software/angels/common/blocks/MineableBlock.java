package mc.craig.software.angels.common.blocks;

import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.DropExperienceBlock;

public class MineableBlock extends DropExperienceBlock {

    public MineableBlock(Properties properties) {
        super(ConstantInt.of(0), properties);
    }

    public MineableBlock(IntProvider intProvider, Properties properties) {
        super(intProvider, properties);
    }
}
