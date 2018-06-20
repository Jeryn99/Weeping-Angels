package me.sub.angels.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIQuatumLocking extends EntityAIBase {

    private Entity entity;

    public EntityAIQuatumLocking(Entity entity) {
        this.entity = entity;
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void updateTask() {
    }


}
