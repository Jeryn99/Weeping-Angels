package me.sub.angels.common.entities;

import me.sub.angels.common.WAObjects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityChronodyneGenerator extends ProjectileEntity
{

    public EntityChronodyneGenerator(World world_1) {
        super(WAObjects.EntityEntries.CHRONODYNE_GENERATOR, world_1);
        setSize(0.5f, 0.5f);
    }

    public EntityChronodyneGenerator(LivingEntity livingEntity_1, World world_1) {
        super(WAObjects.EntityEntries.CHRONODYNE_GENERATOR, livingEntity_1, world_1);
        this.method_7437(livingEntity_1, livingEntity_1.pitch, livingEntity_1.headYaw, 0, 1.5F, 1.0F);
    }

    public EntityChronodyneGenerator( double double_1, double double_2, double double_3, World world_1) {
        super(WAObjects.EntityEntries.CHRONODYNE_GENERATOR, double_1, double_2, double_3, world_1);
    }

    //TODO
//    public static void registerFixesGen(DataFixer fixer) {
//        EntityThrowable.registerFixesThrowable(fixer, "gen");
//    }

    @Override
    @Environment(EnvType.CLIENT)
    public void method_5711(byte byte_1) {
    }


    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    @Override
    protected void method_7457(HitResult result) {

        if (result.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = new BlockPos(result.getPos().x, result.getPos().y + 1, result.getPos().z);
            if (world.getBlockState(pos).equals(Blocks.AIR.getDefaultState())) {
                world.setBlockState(pos, WAObjects.Blocks.CG.getDefaultState());
                if (world.getBlockEntity(pos) != null) {
                    //TODO
//                    world.getBlockEntity(pos).getTileData().setDouble(WAConstants.ABS_X, posX);
//                    world.getBlockEntity(pos).getTileData().setDouble(WAConstants.ABS_Y, posY);
//                    world.getBlockEntity(pos).getTileData().setDouble(WAConstants.ABS_Z, posZ);
                    this.invalidate();
                }
            }
        }

        if (result instanceof EntityHitResult && ((EntityHitResult) result).getEntity() instanceof EntityWeepingAngel) {
            if (world.isClient) {
                //TODO
//                this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ(), 1.0D, 0.0D, 0.0D);
            } else {
                EntityAnomaly a = new EntityAnomaly(world);
                //TODO
//                a.setEntityEyeHeight(result.entityHit.getEyeHeight());
                a.setPositionAndAngles(((EntityHitResult) result).getEntity());
                world.spawnEntity(a);

                ((EntityHitResult) result).getEntity().invalidate();
                invalidate();
            }
        }

        if (!this.world.isClient) {
            //TODO
//            this.world.setEntityState(this, (byte) 3);
            this.invalidate();
        }
    }

    @Override protected ItemStack asItemStack()
    {
        return ItemStack.EMPTY;
    }
}
