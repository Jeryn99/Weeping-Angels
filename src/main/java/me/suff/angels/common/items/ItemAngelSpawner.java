package me.suff.angels.common.items;

import me.suff.angels.common.entities.AngelEnums;
import me.suff.angels.common.entities.EntityWeepingAngel;
import me.suff.angels.common.misc.WATabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Function;

public class ItemAngelSpawner<E extends EntityWeepingAngel> extends Item {
	
	private Function<World, E> entityCreator;
	private AngelEnums.AngelType type;
	
	public ItemAngelSpawner(AngelEnums.AngelType type, Function<World, E> angel) {
		super(new Properties().group(WATabs.MAIN_TAB));
		entityCreator = angel;
		this.type = type;
	}
	
	@Override
	public EnumActionResult onItemUse(ItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		EntityPlayer player = context.getPlayer();
		EnumHand hand = player.getActiveHand();
		
		if (!worldIn.isRemote) {
			EntityWeepingAngel angel = entityCreator.apply(worldIn);
			angel.setType(type.getId());
			angel.setChild(type.isChild());
			angel.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
			angel.faceEntity(player, 10.0F, 10.0F);
			worldIn.spawnEntity(angel);
			player.getHeldItem(hand).shrink(1);
		}
		return super.onItemUse(context);
	}
	
	
}
