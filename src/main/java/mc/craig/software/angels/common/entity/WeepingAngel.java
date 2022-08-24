package mc.craig.software.angels.common.entity;

import mc.craig.software.angels.WAConfiguration;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class WeepingAngel extends AbstractWeepingAngel {

    public AnimationState POSE_ANIMATION_STATE = new AnimationState();

    public WeepingAngel(Level worldIn, EntityType<? extends Monster> entityType) {
        super(worldIn, entityType);
        int id = 0;
        goalSelector.addGoal(id++, new OpenDoorGoal(this, false));
        targetSelector.addGoal(id++, new NearestAttackableTargetGoal<>(this, Player.class, true));
        targetSelector.addGoal(id++, (new HurtByTargetGoal(this)).setAlertOthers(WeepingAngel.class));
        goalSelector.addGoal(id++, new MeleeAttackGoal(this, 0.5f, true));
    }

    @Override
    public void tick() {
        super.tick();
        if(!POSE_ANIMATION_STATE.isStarted()){
            POSE_ANIMATION_STATE.start(tickCount - random.nextInt(10000));
        }
    }

    public void investigateBlocks(AABB aabb){

    }
}
