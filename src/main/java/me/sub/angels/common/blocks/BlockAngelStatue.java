package me.sub.angels.common.blocks;

import me.sub.angels.WeepingAngels;
import me.sub.angels.client.models.poses.PoseManager;
import me.sub.angels.common.tileentities.TileEntityPlinth;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockAngelStatue extends Block {
	
	public BlockAngelStatue(String name) {
		super(Material.CORAL);
		setUnlocalizedName(name);
		setRegistryName(WeepingAngels.MODID, name);
		translucent = true;
		this.setHardness(1.0F);
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityPlinth();
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	/**
	 * Called by ItemBlocks after a block is set in the world, to allow post-place logic
	 */
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		super.onBlockPlacedBy(world, pos, state, placer, stack);
		
		if (world.getTileEntity(pos) instanceof TileEntityPlinth) {
			int rotation = MathHelper.floor(placer.rotationYaw + 180);
			TileEntityPlinth plinth = (TileEntityPlinth) world.getTileEntity(pos);
			plinth.setRotation(rotation);
			plinth.setPose(getRandomPose(world).toString());
			plinth.sendUpdates();
		}
	}
	
	public PoseManager.AngelPoses getRandomPose(World world) {
		return PoseManager.AngelPoses.values()[world.rand.nextInt(PoseManager.AngelPoses.values().length)];
	}
	
}
