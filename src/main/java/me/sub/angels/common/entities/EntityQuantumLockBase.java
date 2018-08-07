package me.sub.angels.common.entities;

import me.sub.angels.WeepingAngels;
import me.sub.angels.client.models.poses.PoseManager;
import me.sub.angels.common.misc.WAConstants;
import me.sub.angels.config.WAConfig;
import me.sub.angels.utils.AngelUtils;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
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
	protected void entityInit() {
		super.entityInit();
		getDataManager().register(IS_SEEN, false);
		getDataManager().register(TIME_VIEWED, 0);
		getDataManager().register(PREVBLOCKPOS, BlockPos.ORIGIN);
		getDataManager().register(QUANTUM, false);
	}

	public BlockPos getPrevPos() {
		return getDataManager().get(PREVBLOCKPOS);
	}

	public void setPrevPos(BlockPos pos) {
		getDataManager().set(PREVBLOCKPOS, pos);
	}

	public boolean isQuantumLocked(){
		return getDataManager().get(QUANTUM);
	}

	public void setQuantum(boolean locked){
		getDataManager().set(QUANTUM, locked);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean(WAConstants.IS_SEEN, isSeen());
		compound.setInteger(WAConstants.TIME_SEEN, getSeenTime());
		compound.setLong(WAConstants.PREVPOS, getPrevPos().toLong());
		compound.setBoolean(WAConstants.QAUNTUM_LOCKED, isQuantumLocked());
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		if (compound.hasKey(WAConstants.IS_SEEN)) setSeen(compound.getBoolean(WAConstants.IS_SEEN));
		if (compound.hasKey(WAConstants.TIME_SEEN)) setSeenTime(compound.getInteger(WAConstants.TIME_SEEN));
		if (compound.hasKey(WAConstants.PREVPOS)) setPrevPos(getPrevPos());
		if (compound.hasKey(WAConstants.QAUNTUM_LOCKED)) setQuantum(compound.getBoolean(WAConstants.QAUNTUM_LOCKED));
	}
	
	public boolean isSeen() {
		return getDataManager().get(IS_SEEN);
	}
	
	public void setSeen(boolean beingViewed) {
		getDataManager().set(IS_SEEN, beingViewed);
	}
	
	public int getSeenTime() {
		return getDataManager().get(TIME_VIEWED);
	}
	
	public void setSeenTime(int time) {
		getDataManager().set(TIME_VIEWED, time);
	}
	
	private void quantumLocking() {
		if(!WAConfig.angels.angelLocking) return;

		List<EntityQuantumLockBase> entityList = world.getEntitiesWithinAABB(EntityQuantumLockBase.class, getEntityBoundingBox().expand(32.0D, 32.0D, 32.0D));
		for (EntityQuantumLockBase viewer : entityList) {
			if (viewer != this && !world.isRemote) {

				if (viewer instanceof EntityWeepingAngel) {
					EntityWeepingAngel angelViewer = (EntityWeepingAngel) viewer;
					if (!angelViewer.getPose().equals(PoseManager.AngelPoses.HIDING_FACE.toString())) {
						continue;
					}
				}

				boolean viewed = AngelUtils.isInSight(viewer, this);

				if (viewed) {
					setSeenTime(getSeenTime() + 1);
				} else {
					setSeenTime(0);
				}

				setSeen(viewed);
				setQuantum(viewed);
			}
		}
	}

	@Override
	protected boolean isMovementBlocked() {
		return true;
	}
	
	@Override
	public void onUpdate() {
		quantumLocking();
		this.setSeen(isInView());
		super.onUpdate();
	}
	
	private boolean isInView() {
		if (world.playerEntities != null) {
			for (EntityPlayer player : world.playerEntities) {
				if (AngelUtils.isInSight(player, this) && !AngelUtils.isDarkForPlayer(this, player) && !player.isPotionActive(MobEffects.BLINDNESS)) {
					return true;
				}
			}
		}
		setSeenTime(0);
		return false;
	}
}
