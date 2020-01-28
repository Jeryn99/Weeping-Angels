package me.swirtzly.angels.utils;

import java.util.LinkedList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.minecart.MinecartEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.network.play.server.SPlaySoundEventPacket;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraft.network.play.server.SRespawnPacket;
import net.minecraft.network.play.server.SServerDifficultyPacket;
import net.minecraft.network.play.server.SSetPassengersPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.hooks.BasicEventHooks;

public class TeleporterNew {


    public static Entity teleportEntity(Entity entity, DimensionType dimension, double xCoord, double yCoord, double zCoord, float yaw, float pitch){
        if (entity == null || entity.world.isRemote)
            return entity;

        MinecraftServer server = entity.getServer();
        DimensionType sourceDim = entity.world.getDimension().getType();

        if (!entity.isBeingRidden() || !entity.isPassenger()){
            return handleEntityTeleport(entity, server, sourceDim, dimension, xCoord, yCoord, zCoord, yaw, pitch);
        }

        final Entity rootEntity = entity.getLowestRidingEntity();
        final PassengerHelper passengerHelper = new PassengerHelper(rootEntity);
        final PassengerHelper rider = passengerHelper.getPassenger(entity);
        if (rider == null) {
            return entity;
        }
        passengerHelper.teleport(server, sourceDim, dimension, xCoord, yCoord, zCoord, yaw, pitch);
        passengerHelper.remountRiders();
        passengerHelper.updateClients();
        return rider.entity;
    }

    public static Entity teleportEntity(Entity entity, DimensionType dimension, double xCoord, double yCoord, double zCoord) {
        return teleportEntity(entity, dimension, xCoord, yCoord, zCoord, entity.rotationYaw, entity.rotationPitch);
    }

    private static Entity handleEntityTeleport(Entity entity, MinecraftServer server, DimensionType sourceDim, DimensionType targetDim, double xCoord, double yCoord, double zCoord, float yaw, float pitch) {
        if (entity == null || entity.world.isRemote()) {
            return entity;
        }
        boolean interDimensional = sourceDim != targetDim;
        if (interDimensional && !ForgeHooks.onTravelToDimension(entity, targetDim)) {
            return entity;
        }
        if (!interDimensional) {
            if (entity instanceof ServerPlayerEntity) {
                ServerPlayerEntity player = (ServerPlayerEntity)entity;
                player.connection.setPlayerLocation(xCoord, yCoord, zCoord, yaw, pitch);
                player.setRotationYawHead(yaw);
            }
            else {
                entity.setLocationAndAngles(xCoord, yCoord, zCoord, yaw, pitch);
                entity.setRotationYawHead(yaw);
            }
            return entity;
        }
        if (entity instanceof ServerPlayerEntity) {
            return teleportPlayerInterdimentional((ServerPlayerEntity)entity, server, sourceDim, targetDim, xCoord, yCoord, zCoord, yaw, pitch);
        }
        return teleportEntityInterdimentional(entity, server, sourceDim, targetDim, xCoord, yCoord, zCoord, yaw, pitch);
    }

    private static Entity teleportEntityInterdimentional(Entity entity, MinecraftServer server, DimensionType sourceDim, DimensionType targetDim, double xCoord, double yCoord, double zCoord, float yaw, float pitch) {
        if (!entity.isAlive()) {
            return null;
        }
        final ServerWorld sourceWorld = server.getWorld(sourceDim);
        final ServerWorld targetWorld = server.getWorld(targetDim);
        if (entity.isAlive() && entity instanceof MinecartEntity) {
            entity.removed = true;
            entity.changeDimension(targetDim);
            entity.removed = false;
        }
        entity.dimension = targetDim;
        sourceWorld.removeEntity(entity);
        entity.removed = false;
        entity.setLocationAndAngles(xCoord, yCoord, zCoord, yaw, pitch);
        sourceWorld.updateEntity(entity);
        final Entity newEntity = entity.getType().create((World)targetWorld);
        if (newEntity != null) {
            newEntity.copyDataFromOld(entity);
            newEntity.setLocationAndAngles(xCoord, yCoord, zCoord, yaw, pitch);
            final boolean flag = newEntity.forceSpawn;
            newEntity.forceSpawn = true;
            targetWorld.addEntity(newEntity);
            newEntity.forceSpawn = flag;
            targetWorld.updateEntity(newEntity);
        }
        entity.removed = true;
        sourceWorld.resetUpdateEntityTick();
        targetWorld.resetUpdateEntityTick();
        return newEntity;
    }

