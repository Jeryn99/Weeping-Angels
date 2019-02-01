package me.sub.angels.common.blocks;

import me.sub.angels.common.WAObjects;
import me.sub.angels.common.tileentities.TileEntityChronodyneGenerator;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BoundingBox;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class BlockChronodyneGenerator extends Block implements BlockEntityProvider
{
	
	protected static final VoxelShape CG_AABB = VoxelShapes.cube(new BoundingBox(0.0D, 0.0D, 0.0D, 1.0D, 0.09375D, 1.0D));
	
	public BlockChronodyneGenerator() {
		super(Settings.of(Material.EARTH, MaterialColor.RED_TERRACOTTA));
//		this.setCreativeTab(WAObjects.ANGEL_TAB);
	}

	@Override public boolean isTranslucent(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1)
	{
		return true;
	}

	@Override public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, VerticalEntityPosition verticalEntityPosition_1)
	{
		return CG_AABB;
	}

	@Override public BlockRenderType getRenderType(BlockState blockState_1)
	{
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override public BlockEntity createBlockEntity(BlockView blockView)
	{
		return new TileEntityChronodyneGenerator(WAObjects.BlockEntities.CG);
	}
}
