package me.suff.mc.angels.common.items;

import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.entities.AngelEnums;
import me.suff.mc.angels.common.entities.AngelEnums.AngelType;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.misc.WATabs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AngelSpawnerItem< E extends WeepingAngelEntity > extends Item {

    public AngelSpawnerItem() {
        super(new Properties().group(WATabs.MAIN_TAB));
    }

    public static void setType(ItemStack stack, AngelEnums.AngelType type) {
        CompoundNBT tag = stack.getOrCreateTag();
        tag.putString("type", type.name());
    }

    public static AngelEnums.AngelType getType(ItemStack stack) {
        CompoundNBT tag = stack.getOrCreateTag();
        String angelType = tag.getString("type");
        angelType = angelType.isEmpty() ? AngelType.ANGELA_MC.name() : angelType;
        return AngelEnums.AngelType.valueOf(angelType);
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList< ItemStack > items) {
        if (isInGroup(group)) {
            for (AngelEnums.AngelType angelType : AngelEnums.AngelType.values()) {
                ItemStack itemstack = new ItemStack(this);
                setType(itemstack, angelType);
                items.add(itemstack);
            }
        }

    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World worldIn = context.getWorld();
        BlockPos pos = context.getPos();
        PlayerEntity player = context.getPlayer();
        Hand hand = player.getActiveHand();

        if (!worldIn.isRemote) {
            WeepingAngelEntity angel = WAObjects.EntityEntries.WEEPING_ANGEL.get().create(worldIn);
            angel.setType(getType(context.getItem()));
            angel.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
            angel.faceEntity(player, 90.0F, 90.0F);
            player.getHeldItem(hand).shrink(1);
            worldIn.addEntity(angel);

            if (!player.isCreative()) {
                context.getItem().shrink(1);
            }
        }
        return super.onItemUse(context);
    }

}
