package mc.craig.software.angels.forge.compat.tardis;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.CatacombTracker;
import mc.craig.software.angels.common.blockentity.Plinth;
import mc.craig.software.angels.common.blocks.StatueBaseBlock;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.forge.compat.tardis.registry.TardisBlocks;
import mc.craig.software.angels.util.WAHelper;
import mc.craig.software.angels.util.WATags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tardis.mod.cap.items.sonic.SonicCapability;
import net.tardis.mod.controls.HandbrakeControl;
import net.tardis.mod.controls.LandingTypeControl;
import net.tardis.mod.controls.ThrottleControl;
import net.tardis.mod.enums.EnumDoorState;
import net.tardis.mod.helper.BlockPosHelper;
import net.tardis.mod.helper.TardisHelper;
import net.tardis.mod.helper.WorldHelper;
import net.tardis.mod.items.SonicItem;
import net.tardis.mod.misc.SpaceTimeCoord;
import net.tardis.mod.registries.Schematics;
import net.tardis.mod.schematics.ExteriorUnlockSchematic;
import net.tardis.mod.sounds.TSounds;
import net.tardis.mod.subsystem.NavComSubsystem;
import net.tardis.mod.subsystem.StabilizerSubsystem;
import net.tardis.mod.subsystem.Subsystem;
import net.tardis.mod.tileentities.ConsoleTile;
import net.tardis.mod.tileentities.console.misc.AlarmType;
import net.tardis.mod.tileentities.console.misc.DistressSignal;
import net.tardis.mod.tileentities.exteriors.ExteriorTile;
import net.tardis.mod.world.dimensions.TDimensions;

import java.util.*;
import java.util.stream.Stream;

public class TardisMod {

    public static ExteriorUnlockSchematic getSchem() {
        ExteriorUnlockSchematic schem = (ExteriorUnlockSchematic) Schematics.SCHEMATIC_REGISTRY.get(new ResourceLocation("weeping_angels:exteriors/2005exterior"));
        if (schem == null) {
            ExteriorUnlockSchematic exteriorUnlockSchematic = new ExteriorUnlockSchematic();
            exteriorUnlockSchematic.setId(new ResourceLocation("weeping_angels:exteriors/2005exterior"));
            exteriorUnlockSchematic.setExterior(TardisBlocks.EXTERIOR_2005.getId());
            exteriorUnlockSchematic.setTranslation("exterior.weeping_angels.2005exterior");
            exteriorUnlockSchematic.setUseTranslatedName(true);
            return exteriorUnlockSchematic;
        }
        schem.setId(new ResourceLocation("weeping_angels:exteriors/2005exterior"));
        return schem;
    }

    public static void enableTardis() {
        MinecraftForge.EVENT_BUS.register(new TardisMod());
        WeepingAngels.LOGGER.info("Tardis Mod Detected! Enabling Compatibility Features!");
    }

    public static void create(MinecraftServer server, BlockPos blockPos, ResourceKey<Level> ResourceKey) {
        for (ServerLevel world : TardisHelper.getTardises(server)) {
            TardisHelper.getConsoleInWorld(world).ifPresent(other -> {
                other.addDistressSignal(new DistressSignal("Angels Hungry!", new SpaceTimeCoord(ResourceKey, blockPos)));
            });
        }
    }

