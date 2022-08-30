package mc.craig.software.angels.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.WASounds;
import mc.craig.software.angels.common.entity.angel.AngelTextureVariant;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
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

    @ExpectPlatform
    public static Packet<?> spawnPacket(LivingEntity livingEntity) {
        throw new RuntimeException("This isn't where you get the packet! tut tut!");
    }

    public static boolean spawnBlackHole(ServerLevel serverLevel, BlockPos blockPos) {
        Entity anomalyEntity = WAEntities.ANOMALY.get().create(serverLevel);
        anomalyEntity.moveTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        return serverLevel.addFreshEntity(anomalyEntity);
    }

    public static boolean spawnWeepingAngel(ServerLevel serverLevel, BlockPos blockPos, AngelTextureVariant angelTextureVariant, boolean dropsLoot, float rotation) {
        WeepingAngel weepingAngel = WAEntities.WEEPING_ANGEL.get().create(serverLevel);
        weepingAngel.setVariant(angelTextureVariant);
        weepingAngel.setDrops(dropsLoot);
        weepingAngel.absMoveTo(blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5, rotation, 0);
        return serverLevel.addFreshEntity(weepingAngel);
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

    public static SoundEvent getRandomSounds(RandomSource randomSource) {
        SoundEvent[] soundEvents = new SoundEvent[]{SoundEvents.AMBIENT_CAVE, SoundEvents.MUSIC_DISC_11, SoundEvents.SCULK_SHRIEKER_SHRIEK, WASounds.ANGEL_MOCKING.get()};
        return soundEvents[randomSource.nextInt(soundEvents.length)];
    }
}
