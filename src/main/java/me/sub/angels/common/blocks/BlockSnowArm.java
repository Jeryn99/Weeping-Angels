package me.sub.angels.common.blocks;

import me.sub.angels.common.tiles.TileSnowArm;
import me.sub.angels.main.WeepingAngels;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockSnowArm extends BlockSnow implements ITileEntityProvider {
	
	public BlockSnowArm() {
		super();
		setUnlocalizedName("arm");
		setRegistryName(WeepingAngels.MODID, "arm");
		translucent = true;
	}
	
	@Nullable
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileSnowArm();
	}
	
}
