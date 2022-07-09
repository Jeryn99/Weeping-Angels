package craig.software.mc.angels.common.items;

import craig.software.mc.angels.common.WAObjects;
import craig.software.mc.angels.common.entities.AngelType;
import craig.software.mc.angels.common.entities.WeepingAngelEntity;
import craig.software.mc.angels.common.misc.WATabs;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class AngelSpawnerItem<E extends WeepingAngelEntity> extends Item {

    public AngelSpawnerItem() {
        super(new Properties().tab(WATabs.MAIN_TAB));
    }

    public static ItemStack setType(ItemStack stack, AngelType type) {
        CompoundNBT tag = stack.getOrCreateTag();
        tag.putString("type", type.name());
        return stack;
    }

    public static AngelType getType(ItemStack stack) {
        CompoundNBT tag = stack.getOrCreateTag();
        String angelType = tag.getString("type");
        angelType = angelType.isEmpty() ? AngelType.DISASTER_MC.name() : angelType;
        return AngelType.valueOf(angelType);
    }

    @Override
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
        if (allowdedIn(group)) {
            for (AngelType angelType : AngelType.values()) {
                ItemStack itemstack = new ItemStack(this);
                setType(itemstack, angelType);
                items.add(itemstack);
            }
        }

    }

    @Override
    public ITextComponent getName(ItemStack stack) {
        return new TranslationTextComponent(this.getDescriptionId(stack), getType(stack).getReadable());
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        World worldIn = context.getLevel();
        BlockPos pos = context.getClickedPos();
        PlayerEntity player = context.getPlayer();
        Hand hand = player.getUsedItemHand();

        if (!worldIn.isClientSide) {
            WeepingAngelEntity angel = WAObjects.EntityEntries.WEEPING_ANGEL.get().create(worldIn);
            angel.setType(getType(context.getItemInHand()));
            angel.setPos(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
            angel.lookAt(player, 90.0F, 90.0F);
            player.getItemInHand(hand).shrink(1);
            worldIn.addFreshEntity(angel);

            if (!player.isCreative()) {
                context.getItemInHand().shrink(1);
            }
        }
        return super.useOn(context);
    }

}
