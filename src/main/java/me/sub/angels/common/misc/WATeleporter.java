package me.sub.angels.common.misc;

import me.sub.angels.common.entities.EntityWeepingAngel;
import me.sub.angels.common.events.mods.EventAngelTeleport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;

public class WATeleporter extends Teleporter {

    public WATeleporter(WorldServer worldIn) {
        super(worldIn);
    }


    public static boolean teleportDimEntity(Entity entity, BlockPos pos, int targetDim, EntityWeepingAngel entityWatching) {
        if (entity.getEntityWorld().isRemote || entity.isRiding() || entity.isBeingRidden() || !entity.isEntityAlive()) {
            return false;
        }

        EntityPlayerMP player = null;

        if (entity instanceof EntityPlayerMP) {
            player = (EntityPlayerMP) entity;

            if (entityWatching != null) {
                MinecraftForge.EVENT_BUS.post(new EventAngelTeleport(player, entityWatching, pos, targetDim));
            }
        }

        int from = entity.dimension;
        if (from != targetDim) {
            MinecraftServer server = player == null ? entity.getServer() : player.mcServer;
            WorldServer fromDim = server.getWorld(from);
            WorldServer toDim = server.getWorld(targetDim);
            Teleporter teleporter = new WATeleporter(toDim);

            if (player != null) {
                server.getPlayerList().transferPlayerToDimension(player, targetDim, teleporter);
                if (from == 1 && entity.isEntityAlive()) {
                    toDim.spawnEntity(entity);
                    toDim.updateEntityWithOptionalForce(entity, false);
                }
            } else {
                NBTTagCompound tagCompound = entity.serializeNBT();
                float rotationYaw = entity.rotationYaw;
                float rotationPitch = entity.rotationPitch;
                fromDim.removeEntity(entity);
                Entity newEntity = EntityList.createEntityFromNBT(tagCompound, toDim);

                if (newEntity != null) {
                    newEntity.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), rotationYaw, rotationPitch);
                    newEntity.forceSpawn = true;
                    toDim.spawnEntity(newEntity);
                    newEntity.forceSpawn = false;
                } else {
                    return false;
                }
            }
        }

        if (!entity.world.isBlockLoaded(pos)) {
            entity.world.getChunkFromBlockCoords(pos);
        }

        if (player != null && player.connection != null) {
            player.connection.setPlayerLocation(pos.getX() + 0.5D, pos.getY() + 0.1D, pos.getZ() + 0.5D, player.rotationYaw, player.rotationPitch);
            player.addExperienceLevel(0);
        } else {
            entity.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY() + 0.1D, pos.getZ() + 0.5D);
        }

        entity.fallDistance = 0;

        return true;
    }


    @Override
    public void placeInPortal(Entity entity, float rotationYaw) {
    }

    @Override
    public boolean placeInExistingPortal(Entity entity, float float_for_something) {
        return true;
    }

    @Override
    public boolean makePortal(Entity entity) {
        return true;
    }

    @Override
    public void removeStalePortalLocations(long stale_long) {

    }
}
