package me.sub.angels.common.items;

import me.sub.angels.common.WAObjects;
import me.sub.angels.common.entities.EntityAngel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Function;

public class ItemAngelSpawner<E extends EntityAngel> extends Item {
	
	private Function<World, E> entityCreator;
    private int type;
    private boolean isChild = false;
	
	public ItemAngelSpawner(int type, Function<World, E> angel) {
		entityCreator = angel;
		this.type = type;
        this.setCreativeTab(WAObjects.angelTab);
	}
	
	public ItemAngelSpawner(int type, Function<World, E> angel, boolean isChild) {
		entityCreator = angel;
		this.type = type;
		this.isChild = isChild;
        this.setCreativeTab(WAObjects.angelTab);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if (!worldIn.isRemote) {
			EntityAngel entity = entityCreator.apply(worldIn);
			entity.setType(type);
			entity.setChild(isChild);
			entity.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
			worldIn.spawnEntity(entity);
		}
		return EnumActionResult.PASS;
	}
	
}
