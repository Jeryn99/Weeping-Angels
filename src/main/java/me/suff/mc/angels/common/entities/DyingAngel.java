package me.suff.mc.angels.common.entities;

import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.utils.AngelUtil;
import me.suff.mc.angels.utils.WADamageSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class DyingAngel extends QuantumLockedLifeform {

    public DyingAngel(Level worldIn, EntityType<? extends Monster> entityType) {
        super(worldIn, entityType);
    }

    public DyingAngel(Level world) {
        super(world, WAObjects.EntityEntries.APLAN.get());
        goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        xpReward = WAConfig.CONFIG.xpGained.get();
    }

    public DyingAngel(EntityType<DyingAngel> dyingAngelEntityType, Level level) {
        this(level);
    }



    @Override
    public boolean displayFireAnimation() {
        return false;
    }

    @Override
    public void knockback(double p_147241_, double p_147242_, double p_147243_) {

    }



    @Override
    public boolean doHurtTarget(Entity p_21372_) {
        float f = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        return p_21372_.hurt(WAObjects.ANGEL_NECK_SNAP, f);
    }
}
