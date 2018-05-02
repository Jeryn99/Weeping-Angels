package me.sub.angels.common.entities;

import net.minecraft.client.renderer.entity.RenderDragon;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityChronodyneGenerator extends EntityThrowable {

    public EntityChronodyneGenerator(World worldIn) {
        super(worldIn);
    }

    public EntityChronodyneGenerator(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityChronodyneGenerator(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if(result.entityHit instanceof EntityAngel) {
            EntityAngel angel = (EntityAngel) result.entityHit;
            angel.attackEntityFrom(DamageSource.ANVIL, Integer.MAX_VALUE);
        }
    }
}
