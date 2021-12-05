package me.suff.mc.angels.compat.tardis;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.api.EventAngelBreakEvent;
import me.suff.mc.angels.common.entities.QuantumLockEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.tardis.mod.controls.HandbrakeControl;
import net.tardis.mod.controls.LandingTypeControl;
import net.tardis.mod.controls.ThrottleControl;
import net.tardis.mod.helper.TardisHelper;
import net.tardis.mod.helper.WorldHelper;
import net.tardis.mod.misc.SpaceTimeCoord;
import net.tardis.mod.sounds.TSounds;
import net.tardis.mod.subsystem.StabilizerSubsystem;
import net.tardis.mod.subsystem.Subsystem;
import net.tardis.mod.tileentities.ConsoleTile;
import net.tardis.mod.tileentities.console.misc.DistressSignal;
import net.tardis.mod.world.dimensions.TDimensions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* Created by Craig on 11/02/2021 */
public class TardisMod {

    public int hbb = 0;

    public static void enableTardis() {
        MinecraftForge.EVENT_BUS.register(new TardisMod());
        WeepingAngels.LOGGER.info("Tardis Mod Detected! Enabling Compatibility Features!");
    }

    /*This method is called from WATeleporter::getRandomDimension, it removes all Tardis interior dimensions
    The reason for this, is because we do not want players teleported into those for the following reasons:
    1. Massive chance of being teleported into just, the void, death, who wants that?
    2. Even if the void wasn't a thing, why would angels canonically send you into a time machine? */
    public static ArrayList<ServerWorld> cleanseDimensions(ArrayList<ServerWorld> serverWorlds) {
        RegistryKey<DimensionType>[] dimensionTypes = new RegistryKey[]{TDimensions.DimensionTypes.TARDIS_TYPE, TDimensions.DimensionTypes.VORTEX_TYPE, TDimensions.DimensionTypes.SPACE_TYPE};
        for (RegistryKey<DimensionType> dimensionType : dimensionTypes) {
            serverWorlds.removeIf(serverWorld -> WorldHelper.areDimensionTypesSame(serverWorld, dimensionType));
        }
        return serverWorlds;
    }

    public static void create(MinecraftServer server, BlockPos blockPos) {
        for (ServerWorld world : TardisHelper.getTardises(server)) {
            TardisHelper.getConsoleInWorld(world).ifPresent(other -> {
                other.addDistressSignal(new DistressSignal("Angels Hungry!", new SpaceTimeCoord(World.OVERWORLD, blockPos)));
            });
        }
    }

    /* Before you ask, imagine how many roundels would just be ripped from the walls or a Angel just nuking a Tardis from existence*/
    @SubscribeEvent
    public void onAngelBlockBreak(EventAngelBreakEvent breakBlockEvent) {
        boolean isTardisDim = WorldHelper.areDimensionTypesSame(breakBlockEvent.getWorld(), TDimensions.DimensionTypes.TARDIS_TYPE);
        boolean isTardisBlock = breakBlockEvent.getBlockState().getBlock().getRegistryName().toString().toLowerCase().contains("tardis:");
        breakBlockEvent.setCanceled(isTardisDim || isTardisBlock);
    }

