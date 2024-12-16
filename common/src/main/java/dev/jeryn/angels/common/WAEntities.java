package dev.jeryn.angels.common;

import com.google.common.base.Supplier;
import dev.jeryn.angels.common.entity.angel.WeepingAngel;
import dev.jeryn.angels.common.entity.projectile.ThrowableGenerator;
import dev.jeryn.angels.registry.DeferredRegistry;
import dev.jeryn.angels.registry.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import static dev.jeryn.angels.WeepingAngels.MODID;

public class WAEntities {

    public static final DeferredRegistry<EntityType<?>> ENTITY_TYPES = DeferredRegistry.create(MODID, Registry.ENTITY_TYPE_REGISTRY);

    public static final RegistrySupplier<EntityType<WeepingAngel>> WEEPING_ANGEL = ENTITY_TYPES.register("weeping_angel", () -> EntityType.Builder.of((EntityType.EntityFactory<WeepingAngel>) (entityType, level) -> new WeepingAngel(level), MobCategory.CREATURE).sized(0.6F, 1.95F).build(MODID + ":weeping_angel"));
    public static final RegistrySupplier<EntityType<ThrowableGenerator>> GENERATOR = ENTITY_TYPES.register("generator", () -> EntityType.Builder.of((EntityType.EntityFactory<ThrowableGenerator>) (entityType, level) -> new ThrowableGenerator(level), MobCategory.MISC).sized(0.25F, 0.25F).updateInterval(10).clientTrackingRange(4).build(MODID + ":generator"));

    public static <T extends Entity> RegistrySupplier<EntityType<T>> register(String id, Supplier<EntityType.Builder<T>> builderSupplier) {
        return ENTITY_TYPES.register(id, () -> builderSupplier.get().build(MODID + ":" + id));
    }

}
