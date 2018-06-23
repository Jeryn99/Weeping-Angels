package me.sub.angels.utils;

import me.sub.angels.common.WAObjects;
import me.sub.angels.common.WAObjects.WAItems;
import me.sub.angels.common.entities.EntityAngel;
import me.sub.angels.events.EventAngelTeleport;
import me.sub.angels.main.WAConstants;
import me.sub.angels.main.config.WAConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;

public class AngelUtils {
	
	/**
	 * Takes in a player, checks if they have a torch in either hand, changes it to a blown out one
	 */
	public static void blowOutTorch(EntityPlayer p) {
		if (!p.world.isRemote && WAConfig.angels.torchBlowOut) {
			for (Object hand : EnumHand.values()) {
				ItemStack torch = p.getHeldItem((EnumHand) hand);
				if (torch.getItem() == Item.getItemFromBlock(Blocks.TORCH) || torch.getItem() == Item.getItemFromBlock(Blocks.REDSTONE_TORCH)) {
					String itemName = torch.getDisplayName();
					ItemStack newStack = new ItemStack(WAItems.UNLIT_TORCH);
					newStack.setStackDisplayName(itemName);
					int count = torch.getCount();
					torch.setCount(0);
					newStack.setCount(count);
					if (hand == EnumHand.MAIN_HAND) {
						p.world.playSound(null, p.posX, p.posY, p.posZ, WAObjects.Sounds.BLOW, SoundCategory.PLAYERS, 1.0F, 1.0F / (p.world.rand.nextFloat() * 0.4F + 1.2F) + 0.5F);
						p.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, newStack);
					} else {
						p.world.playSound(null, p.posX, p.posY, p.posZ, WAObjects.Sounds.BLOW, SoundCategory.PLAYERS, 1.0F, 1.0F / (p.world.rand.nextFloat() * 0.4F + 1.2F) + 0.5F);
						p.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, newStack);
					}
				}
			}
		}
	}
	
	public static boolean teleportDimEntity(Entity entity, BlockPos pos, int targetDim, EntityAngel entityBeingWatched) {
		if (entity.getEntityWorld().isRemote || entity.isRiding() || entity.isBeingRidden() || !entity.isEntityAlive()) {
			return false;
		}
		
		EntityPlayerMP player = null;
		
		if (entity instanceof EntityPlayerMP) {
			player = (EntityPlayerMP) entity;
			
			if (player.world.rand.nextInt(2) == 2) {
				player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 600, 3));
				player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 600, 1));
				player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 600, 3));
				player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 600, 3));
			}
			
			if (entityBeingWatched != null) {
				MinecraftForge.EVENT_BUS.post(new EventAngelTeleport(player, entityBeingWatched));
			}
		}
		
		int from = entity.dimension;
		if (from != targetDim) {
			MinecraftServer server = player == null ? entity.getServer() : player.mcServer;
			WorldServer fromDim = server.getWorld(from);
			WorldServer toDim = server.getWorld(targetDim);
			Teleporter teleporter = new WAMTeleporter(toDim);
			
			if (player != null) {
				server.getPlayerList().transferPlayerToDimension(player, targetDim, teleporter);
				if (from == 1 && entity.isEntityAlive()) {
					toDim.spawnEntity(entity);
					toDim.updateEntityWithOptionalForce(entity, false);
				}
			} else {
				NBTTagCompound tagCompound = entity.serializeNBT();
				float rotationYaw = entity.rotationYaw;
				float rotationPitch = entity.rotationPitch;
				fromDim.removeEntity(entity);
				Entity newEntity = EntityList.createEntityFromNBT(tagCompound, toDim);
				
				if (newEntity != null) {
					newEntity.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), rotationYaw, rotationPitch);
					newEntity.forceSpawn = true;
					toDim.spawnEntity(newEntity);
					newEntity.forceSpawn = false;
				} else {
					return false;
				}
			}
		}
		
		if (!entity.world.isBlockLoaded(pos)) {
			entity.world.getChunkFromBlockCoords(pos);
		}
		
		if (player != null && player.connection != null) {
			player.connection.setPlayerLocation(pos.getX() + 0.5D, pos.getY() + 0.1D, pos.getZ() + 0.5D, player.rotationYaw, player.rotationPitch);
			player.addExperienceLevel(0);
		} else {
			entity.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY() + 0.1D, pos.getZ() + 0.5D);
		}
		
		entity.fallDistance = 0;
		
		return true;
	}

	/**
	 * Method that detects whether entityBeingWatched is the the view sight of viewer
	 *
	 * @param viewer             The viewer entity
	 * @param entityBeingWatched The entity being watched by viewer
	 */
	public static boolean isInSight(EntityLivingBase viewer, Entity entityBeingWatched) {
		double dx = entityBeingWatched.posX - viewer.posX;
		double dz;
		for (dz = entityBeingWatched.posZ - viewer.posZ; dx * dx + dz * dz < 1.0E-4D; dz = (Math.random() - Math.random()) * 0.01D) {
			dx = (Math.random() - Math.random()) * 0.01D;
		}
		while (viewer.rotationYaw > 360) {
			viewer.rotationYaw -= 360;
		}
		while (viewer.rotationYaw < -360) {
			viewer.rotationYaw += 360;
		}
		float yaw = (float) (Math.atan2(dz, dx) * 180.0D / Math.PI) - viewer.rotationYaw;
		yaw = yaw - 90;
		while (yaw < -180) {
			yaw += 360;
		}
		while (yaw >= 180) {
			yaw -= 360;
		}

		return yaw < 60 && yaw > -60 && viewer.canEntityBeSeen(entityBeingWatched);
	}

	/**
	 * Method that detects whether a tile is the the view sight of viewer
	 *
	 * @param viewer The viewer entity
	 * @param tile The tile being watched by viewer
	 */
	public static boolean isInSightTile(EntityLivingBase viewer, TileEntity tile) {
		double dx = tile.getPos().getX() - viewer.posX;
		double dz;
		for (dz = tile.getPos().getX() - viewer.posZ; dx * dx + dz * dz < 1.0E-4D; dz = (Math.random() - Math.random()) * 0.01D) {
			dx = (Math.random() - Math.random()) * 0.01D;
		}
		while (viewer.rotationYaw > 360) {
			viewer.rotationYaw -= 360;
		}
		while (viewer.rotationYaw < -360) {
			viewer.rotationYaw += 360;
		}
		float yaw = (float) (Math.atan2(dz, dx) * 180.0D / Math.PI) - viewer.rotationYaw;
		yaw = yaw - 90;
		while (yaw < -180) {
			yaw += 360;
		}
		while (yaw >= 180) {
			yaw -= 360;
		}

		return yaw < 60 && yaw > -60;
	}
	
	@Deprecated
	public static void getAllAngels(EntityAngel angel_viewer) {
		for (EntityAngel angel2 : angel_viewer.world.getEntitiesWithinAABB(EntityAngel.class, angel_viewer.getEntityBoundingBox().grow(20, 20, 20))) {
			if (angel_viewer.canEntityBeSeen(angel2) && angel_viewer != angel2 && isInSight(angel_viewer, angel2)) {
				angel2.setSeen(true);
			}
		}
	}
	
	public static boolean isDarkForPlayer(EntityAngel angel, EntityLivingBase living) {
		return !living.isPotionActive(MobEffects.NIGHT_VISION) && angel.world.getLight(angel.getPosition()) == 0 && !WAUtils.handLightCheck(living);
	}

	public static void handleKeyThief(Entity entity, EntityAngel angel) {
		if (entity instanceof EntityPlayerMP && !entity.world.isRemote) {
			EntityPlayerMP player = (EntityPlayerMP) entity;
			for (ItemStack stack : player.inventory.mainInventory) {
				
				if (stack.getItem().getRegistryName().toString().equals(WAConstants.TARDIS_MOD_KEY) || stack.getItem().getRegistryName().toString().equals(WAConstants.DALEK_MOD_KEY)) {
					
					ItemStack keyStack = stack.copy();
					
					if (angel.getHeldItemMainhand().isEmpty()) {
						angel.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, keyStack);
						stack.setCount(0);
						return;
					} else {
						if (angel.getHeldItemOffhand().isEmpty()) {
							angel.setHeldItem(EnumHand.OFF_HAND, keyStack);
							stack.setCount(0);
							return;
						}
					}
				}
			}
		}
	}
	
	// Teleporter
	public static class WAMTeleporter extends Teleporter {
		
		public WAMTeleporter(WorldServer worldIn) {
			super(worldIn);
		}
		
		@Override
		public void placeInPortal(Entity entity, float rotationYaw) {}
		
		@Override
		public boolean placeInExistingPortal(Entity entity, float float_for_something) {
			return true;
		}
		
		@Override
		public boolean makePortal(Entity entity) {
			return true;
		}
		
		@Override
		public void removeStalePortalLocations(long stale_long) {
			
		}
	}
	
}
