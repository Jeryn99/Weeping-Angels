package me.swirtzly.angels.compat;

import me.swirtzly.angels.common.entities.WeepingAngelEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

/**
 * Created by Swirtzly
 * on 01/03/2020 @ 15:16
 */
public interface ICompat {

    boolean onBlockBreak(WeepingAngelEntity weepingAngel, BlockState state, BlockPos pos);


}
