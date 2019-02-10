package me.suff.angels.common.entities;

import me.suff.angels.common.misc.WAConstants;
import me.suff.angels.config.WAConfig;
import me.suff.angels.utils.AngelUtils;
import me.suff.angels.utils.ViewUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class EntityQuantumLockBase extends EntityMob {
	
	private static final DataParameter<Boolean> IS_SEEN = EntityDataManager.createKey(EntityQuantumLockBase.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> TIME_VIEWED = EntityDataManager.createKey(EntityQuantumLockBase.class, DataSerializers.VARINT);
	private static final DataParameter<BlockPos> PREVBLOCKPOS = EntityDataManager.createKey(EntityQuantumLockBase.class, DataSerializers.BLOCK_POS);
	private static final DataParameter<Boolean> QUANTUM = EntityDataManager.createKey(EntityQuantumLockBase.class, DataSerializers.BOOLEAN);
	
	public EntityQuantumLockBase(World worldIn) {
		super(worldIn);
	}
	
	@Override
	public void onLivingUpdate() {
		
		if (!world.isRemote && ticksExisted % 4 == 0) {
			setQuantum(quantumCheck());
		}
		
		super.onLivingUpdate();
		
		if (!isQuantumLocked() || WAConfig.angels.freezeOnAngel) {
			
			rotationYawHead = rotationYaw;
			if (!world.isRemote && ticksExisted % 5 == 0) {
				List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(WAConfig.angels.stalkRange));
				players.removeIf(player -> player.isSpectator() || player.isInvisible() || player.isPlayerSleeping());
				
				if (players.isEmpty()) {
					setSeenTime(0);
					return;
				}
				
				EntityPlayer targetPlayer = null;
				for (EntityPlayer player : players) {
					if (ViewUtil.isInSight(player, this) && !AngelUtils.isDarkForPlayer(this, player)) {
						setSeenTime(getSeenTime() + 1);
						invokeSeen(player);
						return;
					} else if (targetPlayer == null) {
						targetPlayer = player;
						
						setSeenTime(0);
					}
				}
				
				Vec3d vecPos = getPositionVector();
				Vec3d vecPlayerPos = targetPlayer.getPositionVector();
				float angle = (float) Math.toDegrees((float) Math.atan2(vecPos.z - vecPlayerPos.z, vecPos.x - vecPlayerPos.x));
				rotationYawHead = rotationYaw = angle > 180 ? angle : angle + 90;
				if (isSeen()) return;
				if (getDistance(targetPlayer) < 2)
					attackEntityAsMob(targetPlayer);
				else
					moveTowards(targetPlayer);
			}
		}
	}
	
	public void moveTowards(EntityLivingBase targetPlayer) {
		getNavigator().tryMoveToEntityLiving(targetPlayer, WAConfig.angels.moveSpeed);
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
	
	public void invokeSeen(EntityPlayer player) {
		setNoAI(true);
		getLookHelper().setLookPositionWithEntity(player, 30, 30);
		getNavigator().setPath(null, 0);
		
	}
	
	private boolean quantumCheck() {
		
		if (WAConfig.angels.freezeOnAngel) {
			List<EntityQuantumLockBase> quantumLockBases = world.getEntitiesWithinAABB(EntityQuantumLockBase.class, getEntityBoundingBox().grow(25));
			boolean flag = quantumLockBases.isEmpty();
			if (flag) {
				setSeenTime(0);
			} else {
				for (EntityQuantumLockBase base : quantumLockBases) {
					if (base.getUniqueID() != getUniqueID() && world.isBlockLoaded(getPosition()) && base.getDistance(this) < 25) {
						if (ViewUtil.canEntitySee(base, this)) return true;
					}
				}
			}
		} else {
			setQuantum(false);
			return false;
		}
		return false;
	}
	
}