    @SubscribeEvent
    public void onAngelLive(LivingEvent.LivingTickEvent event) {

        if (!(event.getEntity() instanceof WeepingAngel)) return;
        WeepingAngel angel = (WeepingAngel) event.getEntity();

        // Do stuff within the Tardis Dimension
        if (WorldHelper.areDimensionTypesSame(angel.level, TDimensions.DimensionTypes.TARDIS_TYPE)) {
            Level world = angel.level;
            ConsoleTile console = (ConsoleTile) world.getBlockEntity(TardisHelper.TARDIS_POS);

            if (world instanceof ServerLevel) {
                if (console.isLanding()) {
                    for (Subsystem system : console.getSubSystems()) {
                        system.setActivated(false);
                    }
                }

                relocateTardis(world, console);
            }

            // Drain Fuel
            if (angel.tickCount % 60 == 0) {
                boolean isAngelHalfHealth = angel.getHealth() == angel.getMaxHealth() / 2;
                if (console != null) {

                    if (console.getArtron() > 0) {
                        BlockPos artonPos = console.getBlockPos();
                        Vec3 end = WorldHelper.vecFromPos(artonPos);
                        Vec3 start = WorldHelper.vecFromPos(angel.blockPosition());
                        Vec3 path = start.subtract(end);
                        for (int i = 0; i < 10; ++i) {
                            double percent = (double) i / 10.0D;
                            Vec3 spawnPoint = new Vec3(artonPos.getX() + 0.5D + path.x() * percent, artonPos.getY() + 1.3D + path.y() * percent, artonPos.getZ() + 0.5D + path.z * percent);
                            if (spawnPoint.distanceTo(end) <= 3.5D && angel.level.isClientSide) {
                                angel.level.addParticle(ParticleTypes.END_ROD, spawnPoint.x, spawnPoint.y, spawnPoint.z, 0.0D, 0.0D, 0.0D);
                            }
                        }
                        console.setArtron(console.getArtron() - (isAngelHalfHealth ? 5 : 1));
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
                                    world.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), TSounds.ELECTRIC_SPARK.get(), SoundSource.BLOCKS, 0.05F, 1.0F, false);
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
                    console.getInteriorManager().setLight(Mth.clamp(randLight, 0, 15));
                }
            }

            return;
        }
        if (angel.level instanceof ServerLevel) {
            Optional<BlockEntity> optTile = angel.level.getChunkAt(angel.blockPosition()).getBlockEntities().values().stream().filter(tile -> tile instanceof ExteriorTile && tile.getBlockPos().closerThan(angel.blockPosition(), 3)).findFirst();
            angel.level.getServer().tell(new TickTask(0, () -> optTile.ifPresent(tile -> {
                        ExteriorTile exterior = (ExteriorTile) tile;
                        if (exterior.getOpen() != EnumDoorState.CLOSED && !exterior.getLocked() && !exterior.isExteriorDeadLocked()) {
                            exterior.transferEntities(Lists.newArrayList(angel)); //TODO force doors open if angel has key
                        }
                    }))
            );
        }
    }
    public static List<BlockPos> getFilteredBlockPositionsInStructure(Pair<BlockPos, Structure> test, ServerLevel world, StructureManager manager, Block filteredBlock) {
        List<BlockPos> list = Lists.newArrayList();
        StructureStart start = manager.getStructureAt(test.getFirst(), test.getSecond());
        Iterator var6 = start.getPieces().iterator();

        while(var6.hasNext()) {
            StructurePiece piece = (StructurePiece)var6.next();
            BoundingBox boundingBox = piece.getBoundingBox();
            Stream<BlockPos> positions = BlockPos.betweenClosedStream(boundingBox).filter((pos) -> world.getBlockState(pos).is(filteredBlock));
            positions.forEach((pos) -> list.add(pos.immutable()));
        }

        return list;
    }

    private static boolean relocateTardis(Level world, ConsoleTile console) {
        if (!console.isInFlight() && console.canFly() && console.getSubsystem(NavComSubsystem.class).orElseGet(null).isActivated()) {

            ResourceKey<Level> spaceTimeDim = console.getCurrentDimension();

            ServerLevel destWorld = console.getLevel().getServer().getLevel(console.getDestinationDimension());
            BlockPos pos = destWorld.findNearestMapStructure(WATags.CATACOMBS, console.getDestinationPosition(), 25, false);

            if(pos == null) return true;

            List<BlockPos> viablePoses = getFilteredBlockPositionsInStructure(new Pair<>(pos, world.registryAccess().registryOrThrow(Registry.STRUCTURE_REGISTRY).get(new ResourceLocation(WeepingAngels.MODID, "catacombs"))), destWorld, destWorld.structureManager(), Blocks.WATER);

            Collections.shuffle(viablePoses);
            if (!viablePoses.isEmpty() && spaceTimeDim != null) {
                console.setDestination(new SpaceTimeCoord(spaceTimeDim, viablePoses.get(0)));
                TardisMod.create(world.getServer(), console.getDestinationPosition(), console.getDestinationDimension());
                console.getInteriorManager().soundAlarm(AlarmType.LOW);
                console.getSubsystem(StabilizerSubsystem.class).ifPresent(sys -> sys.setControlActivated(true));

                console.getControl(HandbrakeControl.class).ifPresent(sys -> {
                    sys.setFree(true);
                    sys.markDirty();
                    sys.startAnimation();
                });


                console.getControl(LandingTypeControl.class).ifPresent(landingTypeControl -> {
                    landingTypeControl.setLandType(LandingTypeControl.EnumLandType.DOWN);
                    landingTypeControl.markDirty();
                    landingTypeControl.startAnimation();
                });


                console.getControl(ThrottleControl.class).ifPresent(sys -> {
                    sys.setAmount(1F);
                    sys.startAnimation();
                    sys.markDirty();
                });
                console.takeoff(false);
            }
        }
        return false;
    }

    @SubscribeEvent
    public void rightClick(PlayerInteractEvent.RightClickBlock event) {
        BlockEntity blockEntity = event.getLevel().getBlockEntity(event.getPos());

        if (blockEntity instanceof Plinth && event.getItemStack().getItem() instanceof SonicItem && CatacombTracker.isInCatacomb(event.getEntity())) {
            Plinth iPlinth = (Plinth) blockEntity;
            event.setCanceled(true);
            ItemStack sonic = event.getItemStack();
            SonicCapability.getForStack(sonic).ifPresent(iSonic -> {
                iSonic.addSchematic(getSchem());
                iSonic.sync(event.getEntity(), event.getHand());
                event.getEntity().displayClientMessage(Component.translatable("message.weeping_angels.2005_schematic"), true);

                blockEntity.getLevel().removeBlock(blockEntity.getBlockPos(), false);
                WAHelper.spawnWeepingAngel((ServerLevel) event.getLevel(), event.getPos(), iPlinth.getVariant(), true, (float) Math.toRadians(22.5F * blockEntity.getBlockState().getValue(StatueBaseBlock.ROTATION)));
            });
        }
    }
}
