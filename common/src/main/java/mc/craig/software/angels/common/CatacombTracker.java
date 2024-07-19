package mc.craig.software.angels.common;

import mc.craig.software.angels.common.level.structures.WAStructures;
import mc.craig.software.angels.network.WANetworkManager;
import mc.craig.software.angels.network.messages.CatacombUpdate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.Vec3;

import static mc.craig.software.angels.util.WAHelper.getConfigured;
import static mc.craig.software.angels.util.WAHelper.intersects;

/**
 * The `CatacombTracker` class is used to track whether a player is inside a catacomb in the game world.
 * It contains static methods to access and update the state of the `isInCatacomb` field, which tracks the player's current location.
 */
public class CatacombTracker {

    private static boolean isInCatacomb = false;


    /**
     * Updates the value of the `isInCatacomb` field.
     * @param isInCatacomb the new value of the `isInCatacomb` field
     */
    public static void setIsInCatacomb(boolean isInCatacomb) {
        CatacombTracker.isInCatacomb = isInCatacomb;
    }


    /**
     * Sends a `UpdateCatacombMessage` to the given `ServerPlayer`, with a boolean value indicating
     * whether the player is inside a catacomb or not.
     * @param serverPlayer the `ServerPlayer` to send the message to
     * @param isInCatacomb the boolean value to include in the message
     */
    public static void tellClient(ServerPlayer serverPlayer, boolean isInCatacomb){
        WANetworkManager.get().sendToPlayer(serverPlayer, new CatacombUpdate(isInCatacomb));
    }

    /**
     * @return the current value of the `isInCatacomb` field
     */
    public static boolean isInCatacomb() {
        return isInCatacomb;
    }

    /**
     * Takes a `LivingEntity` object representing the player, and returns `true` if the player is inside a catacomb, `false` otherwise.
     * It does this by checking the player's current `ServerLevel` for the presence of a `WAStructures.CATACOMB` structure at the player's position,
     * and checking whether the player's bounding box intersects with the bounding box of the structure.
     * @param playerEntity the `LivingEntity` representing the player
     * @return `true` if the player is inside a catacomb, `false` otherwise
     */
    public static boolean isInCatacomb(LivingEntity playerEntity) {
        if (playerEntity.level() instanceof ServerLevel serverWorld) {
            boolean isCatacomb = serverWorld.structureManager().getStructureAt(playerEntity.blockPosition(), getConfigured(serverWorld, WAStructures.CATACOMB.getId())).isValid();

            if (isCatacomb) {
                BoundingBox box = serverWorld.structureManager().getStructureAt(playerEntity.blockPosition(), getConfigured(serverWorld, WAStructures.CATACOMB.getId())).getBoundingBox();
                return intersects(playerEntity.getBoundingBox(), new Vec3(box.minX(), box.minY(), box.minZ()), new Vec3(box.maxX(), box.maxY(), box.maxZ()));
            }
        }
        return false;
    }

}
