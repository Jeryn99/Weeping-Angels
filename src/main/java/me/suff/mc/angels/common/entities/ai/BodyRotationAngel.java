package me.suff.mc.angels.common.entities.ai;

import me.suff.mc.angels.common.entities.WeepingAngel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.BodyRotationControl;

public class BodyRotationAngel extends BodyRotationControl {

    private final Mob mob;

    public BodyRotationAngel(Mob mob) {
        super(mob);
        this.mob = mob;
    }

    @Override
    public void clientTick() {
        if (mob instanceof WeepingAngel weepingAngel) {
            if (!weepingAngel.isSeen()) {
                super.clientTick();
            }
        }
    }


}
