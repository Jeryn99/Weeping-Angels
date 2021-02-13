package me.swirtzly.minecraft.angels.common.items;

import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.common.entities.AngelEnums;
import me.swirtzly.minecraft.angels.common.entities.AngelEnums.AngelType;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.minecraft.angels.common.misc.WATabs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AngelSpawnerItem< E extends WeepingAngelEntity > extends Item {

    public AngelSpawnerItem() {
        super(new Properties().group(WATabs.MAIN_TAB));
        addPropertyOverride(new ResourceLocation(WeepingAngels.MODID, "angel_type"), (itemStack, clientWorld, livingEntity) -> {
            if (itemStack == null || itemStack.isEmpty()) {
                return 0;
            }
            AngelEnums.AngelType type = AngelSpawnerItem.getType(itemStack);
            return type.ordinal();
        });
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
            angel.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
            angel.faceEntity(player, 10.0F, 10.0F);
            player.getHeldItem(hand).shrink(1);
            worldIn.addEntity(angel);

            if (!player.isCreative()) {
                context.getItem().shrink(1);
            }
        }
        return super.onItemUse(context);
    }

}
