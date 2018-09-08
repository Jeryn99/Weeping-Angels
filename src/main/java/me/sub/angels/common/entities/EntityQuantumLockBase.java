package me.sub.angels.common.entities;

import me.sub.angels.common.misc.WAConstants;
import me.sub.angels.config.WAConfig;
import me.sub.angels.utils.AngelUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class EntityQuantumLockBase extends EntityMob {
	
	private static final DataParameter<Boolean> IS_SEEN = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> TIME_VIEWED = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.VARINT);
	private static final DataParameter<BlockPos> PREVBLOCKPOS = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.BLOCK_POS);
	private static final DataParameter<Boolean> QUANTUM = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.BOOLEAN);

	public EntityQuantumLockBase(World worldIn) {
		super(worldIn);
	}

	@Override
	public void onLivingUpdate() {


		if (!world.isRemote && ticksExisted % 5 == 0) {
			setQuantum(quantumCheck());
		}

		super.onLivingUpdate();

		if (!isQuantumLocked()) {

			this.rotationYawHead = this.rotationYaw;
			if (!world.isRemote && ticksExisted % 5 == 0) {
				List<EntityPlayer> players = this.world.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().grow(60));
				players.removeIf(player -> player.isSpectator() || player.isInvisible() || player.isPlayerSleeping());

				if (players.isEmpty()) {
					setSeenTime(0);
					return;
				}

				EntityPlayer closest = null;
				for (EntityPlayer player : players) {

					if (AngelUtils.isInSight(player, this)) {
						setSeenTime(getSeenTime() + 1);
						invokeSeen(player);
						return;

					} else if (closest == null || this.getDistance(closest) > this.getDistance(player)) {
						closest = player;
						setSeenTime(0);
					}
				}

				Vec3d vecp = getPositionVector();
				Vec3d vect = closest.getPositionVector();
				float angle = (float) Math.toDegrees((float) Math.atan2(vecp.z - vect.z, vecp.x - vect.x));
				rotationYawHead = rotationYaw = angle > 180 ? angle : angle + 90;
				if (!closest.isCreative()) return;
				if (getDistance(closest) < 1)
					attackEntityAsMob(closest);
				else
					teleportTowards(closest);
			}
		}
	}

	public void teleportTowards(EntityLivingBase closest) {
		if (isQuantumLocked()) {
			setSeenTime(0);
			return;
		}
		Path p = this.navigator.getPathToEntityLiving(closest);
		if (p == null) return;
		if (p.getCurrentPathLength() > p.getCurrentPathIndex() + 1) p.incrementPathIndex();

		Vec3d vec3d = p.getCurrentPos();
		this.setLocationAndAngles(vec3d.x, vec3d.y, vec3d.z, this.rotationYaw, this.rotationPitch);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		getDataManager().register(IS_SEEN, false);
		getDataManager().register(TIME_VIEWED, 0);
		getDataManager().register(PREVBLOCKPOS, BlockPos.ORIGIN);
		getDataManager().register(QUANTUM, false);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean(WAConstants.IS_SEEN, isSeen());
		compound.setInteger(WAConstants.TIME_SEEN, getSeenTime());
		compound.setLong(WAConstants.PREVPOS, getPrevPos().toLong());
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		if (compound.hasKey(WAConstants.TIME_SEEN)) setSeenTime(compound.getInteger(WAConstants.TIME_SEEN));
		if (compound.hasKey(WAConstants.PREVPOS)) setPrevPos(getPrevPos());
		if (compound.hasKey(WAConstants.IS_SEEN)) setQuantum(compound.getBoolean(WAConstants.IS_SEEN));
	}
	
	public boolean isSeen() {
		return getSeenTime() > 0;
	}

	public void setSeen(boolean seen) {
		getDataManager().set(IS_SEEN, seen);
	}
	
	public int getSeenTime() {
		return getDataManager().get(TIME_VIEWED);
	}
	
	public void setSeenTime(int time) {
		getDataManager().set(TIME_VIEWED, time);
	}

	public BlockPos getPrevPos() {
		return getDataManager().get(PREVBLOCKPOS);
	}

	public void setPrevPos(BlockPos pos) {
		getDataManager().set(PREVBLOCKPOS, pos);
	}

	public boolean isQuantumLocked() {
		return getDataManager().get(QUANTUM);
	}

	public void setQuantum(boolean locked) {
		getDataManager().set(QUANTUM, locked);
	}
	
	@Override
	protected boolean isMovementBlocked() {
		return true;
	}

	public boolean isAngel() {
		return false;
	}

	public void invokeSeen(EntityPlayer player) {
		// TODO Do nothing.
	}

	public boolean quantumCheck() {

		if (WAConfig.angels.freezeOnAngel) {

			List<EntityQuantumLockBase> quantumLockBases = this.world.getEntitiesWithinAABB(EntityQuantumLockBase.class, this.getEntityBoundingBox().grow(25));
			boolean flag = quantumLockBases.isEmpty();
			if (flag) {
				setSeenTime(0);
			} else {
				for (EntityQuantumLockBase base : quantumLockBases) {
					if (base.getUniqueID() != this.getUniqueID() && world.isBlockLoaded(getPosition()) && base.getDistance(this) < 25) {
						return AngelUtils.canSee(base, this);
					}
				}
			}
		}
		return false;
	}
	
}
