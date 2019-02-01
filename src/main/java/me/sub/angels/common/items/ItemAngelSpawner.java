package me.sub.angels.common.items;

import me.sub.angels.common.entities.EntityWeepingAngel;
import me.sub.angels.common.misc.AngelEnums;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.function.Function;

public class ItemAngelSpawner<E extends EntityWeepingAngel> extends Item {

    private Function<World, E> entityCreator;
    private AngelEnums.AngelType type;

    public ItemAngelSpawner(AngelEnums.AngelType type, Function<World, E> angel) {
        super(new Settings());
        entityCreator = angel;
        this.type = type;
//        this.setCreativeTab(WAObjects.ANGEL_TAB);
    }

    @Override public ActionResult useOnBlock(ItemUsageContext itemUsageContext_1)
    {
        if (!itemUsageContext_1.getWorld().isClient) {
            EntityWeepingAngel angel = entityCreator.apply(itemUsageContext_1.getWorld());
            angel.setAngelType(type.getId());
            angel.setChild(type.isChild());
            angel.setPosition(itemUsageContext_1.getPos().x, itemUsageContext_1.getPos().y + 1, itemUsageContext_1.getPos().z);
            itemUsageContext_1.getWorld().spawnEntity(angel);

            itemUsageContext_1.getItemStack().subtractAmount(1);
        }
        return ActionResult.PASS;
    }

	
}
