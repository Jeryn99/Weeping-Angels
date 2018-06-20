package me.sub.angels.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Function;

public class ItemHanging extends Item {

	private Function<World, EntityHanging> entityHanging;

	public ItemHanging(Function<World, EntityHanging> entity) {
		super();
		this.setCreativeTab(CreativeTabs.DECORATIONS);
		entityHanging = entity;
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItemMainhand();
		
		if (side == EnumFacing.DOWN) {
			return EnumActionResult.FAIL;
		} else if (side == EnumFacing.UP) {
			return EnumActionResult.FAIL;
		} else {
			BlockPos wallPos = pos.offset(side);

			if (!player.canPlayerEdit(wallPos, side, stack)) {
				return EnumActionResult.FAIL;
			} else {
				EntityHanging entityhanging = this.createHangingEntity(worldIn, wallPos, side);
				
				if (entityhanging.onValidSurface()) {
					if (!worldIn.isRemote) {
						worldIn.spawnEntity(entityhanging);
					}
					
					stack.shrink(1);
				}
				
				return EnumActionResult.PASS;
			}
		}
	}
	
	private EntityHanging createHangingEntity(World worldIn, BlockPos pos, EnumFacing clickedSide) {
		return entityHanging.apply(worldIn);
	}
}
