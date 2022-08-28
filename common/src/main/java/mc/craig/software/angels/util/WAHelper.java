package mc.craig.software.angels.util;

import mc.craig.software.angels.common.WAEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

public class WAHelper {

    public static Predicate<? super Entity> ANOMALY_ENTITIES = input -> input.getType().is(WATags.ANOMALYS);

    public static List<Entity> getAnomaliesAroundEntity(Entity entity, int radius) {
        return entity.level.getEntities((Entity) null, entity.getBoundingBox().inflate(radius, radius, radius), ANOMALY_ENTITIES);
    }

    public static boolean spawnBlackHole(ServerLevel serverLevel, BlockPos blockPos) {
        Entity anomalyEntity = WAEntities.ANOMALY.get().create(serverLevel);
        anomalyEntity.moveTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return serverLevel.addFreshEntity(anomalyEntity);
    }

    public static Vec3 fogColor() {
        return new Vec3(0.14F, 0.15F, 0.22F);
    }


}
