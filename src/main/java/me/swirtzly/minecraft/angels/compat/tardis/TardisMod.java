package me.swirtzly.minecraft.angels.compat.tardis;

import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.api.EventAngelBreakEvent;
import me.swirtzly.minecraft.angels.common.entities.QuantumLockBaseEntity;
import net.minecraft.particles.ParticleTypes;
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
import net.tardis.mod.helper.Helper;
import net.tardis.mod.helper.TardisHelper;
import net.tardis.mod.particles.TParticleTypes;
import net.tardis.mod.sounds.TSounds;
import net.tardis.mod.subsystem.Subsystem;
import net.tardis.mod.tileentities.ConsoleTile;
import net.tardis.mod.world.dimensions.TDimensions;

import java.util.ArrayList;
import java.util.List;

/* Created by Craig on 11/02/2021 */
public class TardisMod {

    public static void enableTardis() {
        MinecraftForge.EVENT_BUS.register(new TardisMod());
        WeepingAngels.LOGGER.info("Tardis Mod Detected! Enabling Compatibility Features!");
    }

    /*This method is called from WATeleporter::getRandomDimension, it removes all Tardis interior dimensions
    The reason for this, is because we do not want players teleported into those for the following reasons:
    1. Massive chance of being teleported into just, the void, death, who wants that?
    2. Even if the void wasn't a thing, why would angels canonically send you into a time machine? */
    public static ArrayList< ServerWorld > cleanseDimensions(ArrayList< ServerWorld > serverWorlds) {
        RegistryKey< DimensionType >[] dimensionTypes = new RegistryKey[]{TDimensions.DimensionTypes.TARDIS_TYPE, TDimensions.DimensionTypes.VORTEX_TYPE, TDimensions.DimensionTypes.SPACE_TYPE};
        for (RegistryKey< DimensionType > dimensionType : dimensionTypes) {
            serverWorlds.removeIf(serverWorld -> Helper.areDimensionTypesSame(serverWorld, dimensionType));
        }
        return serverWorlds;
    }

    /* Before you ask, imagine how many roundels would just be ripped from the walls or a Angel just nuking a Tardis from existence*/
    @SubscribeEvent
    public void onAngelBlockBreak(EventAngelBreakEvent breakBlockEvent) {
        boolean isTardisDim = Helper.areDimensionTypesSame(breakBlockEvent.getWorld(), TDimensions.DimensionTypes.TARDIS_TYPE);
        boolean isTardisBlock = breakBlockEvent.getBlockState().getBlock().getRegistryName().toString().toLowerCase().contains("tardis:");
        breakBlockEvent.setCanceled(isTardisDim || isTardisBlock);
    }

    @SubscribeEvent
    public void onAngelLive(LivingEvent.LivingUpdateEvent event) {

        if (event.getEntity() instanceof QuantumLockBaseEntity) {
            QuantumLockBaseEntity angel = (QuantumLockBaseEntity) event.getEntity();

            // Do stuff within the Tardis Dimension
            if (Helper.areDimensionTypesSame(angel.world, TDimensions.DimensionTypes.TARDIS_TYPE)) {
                World world = angel.world;
                ConsoleTile console = (ConsoleTile) world.getTileEntity(TardisHelper.TARDIS_POS);

                // Drain Fuel
                if (angel.ticksExisted % 60 == 0) {
                    boolean isAngelHealthHalfed = angel.getHealth() == angel.getMaxHealth() / 2;
                    if (console != null) {

                        if (console.getArtron() > 0) {
                            BlockPos artonPos = console.getPos();
                            Vector3d end = Helper.vecFromPos(artonPos);
                            Vector3d start = Helper.vecFromPos(angel.getPosition());
                            Vector3d path = start.subtract(end);
                            for (int i = 0; i < 10; ++i) {
                                double percent = (double) i / 10.0D;
                                Vector3d spawnPoint = new Vector3d(artonPos.getX() + 0.5D + path.getX() * percent, artonPos.getY() + 1.3D + path.getY() * percent, artonPos.getZ() + 0.5D + path.z * percent);
                                if (spawnPoint.distanceTo(end) <= 3.5D) {
                                    angel.world.addParticle(TParticleTypes.ARTRON.get(), spawnPoint.x, spawnPoint.y, spawnPoint.z, 0, 0, 0);
                                }
                            }
                            console.setArtron(console.getArtron() - (isAngelHealthHalfed ? 5 : 1));
                            angel.heal(0.5F);
                        }

                        //No Fuel? No Problem, we'll just rip your systems apart to find some
                        if (console.getArtron() <= 0 && console.getWorld().getGameTime() % 120 == 0) {
                            List< Subsystem > subsystems = console.getSubSystems();
                            if (!subsystems.isEmpty()) {
                                Subsystem randomSubsystem = subsystems.get(world.rand.nextInt(subsystems.size()));
                                if (world.rand.nextBoolean()) {
                                    for (int i = 0; i < 18; ++i) {
                                        double angle = Math.toRadians(i * 20);
                                        double x = Math.sin(angle);
                                        double z = Math.cos(angle);
                                        BlockPos pos = new BlockPos(0, 128, 0);
                                        world.playSound(pos.getX(), pos.getY(), pos.getZ(), TSounds.ELECTRIC_SPARK.get(), SoundCategory.BLOCKS, 0.05F, 1.0F, false);
                                        world.addParticle(ParticleTypes.LAVA, (double) pos.getX() + 0.5D + x, (double) pos.getY() + world.rand.nextDouble(), (double) pos.getZ() + 0.5D + z, 0.0D, 0.0D, 0.0D);
                                    }
                                    if (randomSubsystem.getHealth() > 0) {
                                        randomSubsystem.damage(null, world.rand.nextInt(5));
                                    }
                                }
                            }
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
            }

        }
    }

}
