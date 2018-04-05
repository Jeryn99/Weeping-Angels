package com.github.reallysub.angels.common.items;

import com.github.reallysub.angels.common.entities.EntityAngelPainting;

import com.github.reallysub.angels.main.WeepingAngels;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class ItemHanging extends Item {
	
	public ItemHanging() {
		super();
		this.setCreativeTab(CreativeTabs.DECORATIONS);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItemMainhand();
		
		if (side == EnumFacing.DOWN) {
			return EnumActionResult.FAIL;
		} else if (side == EnumFacing.UP) {
			return EnumActionResult.FAIL;
		} else {
			BlockPos blockpos1 = pos.offset(side);
			
			if (!player.canPlayerEdit(blockpos1, side, stack)) {
				return EnumActionResult.FAIL;
			} else {
				EntityHanging entityhanging = this.createHangingEntity(worldIn, blockpos1, side);
				
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
		return new EntityAngelPainting(worldIn, pos, clickedSide);
	}
}
