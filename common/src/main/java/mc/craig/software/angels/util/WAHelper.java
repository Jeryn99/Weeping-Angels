package mc.craig.software.angels.util;

import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.level.features.WAFeatures;
import mc.craig.software.angels.common.level.structures.WAStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.phys.AABB;
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


    public static boolean intersects(AABB bb, Vec3 min, Vec3 max) {
        return bb.intersects(Math.min(min.x, max.x), Math.min(min.y, max.y), Math.min(min.z, max.z), Math.max(min.x, max.x), Math.max(min.y, max.y), Math.max(min.z, max.z));
    }

    public static Structure getConfigured(ServerLevel level, ResourceLocation resourceLocation) {
        Registry<Structure> registry = level.registryAccess().registryOrThrow(Registry.STRUCTURE_REGISTRY);
        return registry.get(resourceLocation);
    }

}
