package mc.craig.software.angels.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MineableBlock extends Block {

    public MineableBlock() {
        super(Properties.of(Material.STONE).strength(3).sound(SoundType.STONE).requiresCorrectToolForDrops());
    }
}