    private static PlayerEntity teleportPlayerInterdimentional(ServerPlayerEntity player, MinecraftServer server, DimensionType sourceDim, DimensionType destination, double xCoord, double yCoord, double zCoord, float yaw, float pitch) {
        DimensionType dimensiontype = player.dimension;
        if (dimensiontype == DimensionType.THE_END && destination == DimensionType.OVERWORLD) {
            player.detach();
            player.getServerWorld().removePlayer(player);
            if (!player.queuedEndExit) {
                player.queuedEndExit = true;
            }
            return player;
        }
        ServerWorld serverworld = player.server.getWorld(dimensiontype);
        player.dimension = destination;
        ServerWorld serverworld2 = player.server.getWorld(destination);
        WorldInfo worldinfo = player.world.getWorldInfo();
        player.connection.sendPacket(new SRespawnPacket(destination, worldinfo.getGenerator(), player.interactionManager.getGameType()));
        player.connection.sendPacket(new SServerDifficultyPacket(worldinfo.getDifficulty(), worldinfo.isDifficultyLocked()));
        PlayerList playerlist = player.server.getPlayerList();
        playerlist.updatePermissionLevel(player);
        serverworld.removeEntity(player, true);
        player.revive();
        double d0 = player.posX;
        double d2 = player.posY;
        double d3 = player.posZ;
        float f = player.rotationPitch;
        float f2 = player.rotationYaw;
        double d4 = 8.0;
        float f3 = f2;
        serverworld.getProfiler().startSection("moving");

        serverworld.getProfiler().endSection();
        player.setLocationAndAngles(xCoord, yCoord, zCoord, yaw, pitch);
        player.setMotion(Vec3d.ZERO);
        player.setWorld(serverworld2);
        serverworld2.func_217447_b(player);
       // player.func_213846_b(serverworld); //TODO PATCH ASAP
        player.connection.setPlayerLocation(player.posX, player.posY, player.posZ, f2, f);
        player.interactionManager.setWorld(serverworld2);
        player.connection.sendPacket(new SPlayerAbilitiesPacket(player.abilities));
        playerlist.sendWorldInfo(player, serverworld2);
        playerlist.sendInventory(player);
        for (final EffectInstance effectinstance : player.getActivePotionEffects()) {
            player.connection.sendPacket(new SPlayEntityEffectPacket(player.getEntityId(), effectinstance));
        }
        //player.connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
        BasicEventHooks.firePlayerChangedDimensionEvent(player, dimensiontype, destination);
        return player;
    }


    public static BlockPos yCoordSanity(World world, BlockPos spawn){
        BlockPos newSpawn = spawn;
        while (!(world.isAirBlock(newSpawn) && world.isAirBlock(newSpawn.up())) && newSpawn.getY() < world.dimension.getHeight() - 5) {
            newSpawn = newSpawn.up();
        }
        System.out.println(newSpawn);
        return newSpawn;
    }

    public static Entity getHighestRidingEntity(final Entity mount) {
        Entity entity;
        for (entity = mount; entity.getPassengers().size() > 0; entity = entity.getPassengers().get(0)) {}
        return entity;
    }


    private static class PassengerHelper {
        public Entity entity;
        public LinkedList<PassengerHelper> passengers;
        public double offsetX;
        public double offsetY;
        public double offsetZ;

        public PassengerHelper(final Entity entity) {
            this.passengers = new LinkedList<PassengerHelper>();
            this.entity = entity;
            if (entity.isPassenger()) {
                this.offsetX = entity.posX - entity.getRidingEntity().posX;
                this.offsetY = entity.posY - entity.getRidingEntity().posY;
                this.offsetZ = entity.posZ - entity.getRidingEntity().posZ;
            }
            for (final Entity passenger : entity.getPassengers()) {
                this.passengers.add(new PassengerHelper(passenger));
            }
        }

        public void teleport(final MinecraftServer server, final DimensionType sourceDim, final DimensionType targetDim, final double xCoord, final double yCoord, final double zCoord, final float yaw, final float pitch) {
            this.entity.removePassengers();
            this.entity = handleEntityTeleport(this.entity, server, sourceDim, targetDim, xCoord, yCoord, zCoord, yaw, pitch);
            for (final PassengerHelper passenger : this.passengers) {
                passenger.teleport(server, sourceDim, targetDim, xCoord, yCoord, zCoord, yaw, pitch);
            }
        }

        public void remountRiders() {
            if (this.entity.isPassenger()) {
                this.entity.setLocationAndAngles(this.entity.posX + this.offsetX, this.entity.posY + this.offsetY, this.entity.posZ + this.offsetZ, this.entity.rotationYaw, this.entity.rotationPitch);
            }
            for (final PassengerHelper passenger : this.passengers) {
                passenger.entity.startRiding(this.entity, true);
                passenger.remountRiders();
            }
        }

        public void updateClients() {
            if (this.entity instanceof ServerPlayerEntity) {
                this.updateClient((ServerPlayerEntity)this.entity);
            }
            for (final PassengerHelper passenger : this.passengers) {
                passenger.updateClients();
            }
        }

        private void updateClient(final ServerPlayerEntity playerMP) {
            if (this.entity.isBeingRidden()) {
                playerMP.connection.sendPacket(new SSetPassengersPacket(this.entity));
            }
            for (final PassengerHelper passenger : this.passengers) {
                passenger.updateClients();
            }
        }

        public PassengerHelper getPassenger(final Entity passenger) {
            if (this.entity == passenger) {
                return this;
            }
            for (final PassengerHelper rider : this.passengers) {
                final PassengerHelper re = rider.getPassenger(passenger);
                if (re != null) {
                    return re;
                }
            }
            return null;
        }
    }
}
