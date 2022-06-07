package me.suff.mc.angels.common.items;

import me.suff.mc.angels.common.entities.AngelType;
import me.suff.mc.angels.common.entities.WeepingAngel;
import me.suff.mc.angels.common.misc.WATabs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class AngelSpawnerItem extends Item {

    public AngelSpawnerItem() {
        super(new Properties().tab(WATabs.MAIN_TAB));
    }

    public static ItemStack setType(ItemStack stack, AngelType type) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putString("type", type.name());
        return stack;
    }

    public static AngelType getType(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        String angelType = tag.getString("type");
        angelType = angelType.isEmpty() ? AngelType.DISASTER_MC.name() : angelType;
        return AngelType.valueOf(angelType);
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        if (allowedIn(group)) {
            for (AngelType angelType : AngelType.values()) {
                ItemStack itemstack = new ItemStack(this);
                setType(itemstack, angelType);
                items.add(itemstack);
            }
        }

    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable(this.getDescriptionId(stack), getType(stack).getReadable());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level worldIn = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();

        if (!worldIn.isClientSide) {
            WeepingAngel angel = new WeepingAngel(worldIn);
            angel.setPos(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
            if (player != null) {
                angel.lookAt(player, 90.0F, 90.0F);
            }
            worldIn.addFreshEntity(angel);
            angel.setType(getType(context.getItemInHand()));

            if (!player.isCreative()) {
                context.getItemInHand().shrink(1);
            }
        }
        return super.useOn(context);
    }

}
