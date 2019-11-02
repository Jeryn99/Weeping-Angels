package me.swirtzly.angels.common.items;

import me.swirtzly.angels.common.entities.EntityAngelPainting;
import me.swirtzly.angels.common.misc.WATabs;
import net.minecraft.entity.item.HangingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemHanging extends Item {
	
	public ItemHanging() {
		super(new Properties().maxStackSize(16).group(WATabs.MAIN_TAB));
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		PlayerEntity player = context.getPlayer();
		Direction side = context.getFace();
		ItemStack stack = player.getHeldItemMainhand();
		World worldIn = context.getWorld();
		
		if (side == Direction.DOWN) {
			return ActionResultType.FAIL;
		} else if (side == Direction.UP) {
			return ActionResultType.FAIL;
		} else {
			BlockPos offsetPos = context.getPos().offset(side);
			
			if (!player.canPlayerEdit(offsetPos, side, stack)) {
				return ActionResultType.FAIL;
			} else {
				HangingEntity entityhanging = new EntityAngelPainting(worldIn, offsetPos, side);
				
				if (entityhanging.onValidSurface()) {
					if (!worldIn.isRemote) {
						worldIn.addEntity(entityhanging);
					}
					stack.shrink(1);
				}
				
				return ActionResultType.PASS;
			}
		}
	}
}
