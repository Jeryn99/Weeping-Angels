package me.swirtzly.angels.common.items;

import me.swirtzly.angels.common.entities.EntityAngelPainting;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemHanging extends Item {
	
	public ItemHanging() {
		super();
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItemMainhand();
		
		if (side == EnumFacing.DOWN) {
			return EnumActionResult.FAIL;
		} else if (side == EnumFacing.UP) {
			return EnumActionResult.FAIL;
		} else {
			BlockPos offsetPos = pos.offset(side);
			
			if (!player.canPlayerEdit(offsetPos, side, stack)) {
				return EnumActionResult.FAIL;
			} else {
				EntityHanging entityhanging = new EntityAngelPainting(worldIn, offsetPos, side);
				
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
}
