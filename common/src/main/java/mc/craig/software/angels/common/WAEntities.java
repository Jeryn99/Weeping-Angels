package mc.craig.software.angels.common;

import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.common.entity.anomaly.AnomalyEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static mc.craig.software.angels.WeepingAngels.MODID;

public class WAEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final RegistryObject<EntityType<WeepingAngel>> WEEPING_ANGEL = ENTITY_TYPES.register("weeping_angel", () -> EntityType.Builder.of((EntityType.EntityFactory<WeepingAngel>) (entityType, level) -> new WeepingAngel(level, entityType), MobCategory.CREATURE)
            .setTrackingRange(80)
            .setUpdateInterval(3).sized(0.6F, 2.9F)
            .setCustomClientFactory((ent, world) -> WAEntities.WEEPING_ANGEL.get().create(world))
            .setShouldReceiveVelocityUpdates(true)
            .build(MODID + ":weeping_angel"));

    public static final RegistryObject<EntityType<AnomalyEntity>> ANOMALY = ENTITY_TYPES.register("anomaly", () -> EntityType.Builder.of(AnomalyEntity::new, MobCategory.CREATURE)
            .setTrackingRange(80)
            .setUpdateInterval(3).sized(5F, 5F)
            .setCustomClientFactory((ent, world) -> WAEntities.ANOMALY.get().create(world))
            .setShouldReceiveVelocityUpdates(true)
            .build(MODID + ":anomaly"));

}
