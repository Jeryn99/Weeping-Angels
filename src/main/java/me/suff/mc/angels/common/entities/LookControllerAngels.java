package me.suff.mc.angels.common.entities;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.controller.LookController;

public class LookControllerAngels extends LookController {

    private final MobEntity angel;

    public LookControllerAngels(MobEntity mob) {
        super(mob);
        this.angel = mob;
    }

    @Override
    public void tick() {
        if(angel instanceof WeepingAngelEntity){
            WeepingAngelEntity weepingAngel = (WeepingAngelEntity) angel;
            if(weepingAngel.getSeenTime() == 0){
                super.tick();
            }
        }
    }
}
