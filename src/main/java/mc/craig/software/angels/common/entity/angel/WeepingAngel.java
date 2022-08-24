package mc.craig.software.angels.common.entity.angel;

import mc.craig.software.angels.util.WADamageSources;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Iterator;

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
        if (!POSE_ANIMATION_STATE.isStarted()) {
            POSE_ANIMATION_STATE.start(tickCount - random.nextInt(10000));
        }

        if (tickCount % 400 == 0) {
            investigateBlocks();
        }
    }

    @Override
    public void invokeSeen(Player player) {
        super.invokeSeen(player);
        if (getSeenTime() == 1) {
            playSound(SoundEvents.STONE_PLACE);
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.STONE_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.STONE_BREAK;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        Entity sourceEntity = pSource.getEntity();

        if (pSource == WADamageSources.GENERATOR) {
            return true;
        }

        if (sourceEntity instanceof Player player) {
            boolean hasPickAxe = player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof PickaxeItem;
            if(!hasPickAxe){
                player.hurt(WADamageSources.PUNCH_STONE, random.nextInt(3));
            }
        }
        return false;
    }


    public void investigateBlocks() {
        for (Iterator<BlockPos> iterator = BlockPos.withinManhattanStream(blockPosition(), 25, 3, 25).iterator(); iterator.hasNext(); ) {
            BlockPos pos = iterator.next();
            BlockState blockState = level.getBlockState(pos);
            BlockBehaviour.BlockReaction blockBehaviour = BlockBehaviour.BLOCK_BEHAVIOUR.get(blockState.getBlock());
            boolean completed = blockBehaviour.interact(this, blockState, level, pos);
            if (completed) return;
        }
    }
}
