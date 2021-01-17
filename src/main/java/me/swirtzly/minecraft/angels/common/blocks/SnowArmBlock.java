package me.swirtzly.minecraft.angels.common.blocks;

import me.swirtzly.minecraft.angels.common.tileentities.SnowArmTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class SnowArmBlock extends SnowBlock {

    public SnowArmBlock() {
        super(Block.Properties.create(Material.SNOW).notSolid().hardnessAndResistance(3).sound(SoundType.SNOW).setRequiresTool());
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new SnowArmTile();
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockstate = context.getWorld().getBlockState(context.getPos());
        if (blockstate.isIn(this)) {
            int i = blockstate.get(LAYERS);
            return blockstate.with(LAYERS, Math.min(8, i + 1));
        } else {
            return super.getStateForPlacement(context).with(LAYERS, 0);
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
