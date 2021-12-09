package me.suff.mc.angels.common.entities;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.controller.BodyController;

public class BodyControllerAngel extends BodyController {

    private final MobEntity mob;

    public BodyControllerAngel(MobEntity mob) {
        super(mob);
        this.mob = mob;
    }

    @Override
    public void clientTick() {
        if (mob instanceof WeepingAngelEntity) {
            WeepingAngelEntity weepingAngel = (WeepingAngelEntity) mob;
            if (!weepingAngel.isSeen()) {
                super.clientTick();
            }
        }
    }
}
