package me.fril.angels.combat.tardis;

import me.fril.angels.common.entities.EntityWeepingAngel;
import me.fril.angels.config.WAConfig;
import me.fril.angels.utils.Teleporter;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.dimensions.WorldProviderTardis;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageDoorOpen;

public class TardisMod {
	
	public static void register() {
		MinecraftForge.EVENT_BUS.register(new TardisMod());
	}
	
	@SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent e) {
		if (e.getEntity() instanceof EntityWeepingAngel) {
			EntityWeepingAngel weepingAngel = (EntityWeepingAngel) e.getEntity();
			weepingAngel.world.loadedTileEntityList.forEach(tileEntity -> processTile(tileEntity, weepingAngel));
		}
	}
	
	private void processTile(TileEntity tileEntity, EntityWeepingAngel weepingAngel) {
		if (weepingAngel.getDistanceSq(tileEntity.getPos()) > 10) return;
		
		//Steal the fuel from the tardis if in the interior
		if (tileEntity instanceof TileEntityTardis && weepingAngel.world.provider instanceof WorldProviderTardis) {
			TileEntityTardis tardis = (TileEntityTardis) tileEntity;
			
			if (weepingAngel.ticksExisted % 200 == 0) {
				tardis.fuel = tardis.fuel - 1;
				weepingAngel.heal(1);
			}
		}
		
		//Navigate to Tardis Exterior
		if (tileEntity instanceof TileEntityDoor) {
			TileEntityDoor door = (TileEntityDoor) tileEntity;
			weepingAngel.getNavigator().tryMoveToXYZ(tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ(), WAConfig.angels.moveSpeed);
			
			if (weepingAngel.getDistanceSq(tileEntity.getPos()) < 1) {
				if (!weepingAngel.getHeldItemMainhand().isEmpty()) {
					door.isLocked = false;
					door.markDirty();
					NetworkHandler.NETWORK.sendToDimension(new MessageDoorOpen(door.getPos(), door), door.getWorld().provider.getDimension());
					BlockPos pos = door.getConsolePos().offset(EnumFacing.SOUTH, 3);
					weepingAngel.changeDimension(TDimensions.TARDIS_ID, new Teleporter.WATeleport(pos.getX(), pos.getY(), pos.getZ()));
					
					try {
						TileEntityTardis tile = ((TileEntityTardis) DimensionManager.getWorld(TDimensions.TARDIS_ID).getTileEntity(door.getConsolePos()));
						tile.setTargetDimension(DimensionManager.getStaticDimensionIDs()[weepingAngel.world.rand.nextInt(DimensionManager.getStaticDimensionIDs().length)]);
						tile.travel();
					} catch (Exception exc) {
					}
					
				}
			}
		}
	}
}
