package me.suff.mc.angels.common.entities.ai;

import me.suff.mc.angels.common.entities.QuantumLockBaseEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;

/* Created by Craig on 04/04/2021 */
public class GoalWalkWhenNotWatched extends WaterAvoidingRandomWalkingGoal {

    public GoalWalkWhenNotWatched(CreatureEntity creature, double speedIn) {
        super(creature, speedIn);
    }

    public GoalWalkWhenNotWatched(CreatureEntity creature, double speedIn, float probabilityIn) {
        super(creature, speedIn, probabilityIn);
    }

    @Override
    public boolean canContinueToUse() {
        if(mob instanceof QuantumLockBaseEntity){
            QuantumLockBaseEntity quantumLockBaseEntity = (QuantumLockBaseEntity) mob;
            return super.canContinueToUse() && !quantumLockBaseEntity.isSeen();
        }
        return super.canContinueToUse();
    }
}
