package me.swirtzly.minecraft.angels.common.blocks;

import me.swirtzly.minecraft.angels.common.entities.AnomalyEntity;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class ChronodyneGeneratorBlock extends Block {
	
	private static final VoxelShape CG_AABB = VoxelShapes.create(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.09375D, 1.0D));
	
	public ChronodyneGeneratorBlock() {
		super(Properties.create(Material.ROCK).notSolid().hardnessAndResistance(3).sound(SoundType.STONE));
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return CG_AABB;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return CG_AABB;
	}
	
	@Override
	public boolean isVariableOpacity() {
		return false;
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		super.onEntityWalk(worldIn, pos, entityIn);

		if(entityIn instanceof WeepingAngelEntity){
			AnomalyEntity anomalyEntity = new AnomalyEntity(worldIn);
			anomalyEntity.setPosition(pos.getX(), pos.getY(), pos.getZ());
			worldIn.addEntity(anomalyEntity);
			worldIn.removeBlock(pos, false);
		}
	}

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		if (worldIn.isBlockPowered(pos)) {
			AnomalyEntity anomalyEntity = new AnomalyEntity(worldIn);
			anomalyEntity.setPosition(pos.getX(), pos.getY(), pos.getZ());
			worldIn.addEntity(anomalyEntity);
			worldIn.removeBlock(pos, false);
		}
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if(!worldIn.isRemote){
			AnomalyEntity anomalyEntity = new AnomalyEntity(worldIn);
			anomalyEntity.setPosition(pos.getX(), pos.getY(), pos.getZ());
			worldIn.addEntity(anomalyEntity);
			worldIn.removeBlock(pos, false);
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
	}
}
