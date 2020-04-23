package me.swirtzly.angels.common.entities.ai;

import me.swirtzly.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.angels.config.WAConfig;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;
import net.tardis.mod.enums.EnumDoorState;
import net.tardis.mod.tileentities.exteriors.ExteriorTile;

import java.util.EnumSet;
import java.util.Iterator;

public class AIMoveTowardsTardis<T extends WeepingAngelEntity> extends Goal {

    private final T weepingAngel;
    private ExteriorTile tardis = null;

    public AIMoveTowardsTardis(T p_i50323_1_) {
        this.weepingAngel = p_i50323_1_;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        return this.weepingAngel.getAttackTarget() == null && !this.weepingAngel.isBeingRidden();
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean shouldContinueExecuting() {
        return this.weepingAngel.world instanceof ServerWorld;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    @Override
    public void tick() {
        if (!weepingAngel.getHeldItemMainhand().isEmpty() || !weepingAngel.getHeldItemOffhand().isEmpty()) {
            if (tardis == null) {

                AxisAlignedBB box = weepingAngel.getBoundingBox().grow(25);
                for (Iterator<BlockPos> iterator = BlockPos.getAllInBox(new BlockPos(box.maxX, box.maxY, box.maxZ), new BlockPos(box.minX, box.minY, box.minZ)).iterator(); iterator.hasNext(); ) {
                    BlockPos pos = iterator.next();
                    TileEntity tileEntity = weepingAngel.world.getTileEntity(pos);
                    if (tileEntity instanceof ExteriorTile) {
                        tardis = (ExteriorTile) tileEntity;
                        weepingAngel.setAttackTarget(null);
                        weepingAngel.getNavigator().clearPath();
                    }

                }
            }

            if (!this.weepingAngel.hasPath() && tardis != null) {
                Vec3d vec3d = new Vec3d(tardis.getPos());
                if (vec3d != null) {
                    this.weepingAngel.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 1.0D);
                }

                if (weepingAngel.getDistanceSq(tardis.getPos().getX(), tardis.getPos().getY(), tardis.getPos().getZ()) < 7) {
                    tardis.setDoorState(EnumDoorState.BOTH);
                    weepingAngel.setPosition(tardis.getPos().getX(), tardis.getPos().getY(), tardis.getPos().getZ());
                }
            }
        }
    }

}