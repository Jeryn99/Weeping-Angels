package me.swirtzly.angels.common.items;

import me.swirtzly.angels.common.WAObjects;
import me.swirtzly.angels.common.entities.AngelEnums;
import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import me.swirtzly.angels.common.misc.WATabs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
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
	public ActionResultType onItemUse(ItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		PlayerEntity player = context.getPlayer();
		Hand hand = player.getActiveHand();
		
		if (!worldIn.isRemote) {
			EntityWeepingAngel angel = WAObjects.EntityEntries.WEEPING_ANGEL.create(worldIn);
			angel.setType(type.getId());
			angel.setChild(type.isChild());
			angel.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
			angel.faceEntity(player, 10.0F, 10.0F);
			player.getHeldItem(hand).shrink(1);
			worldIn.addEntity(angel);
		}
		return super.onItemUse(context);
	}
	
	
}
