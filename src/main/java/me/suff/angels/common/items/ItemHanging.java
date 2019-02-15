package me.suff.angels.common.items;

import me.suff.angels.common.entities.EntityAngelPainting;
import me.suff.angels.common.misc.WATabs;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemHanging extends Item {
	
	public ItemHanging() {
		super(new Properties().maxStackSize(16).group(WATabs.MAIN_TAB));
	}
	
	@Override
	public EnumActionResult onItemUse(ItemUseContext context) {
		EntityPlayer player = context.getPlayer();
		EnumFacing side = context.getFace();
		ItemStack stack = player.getHeldItemMainhand();
		World worldIn = context.getWorld();
		
		if (side == EnumFacing.DOWN) {
			return EnumActionResult.FAIL;
		} else if (side == EnumFacing.UP) {
			return EnumActionResult.FAIL;
		} else {
			BlockPos offsetPos = context.getPos().offset(side);
			
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