    @SubscribeEvent
    public void onAngelLive(LivingEvent.LivingUpdateEvent event) {

        if (!(event.getEntity() instanceof QuantumLockEntity)) return;
        QuantumLockEntity angel = (QuantumLockEntity) event.getEntity();

        // Do stuff within the Tardis Dimension
        if (WorldHelper.areDimensionTypesSame(angel.level, TDimensions.DimensionTypes.TARDIS_TYPE)) {
            World world = angel.level;
            ConsoleTile console = (ConsoleTile) world.getBlockEntity(TardisHelper.TARDIS_POS);

            if (world instanceof ServerWorld) {

                DistressSignal angelSig = null;
                for (DistressSignal signal : console.getDistressSignals()) {
                    if (signal.getMessage().contains("Angels")) {
                        console.setDestination(signal.getSpaceTimeCoord());

                        if (!console.isInFlight()) {

                            console.getSubsystem(StabilizerSubsystem.class).ifPresent(sys -> {
                                sys.setControlActivated(true);
                            });

                            console.getControl(HandbrakeControl.class).ifPresent(sys -> {
                                sys.setFree(true);
                                sys.markDirty();
                                sys.startAnimation();
                            });


                            console.getControl(LandingTypeControl.class).ifPresent(landingTypeControl -> {
                                landingTypeControl.setLandType(LandingTypeControl.EnumLandType.UP);
                                landingTypeControl.markDirty();
                                landingTypeControl.startAnimation();
                            });


                            console.getControl(ThrottleControl.class).ifPresent(sys -> {
                                sys.setAmount(1F);
                                sys.startAnimation();
                                sys.markDirty();
                            });

                            angelSig = signal;
                            console.takeoff();
                        }
                    }
                }
                if (angelSig != null) {
                    console.getDistressSignals().remove(angelSig);
                }
            }

            // Drain Fuel
            if (angel.tickCount % 60 == 0) {
                boolean isAngelHealthHalfed = angel.getHealth() == angel.getMaxHealth() / 2;
                if (console != null) {

                    if (console.getArtron() > 0) {
                        BlockPos artonPos = console.getBlockPos();
                        Vector3d end = WorldHelper.vecFromPos(artonPos);
                        Vector3d start = WorldHelper.vecFromPos(angel.blockPosition());
                        Vector3d path = start.subtract(end);
                        for (int i = 0; i < 10; ++i) {
                            double percent = (double) i / 10.0D;
                            Vector3d spawnPoint = new Vector3d(artonPos.getX() + 0.5D + path.x() * percent, artonPos.getY() + 1.3D + path.y() * percent, artonPos.getZ() + 0.5D + path.z * percent);
                            if (spawnPoint.distanceTo(end) <= 3.5D) {
                                angel.level.addParticle(ParticleTypes.END_ROD, spawnPoint.x, spawnPoint.y, spawnPoint.z, 0, 0, 0);
                            }
                        }
                        console.setArtron(console.getArtron() - (isAngelHealthHalfed ? 5 : 1));
                        angel.heal(0.5F);
                    }

                    //No Fuel? No Problem, we'll just rip your systems apart to find some
                    if (console.getArtron() <= 0 && console.getLevel().getGameTime() % 120 == 0) {
                        List<Subsystem> subsystems = console.getSubSystems();
                        if (!subsystems.isEmpty()) {
                            Subsystem randomSubsystem = subsystems.get(world.random.nextInt(subsystems.size()));
                            if (world.random.nextBoolean()) {
                                for (int i = 0; i < 18; ++i) {
                                    double angle = Math.toRadians(i * 20);
                                    double x = Math.sin(angle);
                                    double z = Math.cos(angle);
                                    BlockPos pos = new BlockPos(0, 128, 0);
                                    world.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), TSounds.ELECTRIC_SPARK.get(), SoundCategory.BLOCKS, 0.05F, 1.0F, false);
                                    world.addParticle(ParticleTypes.LAVA, (double) pos.getX() + 0.5D + x, (double) pos.getY() + world.random.nextDouble(), (double) pos.getZ() + 0.5D + z, 0.0D, 0.0D, 0.0D);
                                }
                                if (randomSubsystem.getHealth() > 0) {
                                    randomSubsystem.damage(null, world.random.nextInt(5));
                                }
                            }
                        }
                    }
                }
            }

            // Mess with the lights
            if (angel.tickCount % 500 == 0) {
                if (console != null) {
                    int randLight = Objects.requireNonNull(console.getLevel()).random.nextInt(15);
                    console.getInteriorManager().setLight(MathHelper.clamp(randLight, 0, 15));
                }
            }
        }
    }

    ;

}
