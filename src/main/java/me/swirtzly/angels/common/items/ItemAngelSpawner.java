package me.swirtzly.angels.common.items;

import me.swirtzly.angels.common.entities.AngelEnums;
import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import me.swirtzly.angels.common.misc.WATabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Function;

public class ItemAngelSpawner<E extends EntityWeepingAngel> extends Item {
	
	private Function<World, E> entityCreator;
	private AngelEnums.AngelType type;
	
	public ItemAngelSpawner(AngelEnums.AngelType type, Function<World, E> angel) {
		entityCreator = angel;
		this.type = type;
		setCreativeTab(WATabs.MAIN_TAB);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if (!worldIn.isRemote) {
			EntityWeepingAngel angel = entityCreator.apply(worldIn);
			angel.setType(type.getId());
			angel.setChild(type.isChild());
			angel.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
			angel.faceEntity(player, 10.0F, 10.0F);
			worldIn.spawnEntity(angel);
			player.getHeldItem(hand).shrink(1);
		}
		return EnumActionResult.PASS;
	}
	
}
