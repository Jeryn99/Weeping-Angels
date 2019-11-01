package me.swirtzly.angels.common.blocks;

import me.swirtzly.angels.client.models.poses.PoseManager;
import me.swirtzly.angels.common.tileentities.TileEntityPlinth;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockAngelStatue extends Block {
	
	public BlockAngelStatue() {
		super(Properties.create(Material.ROCK).hardnessAndResistance(3).sound(SoundType.STONE));
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileEntityPlinth();
	}
	
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	
	@Override
	public boolean isVariableOpacity() {
		return false;
	}
	
	/**
	 * Called by ItemBlocks after a block is set in the world, to allow post-place logic
	 */
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		super.onBlockPlacedBy(world, pos, state, placer, stack);
		
		if (world.getTileEntity(pos) instanceof TileEntityPlinth) {
			int rotation = MathHelper.floor(placer.rotationYaw + 180);
			TileEntityPlinth plinth = (TileEntityPlinth) world.getTileEntity(pos);
			plinth.setRotation(rotation);
			plinth.setPose(PoseManager.getRandomPose().getRegistryName());
			plinth.sendUpdates();
		}
	}
	
}
