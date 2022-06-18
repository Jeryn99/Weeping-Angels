package craig.software.mc.angels.common.entities.ai;

import craig.software.mc.angels.common.entities.QuantumLockedLifeform;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;

/* Created by Craig on 04/04/2021 */
public class GoalWalkWhenNotWatched extends WaterAvoidingRandomStrollGoal {

    public GoalWalkWhenNotWatched(PathfinderMob creature, double speedIn) {
        super(creature, speedIn);
    }

    public GoalWalkWhenNotWatched(PathfinderMob creature, double speedIn, float probabilityIn) {
        super(creature, speedIn, probabilityIn);
    }

    @Override
    public boolean canContinueToUse() {
        if (mob instanceof QuantumLockedLifeform) {
            QuantumLockedLifeform quantumLockBaseEntity = (QuantumLockedLifeform) mob;
            return super.canContinueToUse() && !quantumLockBaseEntity.isSeen();
        }
        return super.canContinueToUse();
    }
}
