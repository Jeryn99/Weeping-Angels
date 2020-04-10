package me.swirtzly.angels.compat.tardis;

import me.swirtzly.angels.common.entities.QuantumLockBaseEntity;
import me.swirtzly.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.angels.compat.events.EventAngelBreakEvent;
import me.swirtzly.angels.compat.events.EventAngelTeleport;
import me.swirtzly.angels.utils.WATeleporter;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.tardis.mod.dimensions.TardisDimension;
import net.tardis.mod.helper.TardisHelper;
import net.tardis.mod.tileentities.ConsoleTile;

/**
 * Created by Swirtzly on 04/03/2020 @ 20:52
 */
public class Tardis {
	
	@SubscribeEvent
	public void onAngelBlockBreak(EventAngelBreakEvent breakBlockEvent) {
		breakBlockEvent.setCanceled(breakBlockEvent.getAngel().world.dimension instanceof TardisDimension);
	}
	
	@SubscribeEvent
	public void onAngelJoinWorld(EntityJoinWorldEvent event) {
		if (event.getWorld().dimension instanceof TardisDimension) {
			if (event.getEntity() instanceof WeepingAngelEntity) {
				ConsoleTile console = (ConsoleTile) event.getWorld().getTileEntity(TardisHelper.TARDIS_POS);
				if (console != null) {
					console.getInteriorManager().setAlarmOn(true);

				}
			}
		}
	}
	
	@SubscribeEvent
	public void onAngelLive(LivingEvent.LivingUpdateEvent event) {
		if (event.getEntity() instanceof QuantumLockBaseEntity) {
			QuantumLockBaseEntity angel = (QuantumLockBaseEntity) event.getEntity();

			// Tardis Dimension
			if (angel.world.dimension instanceof TardisDimension) {
				World world = angel.world;
				ConsoleTile console = (ConsoleTile) world.getTileEntity(TardisHelper.TARDIS_POS);

				//Drain Fuel
				if(angel.ticksExisted % 100 == 0){
					boolean isAngelHealthHalfed = angel.getHealth() == angel.getMaxHealth() / 2;
					if (console != null) {
						console.setArtron(isAngelHealthHalfed ? 5 : 1);
					}
				}

				//Mess with the lights
				if (angel.ticksExisted % 500 == 0) {
					if (console != null) {
						console.getInteriorManager().setLight(console.getInteriorManager().getLight() > 0 ? 0 : 15);
					}
				}

				// Make Angel Steal Tardis?
				if (angel.ticksExisted % 6000 == 0 && world.rand.nextInt(10) < 5) {
					DimensionType Nworld = WATeleporter.getRandomDimension(angel.world.rand);
					if (console != null) {
						 console.setDestination(Nworld, console.randomizeCoords(console.getLocation(), 7000));
						 console.takeoff();
					}
				}
			}
			
		}
	}
	
	@SubscribeEvent
	public void onPlayerTeleported(EventAngelTeleport teleport) {
		if (teleport.getTargetDimension().dimension instanceof TardisDimension) {
			teleport.setTargetDimension(DimensionManager.getWorld(ServerLifecycleHooks.getCurrentServer(), DimensionType.OVERWORLD, false, true));
		}
	}
	
}
