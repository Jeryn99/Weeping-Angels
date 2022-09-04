package mc.craig.software.angels.common.entity.angel.ai;

import mc.craig.software.angels.common.entity.angel.AbstractWeepingAngel;
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
        if (mob instanceof AbstractWeepingAngel weepingAngel) {
            if (!weepingAngel.isSeen()) {
                super.clientTick();
            }
        }
    }


}