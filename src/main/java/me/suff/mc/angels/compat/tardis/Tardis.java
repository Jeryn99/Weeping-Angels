package me.suff.mc.angels.compat.tardis;

import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.entities.QuantumLockBaseEntity;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.entities.ai.AIMoveTowardsTardis;
import me.suff.mc.angels.compat.events.EventAngelBreakEvent;
import me.suff.mc.angels.compat.events.EventAngelTeleport;
import me.suff.mc.angels.utils.WATeleporter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.tardis.mod.cap.Capabilities;
import net.tardis.mod.cap.items.IWatch;
import net.tardis.mod.cap.items.WatchCapability;
import net.tardis.mod.dimensions.TardisDimension;
import net.tardis.mod.helper.Helper;
import net.tardis.mod.helper.PlayerHelper;
import net.tardis.mod.helper.TardisHelper;
import net.tardis.mod.tileentities.ConsoleTile;

import static net.tardis.mod.events.CommonEvents.WATCH_CAP;

/**
 * Created by Swirtzly on 04/03/2020 @ 20:52
 */
public class Tardis {

    public static void loadCompat() {
        MinecraftForge.EVENT_BUS.register(new Tardis());
    }

    @SubscribeEvent
    public void onAngelBlockBreak(EventAngelBreakEvent breakBlockEvent) {
        breakBlockEvent.setCanceled(breakBlockEvent.getAngel().world.dimension instanceof TardisDimension);

        if (breakBlockEvent.getState().getBlock().getRegistryName().getPath().contentEquals("artron_collector")) {
            World world = breakBlockEvent.getAngel().getEntityWorld();
            TileEntity artronConverterTile = world.getTileEntity(breakBlockEvent.getPos());

                BlockPos artonPos = artronConverterTile.getPos();

                Vec3d end = Helper.vecFromPos(artonPos);
                Vec3d start = Helper.vecFromPos(breakBlockEvent.getAngel().getPosition());
                Vec3d path = start.subtract(end);
                Vec3d speed = path.normalize().scale(0.05D);

                for (int i = 0; i < 10; ++i) {
                    double percent = (double) i / 10.0D;
                    Vec3d spawnPoint = new Vec3d(artonPos.getX() + 0.5D + path.getX() * percent, artonPos.getY() + 1.3D + path.getY() * percent, artonPos.getZ() + 0.5D + path.z * percent);
                    if (spawnPoint.distanceTo(end) <= 3.5D) {
                        breakBlockEvent.getAngel().world.addParticle(ParticleTypes.END_ROD, spawnPoint.x, spawnPoint.y, spawnPoint.z, speed.x, speed.y, speed.z);
                    }
                }

            }
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

                // Drain Fuel
                if (angel.ticksExisted % 60 == 0) {
                    boolean isAngelHealthHalfed = angel.getHealth() == angel.getMaxHealth() / 2;
                    if (console != null) {

                        if (console.getArtron() > 0) {

                            BlockPos artonPos = console.getPos();

                            Vec3d end = Helper.vecFromPos(artonPos);
                            Vec3d start = Helper.vecFromPos(angel.getPosition());
                            Vec3d path = start.subtract(end);
                            Vec3d speed = path.normalize().scale(0.05D);

                            for (int i = 0; i < 10; ++i) {
                                double percent = (double) i / 10.0D;
                                Vec3d spawnPoint = new Vec3d(artonPos.getX() + 0.5D + path.getX() * percent, artonPos.getY() + 1.3D + path.getY() * percent, artonPos.getZ() + 0.5D + path.z * percent);
                                if (spawnPoint.distanceTo(end) <= 3.5D) {
                                    angel.world.addParticle(ParticleTypes.END_ROD, spawnPoint.x, spawnPoint.y, spawnPoint.z, speed.x, speed.y, speed.z);
                                }
                            }

                            console.setArtron(console.getArtron() - (isAngelHealthHalfed ? 5 : 1));
                            angel.heal(0.5F);
                        }
                    }
                }

                // Mess with the lights
                if (angel.ticksExisted % 500 == 0) {
                    if (console != null) {
                        int randLight = console.getWorld().rand.nextInt(15);
                        console.getInteriorManager().setLight(MathHelper.clamp(randLight, 0, 15));
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
        ServerWorld targetDimension = teleport.getTargetDimension();
        if (targetDimension.dimension instanceof TardisDimension) {
            teleport.setTargetDimension(DimensionManager.getWorld(ServerLifecycleHooks.getCurrentServer(), DimensionType.OVERWORLD, false, true));
        }
    }

    @SubscribeEvent
    public void attachItemStackCap(AttachCapabilitiesEvent<ItemStack> event) {
        try {
            if (event.getObject().getItem() == WAObjects.Items.TIMEY_WIMEY_DETECTOR.get()) {
                event.addCapability(WATCH_CAP, new IWatch.Provider(new WatchCapability()));
            }
        } catch (Exception ignored) {
            System.out.println("Failed to attach watch cap to Detector");
        }
    }

    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent entityJoinWorldEvent) {
        if (entityJoinWorldEvent.getEntity() instanceof WeepingAngelEntity) {
            WeepingAngelEntity weepingAngelEntity = (WeepingAngelEntity) entityJoinWorldEvent.getEntity();
            weepingAngelEntity.goalSelector.addGoal(0, new AIMoveTowardsTardis<>(weepingAngelEntity));
        }
    }

}
