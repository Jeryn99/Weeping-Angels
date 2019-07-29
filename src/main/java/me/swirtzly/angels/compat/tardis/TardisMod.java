package me.swirtzly.angels.compat.tardis;

import me.swirtzly.angels.WeepingAngels;
import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import me.swirtzly.angels.config.WAConfig;
import me.swirtzly.angels.utils.Teleporter;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.dimensions.WorldProviderTardis;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageDoorOpen;

import java.util.Objects;

import static net.tardis.mod.common.tileentity.TileEntityTardis.defaultFuelUse;

public class TardisMod {
	
	public static void register() {
		if (WAConfig.integrations.tardisModIntegration) {
			WeepingAngels.LOGGER.info("Tardis Mod Detected, registering compatibility events");
			MinecraftForge.EVENT_BUS.register(new TardisMod());
		}
	}
	
	@SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent e) {
		if (e.getEntity() instanceof EntityWeepingAngel) {
			EntityWeepingAngel weepingAngel = (EntityWeepingAngel) e.getEntity();
			if (weepingAngel.ticksExisted % 200 == 0 && WAConfig.integrations.tardisFuelTheft && e.getEntity().world.provider instanceof WorldProviderTardis) {
				AxisAlignedBB box = weepingAngel.getEntityBoundingBox().grow(WAConfig.angels.blockBreakRange);
				for (BlockPos pos : BlockPos.getAllInBox(new BlockPos(box.minX, box.minY, box.minZ), new BlockPos(box.maxX, box.maxY, box.maxZ))) {
					IBlockState blockState = weepingAngel.world.getBlockState(pos);
					if (blockState.getBlock().hasTileEntity(blockState)) {
						if (weepingAngel.world.getTileEntity(pos) != null) {
							processTile(Objects.requireNonNull(weepingAngel.world.getTileEntity(pos)), weepingAngel);
						}
					}
				}
			}
		}
		
	}
	
	private void processTile(TileEntity tileEntity, EntityWeepingAngel weepingAngel) {
		if (weepingAngel.getDistanceSq(tileEntity.getPos()) > 15) return;
		//Steal the fuel from the tardis if in the interior
		if (tileEntity instanceof TileEntityTardis && weepingAngel.world.provider instanceof WorldProviderTardis) {
			TileEntityTardis tardis = (TileEntityTardis) tileEntity;
			if (tardis.fuel > 0.0F) {
				tardis.setFuel(tardis.fuel - defaultFuelUse * 1.5F);
				tardis.getWorld().playSound(null, tileEntity.getPos(), TSounds.cloister_bell, SoundCategory.BLOCKS, 1, 1);
				tardis.markDirty();
				weepingAngel.heal(1);
				return;
			}
		}
		
		
		//Navigate to Tardis Exterior
		if (tileEntity instanceof TileEntityDoor) {
			TileEntityDoor door = (TileEntityDoor) tileEntity;
			weepingAngel.getNavigator().tryMoveToXYZ(tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ(), WAConfig.angels.moveSpeed);
			if (weepingAngel.getDistanceSq(tileEntity.getPos()) < 5) {
				if (!weepingAngel.getHeldItemMainhand().isEmpty() || !door.isLocked) {
					NetworkHandler.NETWORK.sendToDimension(new MessageDoorOpen(door.getPos(), door), door.getWorld().provider.getDimension());
					BlockPos pos = door.getConsolePos().offset(EnumFacing.SOUTH, 3);
					weepingAngel.changeDimension(TDimensions.TARDIS_ID, new Teleporter.WATeleport(pos.getX(), pos.getY(), pos.getZ()));
					try {
						TileEntityTardis tile = ((TileEntityTardis) DimensionManager.getWorld(TDimensions.TARDIS_ID).getTileEntity(door.getConsolePos()));
						
						if (tile != null && !tile.isInFlight() && weepingAngel.ticksExisted < 500 && WAConfig.integrations.tardisTheft) {
							
							DimensionType dimensionDest = null;
							
							if (WAConfig.integrations.tardisTheftDimensional) {
								dimensionDest = Teleporter.getRandomDimension(weepingAngel, door.getWorld().rand);
							} else {
								dimensionDest = DimensionManager.getWorld(tile.dimension).provider.getDimensionType();
							}
							
							tile.setDesination(new BlockPos(tile.getPos().getX() + tile.getWorld().rand.nextInt(WAConfig.integrations.theftRange), 64, tile.getPos().getZ() + tile.getWorld().rand.nextInt(WAConfig.integrations.theftRange)), dimensionDest.getId());
							tile.getDoor().setOpen(false);
							tile.startFlight();
							tile.markDirty();
							door.markDirty();
						}
					} catch (Exception exc) {
						WeepingAngels.LOGGER.error("Something went wrong while a Angel entered the tardis", exc);
					}
					
				}
			}
		}
	}
}
