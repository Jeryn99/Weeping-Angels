package me.sub.angels.common.blocks;

import me.sub.angels.common.WAObjects;
import me.sub.angels.common.tileentities.TileEntitySnowArm;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class BlockSnowArm extends SnowBlock implements BlockEntityProvider {
	
	public BlockSnowArm() {
		super(Settings.copy(Blocks.SNOW));

	}

	@Override public boolean isTranslucent(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1)
	{
		return true;
	}

	@Override public BlockEntity createBlockEntity(BlockView blockView)
	{
		return new TileEntitySnowArm(WAObjects.BlockEntities.ARM);
	}
}
