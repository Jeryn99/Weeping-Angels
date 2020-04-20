package me.swirtzly.angels.compat.tardis;

import me.swirtzly.angels.common.WAObjects;
import me.swirtzly.angels.common.entities.QuantumLockBaseEntity;
import me.swirtzly.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.angels.common.entities.ai.AIMoveTowardsTardis;
import me.swirtzly.angels.compat.events.EventAngelBreakEvent;
import me.swirtzly.angels.compat.events.EventAngelTeleport;
import me.swirtzly.angels.utils.WATeleporter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.tardis.mod.cap.Capabilities;
import net.tardis.mod.cap.items.IWatch;
import net.tardis.mod.cap.items.WatchCapability;
import net.tardis.mod.dimensions.TardisDimension;
import net.tardis.mod.helper.PlayerHelper;
import net.tardis.mod.helper.TardisHelper;
import net.tardis.mod.tileentities.ConsoleTile;

import java.lang.annotation.Target;

import static net.tardis.mod.events.CommonEvents.WATCH_CAP;

/**
 * Created by Swirtzly on 04/03/2020 @ 20:52
 */
public class Tardis {
	
	@SubscribeEvent
	public void onAngelBlockBreak(EventAngelBreakEvent breakBlockEvent) {
		breakBlockEvent.setCanceled(breakBlockEvent.getAngel().world.dimension instanceof TardisDimension);
	}
	
	@SubscribeEvent
	public void onAngelLive(LivingEvent.LivingUpdateEvent event) {

		if (event.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity playerEntity = (PlayerEntity) event.getEntityLiving();
			for (Hand value : Hand.values()) {
				if (PlayerHelper.isInHand(value, playerEntity, WAObjects.Items.TIMEY_WIMEY_DETECTOR.get())) {
					ItemStack stack = playerEntity.getHeldItem(value);
					if (playerEntity.world.getGameTime() % 100L == 0L) {
						stack.getCapability(Capabilities.WATCH_CAPABILITY).ifPresent((watch) -> watch.tick(playerEntity.world, playerEntity));
					}
				}
			}
		}

		if (event.getEntity() instanceof QuantumLockBaseEntity) {
			QuantumLockBaseEntity angel = (QuantumLockBaseEntity) event.getEntity();

			// Tardis Dimension
			if (angel.world.dimension instanceof TardisDimension) {
				World world = angel.world;
				ConsoleTile console = (ConsoleTile) world.getTileEntity(TardisHelper.TARDIS_POS);

				//Drain Fuel
				if(angel.ticksExisted % 400 == 0){
					boolean isAngelHealthHalfed = angel.getHealth() == angel.getMaxHealth() / 2;
					if (console != null) {
						if(console.getArtron() > 0) {
							console.setArtron(console.getArtron() - (isAngelHealthHalfed ? 5 : 1));
							angel.heal(0.5F);
						}
					}
				}

				//Mess with the lights
				if (angel.ticksExisted % 500 == 0) {
					if (console != null) {
						console.getInteriorManager().setLight(console.getInteriorManager().getLight() > 0 ? 0 : 15);
					}
				}

				// Make Angel Steal Tardis?
				BlockPos consolePos = TardisHelper.TARDIS_POS;
				if (angel.getDistanceSq(consolePos.getX(), consolePos.getY(), consolePos.getZ()) < 7 && angel.ticksExisted % 6000 == 0 && world.rand.nextInt(10) < 5) {
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

	@SubscribeEvent
	public void attachItemStackCap(AttachCapabilitiesEvent<ItemStack> event) {
		if (event.getObject().getItem() == WAObjects.Items.TIMEY_WIMEY_DETECTOR.get()) {
			event.addCapability(WATCH_CAP, new IWatch.Provider(new WatchCapability()));
		}
	}

	@SubscribeEvent
	public void onJoin(EntityJoinWorldEvent entityJoinWorldEvent){
		if(entityJoinWorldEvent.getEntity() instanceof WeepingAngelEntity){
			WeepingAngelEntity weepingAngelEntity = (WeepingAngelEntity) entityJoinWorldEvent.getEntity();
			weepingAngelEntity.goalSelector.addGoal(0, new AIMoveTowardsTardis<>(weepingAngelEntity));
		}
	}

}
