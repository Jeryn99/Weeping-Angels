package me.swirtzly.angels.compat.tardis;

import me.swirtzly.angels.common.entities.QuantumLockBaseEntity;
import me.swirtzly.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.angels.compat.events.EventAngelBreakEvent;
import me.swirtzly.angels.compat.events.EventAngelTeleport;
import me.swirtzly.angels.config.WAConfig;
import me.swirtzly.angels.utils.PlayerUtils;
import me.swirtzly.angels.utils.WATeleporter;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.tardis.mod.dimensions.DimensionTardis;
import net.tardis.mod.helper.TardisHelper;
import net.tardis.mod.misc.EmotionHandler;
import net.tardis.mod.tileentities.TileEntityConsole;
import net.tardis.mod.tileentities.exteriors.TileEntityExterior;

/**
 * Created by Swirtzly on 04/03/2020 @ 20:52
 */
public class Tardis {
	
	@SubscribeEvent
	public void onAngelBlockBreak(EventAngelBreakEvent breakBlockEvent) {
		breakBlockEvent.setCanceled(breakBlockEvent.getAngel().world.dimension instanceof DimensionTardis);
	}
	
	@SubscribeEvent
	public void onAngelJoinWorld(EntityJoinWorldEvent event) {
		if (event.getWorld().dimension instanceof DimensionTardis) {
			if (event.getEntity() instanceof WeepingAngelEntity) {
				TileEntityConsole console = (TileEntityConsole) event.getWorld().getTileEntity(TardisHelper.TARDIS_POS);
				if (console != null) {
					console.getInteriorManager().setAlarmOn(true);
					console.getEmotionHandler().setMood(EmotionHandler.EnumHappyState.SAD.getTreshold());
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onAngelLive(LivingEvent.LivingUpdateEvent event) {
		if (event.getEntity() instanceof QuantumLockBaseEntity) {
			QuantumLockBaseEntity angel = (QuantumLockBaseEntity) event.getEntity();
			
			// Tardis Dimension
			if (angel.world.dimension instanceof DimensionTardis) {
				World world = angel.world;
				TileEntityConsole console = (TileEntityConsole) world.getTileEntity(TardisHelper.TARDIS_POS);
				if (angel.ticksExisted % 500 == 0) {
					if (console != null) {
						console.getInteriorManager().setLight(console.getInteriorManager().getLight() > 0 ? 0 : 15);
					}
				}
				
				if (angel.ticksExisted % 6000 == 0 && world.rand.nextInt(10) < 5) {
					DimensionType Nworld = WATeleporter.getRandomDimension(angel.world.rand);
					if (console != null) {
						// console.setDestination(Nworld, console.randomizeCoords(console.getLocation(), 7000));
						// console.takeoff();
					}
				}
			}
			
			// Everywhere else to steal Tardis
			for (TileEntity tileEntity : angel.world.loadedTileEntityList) {
				if (tileEntity instanceof TileEntityExterior) {
					TileEntityExterior exterior = (TileEntityExterior) tileEntity;
					exterior.open();
				}
			}
			
		}
	}
	
	@SubscribeEvent
	public void onPlayerTeleported(EventAngelTeleport teleport) {
		if (teleport.getTargetDimension().dimension instanceof DimensionTardis) {
			teleport.setTargetDimension(DimensionManager.getWorld(ServerLifecycleHooks.getCurrentServer(), DimensionType.OVERWORLD, false, true));
		}
	}
	
}
