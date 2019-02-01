//package me.sub.angels.common.items;
//
//import me.sub.angels.common.entities.EntityAngelPainting;
//import net.minecraft.entity.decoration.AbstractDecorationEntity;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.ItemUsageContext;
//import net.minecraft.util.ActionResult;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.Direction;
//import net.minecraft.world.World;
//
//public class ItemHanging extends Item
//{
//
//	public ItemHanging()
//	{
//		super(new Settings());
//	}
//
//	@Override public ActionResult useOnBlock(ItemUsageContext itemUsageContext_1)
//	{
//		ItemStack stack = itemUsageContext_1.getPlayer().getMainHandStack();
//		BlockPos blockpos1 = itemUsageContext_1.getBlockPos().offset(itemUsageContext_1.getFacing());
//
//		if (itemUsageContext_1.getFacing() == Direction.DOWN || itemUsageContext_1.getFacing() == Direction.UP || !itemUsageContext_1.getPlayer()
//				.canPlaceBlock(blockpos1, itemUsageContext_1.getFacing(), stack))
//		{
//			return ActionResult.FAILURE;
//		}
//		else
//		{
//
//			AbstractDecorationEntity entityhanging = this.createHangingEntity(itemUsageContext_1.getWorld(), blockpos1, itemUsageContext_1.getFacing());
//
//			if (entityhanging.isValid())
//			{
//				if (!entityhanging.world.isClient)
//				{
//					entityhanging.world.spawnEntity(entityhanging);
//				}
//
//				stack.subtractAmount(1);
//			}
//
//			return ActionResult.PASS;
//		}
//	}
//
//	private AbstractDecorationEntity createHangingEntity(World worldIn, BlockPos pos, Direction clickedSide)
//	{
//		return new EntityAngelPainting(worldIn, pos, clickedSide);
//	}
//}
