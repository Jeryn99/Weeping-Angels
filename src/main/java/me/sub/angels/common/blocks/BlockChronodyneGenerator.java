package me.sub.angels.common.blocks;

import me.sub.angels.WeepingAngels;
import me.sub.angels.common.WAObjects;
import me.sub.angels.common.tileentities.TileEntityChronodyneGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockChronodyneGenerator extends Block {
	
	protected static final AxisAlignedBB CG_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.09375D, 1.0D);
	
	public BlockChronodyneGenerator(String name) {
		super(Material.GRASS, MapColor.ADOBE);
		setUnlocalizedName(name);
		setRegistryName(WeepingAngels.MODID, name);
		translucent = true;
		this.setCreativeTab(WAObjects.ANGEL_TAB);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return CG_AABB;
	}
	
	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks for render
	 */
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
		return true;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	/**
	 * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only, LIQUID for vanilla liquids, INVISIBLE to skip all rendering
	 */
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityChronodyneGenerator();
	}
}
