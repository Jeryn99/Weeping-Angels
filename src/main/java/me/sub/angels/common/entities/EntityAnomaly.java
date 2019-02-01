package me.sub.angels.common.entities;

import me.sub.angels.common.WAObjects;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.item.ItemStack;
import net.minecraft.sortme.OptionMainHand;
import net.minecraft.world.World;

import java.util.ArrayList;

public class EntityAnomaly extends LivingEntity
{

//    private static final DataParameter<Float> EYE_HEIGHT = EntityDataManager.createKey(EntityAnomaly.class, DataSerializers.FLOAT);

    public EntityAnomaly(World worldIn) {
        super(WAObjects.EntityEntries.ANOMALY, worldIn);
        setSize(0.5f, 0.5f);
    }

    @Override public void move(MovementType movementType_1, double double_1, double double_2, double double_3)
    {

    }

    @Override public boolean isInvulnerable()
    {
        return true;
    }

    //TODO
//    @Override
//    protected boolean isMovementBlocked() {
//        return true;
//    }

    @Override public Iterable<ItemStack> getItemsArmor()
    {
        return new ArrayList<>();
    }

    @Override public ItemStack getEquippedStack(EquipmentSlot equipmentSlot)
    {
        return ItemStack.EMPTY;
    }

    @Override public void setEquippedStack(EquipmentSlot equipmentSlot, ItemStack itemStack)
    {

    }

    @Override public void update()
    {
        super.update();
        if (age == 1) {
            //TODO sounds
            //            playSound(WAObjects.Sounds.ANGEL_TELEPORT, 1.0F, 1.0F);
        }

        if (age > 100) {
            invalidate();
        }
    }

    @Override public OptionMainHand getMainHand()
    {
        return OptionMainHand.LEFT;
    }

    //TODO
//    @Override
//    protected void entityInit() {
//        super.entityInit();
//        getDataManager().register(EYE_HEIGHT, getEyeHeight());
//    }

    @Override public float getEyeHeight()
    {
//        return this.getDataManager().get(EYE_HEIGHT);
        return super.getEyeHeight();
    }

//    public void setEntityEyeHeight(float eyeHeight) {
//        getDataManager().set(EYE_HEIGHT, eyeHeight);
//    }
}
