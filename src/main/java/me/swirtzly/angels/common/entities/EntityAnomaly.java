package me.swirtzly.angels.common.entities;

import me.swirtzly.angels.common.WAObjects;
import net.minecraft.entity.EntityLiving;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityAnomaly extends EntityLiving {

    private static final DataParameter<Float> EYE_HEIGHT = EntityDataManager.createKey(EntityAnomaly.class, DataSerializers.FLOAT);

    public EntityAnomaly(World worldIn) {
        super(worldIn);
    }

    @Override
    public boolean getIsInvulnerable() {
        return true;
    }

    @Override
    protected boolean isMovementBlocked() {
        return true;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        setNoAI(true);

        if (ticksExisted == 1) {
            playSound(WAObjects.Sounds.ANGEL_TELEPORT, 1.0F, 1.0F);
        }

        if (ticksExisted > 100) {
            setDead();
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        getDataManager().register(EYE_HEIGHT, getEyeHeight());
    }

    public float getEntityEyeHeight() {
        return getDataManager().get(EYE_HEIGHT);
    }

    public void setEntityEyeHeight(float eyeHeight) {
        getDataManager().set(EYE_HEIGHT, eyeHeight);
    }
}
