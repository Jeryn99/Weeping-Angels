package mc.craig.software.angels.common;

import dev.architectury.injectables.annotations.ExpectPlatform;
import mc.craig.software.angels.common.level.structures.WAStructures;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.Vec3;

import static mc.craig.software.angels.util.WAHelper.getConfigured;
import static mc.craig.software.angels.util.WAHelper.intersects;

public class CatacombTracker {

    private static boolean isInCatacomb = false;

    public static void setIsInCatacomb(boolean isInCatacomb) {
        CatacombTracker.isInCatacomb = isInCatacomb;
    }


    @ExpectPlatform
    public static void tellClient(ServerPlayer serverPlayer, boolean isInCatacomb){
        throw new RuntimeException("Oopsie!!!!!!");
    }

    public static boolean isInCatacomb() {
        return isInCatacomb;
    }

    public static boolean isInCatacomb(LivingEntity playerEntity) {
        if (playerEntity.level instanceof ServerLevel serverWorld) {
            boolean isCatacomb = serverWorld.structureManager().getStructureAt(playerEntity.blockPosition(), getConfigured(serverWorld, WAStructures.CATACOMB.getId())).isValid();

            if (isCatacomb) {
                BoundingBox box = serverWorld.structureManager().getStructureAt(playerEntity.blockPosition(), getConfigured(serverWorld, WAStructures.CATACOMB.getId())).getBoundingBox();
                return intersects(playerEntity.getBoundingBox(), new Vec3(box.minX(), box.minY(), box.minZ()), new Vec3(box.maxX(), box.maxY(), box.maxZ()));
            }
        }
        return false;
    }

}
