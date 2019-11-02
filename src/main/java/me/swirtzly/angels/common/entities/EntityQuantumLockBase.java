package me.swirtzly.angels.common.entities;

import me.swirtzly.angels.common.entities.ai.AngelFormation;
import me.swirtzly.angels.common.misc.WAConstants;
import me.swirtzly.angels.config.WAConfig;
import me.swirtzly.angels.utils.AngelUtils;
import me.swirtzly.angels.utils.ViewUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EntityQuantumLockBase extends EntityMob {
	
	private static final DataParameter<Boolean> IS_SEEN = EntityDataManager.createKey(EntityQuantumLockBase.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> TIME_VIEWED = EntityDataManager.createKey(EntityQuantumLockBase.class, DataSerializers.VARINT);
	private static final DataParameter<BlockPos> PREVBLOCKPOS = EntityDataManager.createKey(EntityQuantumLockBase.class, DataSerializers.BLOCK_POS);
	private static final DataParameter<Boolean> QUANTUM = EntityDataManager.createKey(EntityQuantumLockBase.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_LEADER = EntityDataManager.createKey(EntityQuantumLockBase.class, DataSerializers.BOOLEAN);
	private static AxisAlignedBB SCAN_RANGE = new AxisAlignedBB(0, 0, 0, 1, 1, 1).grow(20);
	//Leader stuff
	private EntityQuantumLockBase LEADER = null;
	private ArrayList<EntityQuantumLockBase> FOLLOWERS = new ArrayList<EntityQuantumLockBase>();
	private AngelFormation FORMATION = null;

	public EntityQuantumLockBase(World worldIn) {
		super(worldIn);
		ArrayList<Vec3d> list = new ArrayList<Vec3d>();
		list.add(new Vec3d(2, 0, -1));
		list.add(new Vec3d(-2, 0, -1));
		FORMATION = new AngelFormation(list);
	}

	public ArrayList<EntityQuantumLockBase> getFollowers() {
		if (!IsLeader()) {
			FOLLOWERS.clear();
		}
		return FOLLOWERS;
	}

	public boolean IsLeader() {
		return getDataManager().get(IS_LEADER);
	}

	public void setIsLeader(boolean isLeader) {
		getDataManager().set(IS_LEADER, isLeader);
	}

	public EntityQuantumLockBase getLeader() {
		return LEADER;
	}

	public void setLeader(EntityQuantumLockBase LEADER) {
		this.LEADER = LEADER;
	}

	public AngelFormation getFormation() {
		return FORMATION;
	}
	
	@Override
	public void onLivingUpdate() {

		if (!world.isRemote && ticksExisted % 4 == 0) {
			setQuantum(quantumCheck());
		}

		if (!world.isRemote) {
			setLeader(scanForLeader());
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
		getDataManager().register(IS_LEADER, false);
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


	public EntityQuantumLockBase scanForLeader() {
		List<EntityQuantumLockBase> angels = world.getEntitiesWithinAABB(EntityQuantumLockBase.class, SCAN_RANGE.offset(this.getPositionVector()));
		//Search range for a commander
		for (EntityQuantumLockBase angel : angels) {
			if (angel.IsLeader()) {
				if (!angel.getFollowers().contains(this))
					angel.addFollower(this);
				return angel;
			}
		}
		//If we can't find one, promote ourselves
		this.setIsLeader(true);

		for (EntityQuantumLockBase angel : angels) {
			angel.setLeader(this);
			this.addFollower(angel);
		}
		return this;
	}

	public void addFollower(EntityQuantumLockBase drone) {
		this.FOLLOWERS.add(drone);
	}
}
