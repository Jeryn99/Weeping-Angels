package mc.jeryn.dev.statues.common;

import com.google.common.base.Supplier;
import mc.jeryn.dev.statues.common.entity.angel.WeepingAngel;
import mc.jeryn.dev.statues.common.entity.projectile.ThrowableGenerator;
import mc.jeryn.dev.statues.registry.DeferredRegister;
import mc.jeryn.dev.statues.registry.RegistryHolder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import static mc.jeryn.dev.statues.WeepingAngels.MODID;

public class WAEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(MODID, Registries.ENTITY_TYPE);

    public static final RegistryHolder<EntityType<?>, EntityType<WeepingAngel>> WEEPING_ANGEL = ENTITY_TYPES.register("weeping_angel", () -> EntityType.Builder.of((EntityType.EntityFactory<WeepingAngel>) (entityType, level) -> new WeepingAngel(level), MobCategory.CREATURE).sized(0.6F, 1.95F).build(MODID + ":weeping_angel"));
    public static final RegistryHolder<EntityType<?>, EntityType<ThrowableGenerator>> GENERATOR = ENTITY_TYPES.register("generator", () -> EntityType.Builder.of((EntityType.EntityFactory<ThrowableGenerator>) (entityType, level) -> new ThrowableGenerator(level), MobCategory.MISC).sized(0.25F, 0.25F).updateInterval(10).clientTrackingRange(4).build(MODID + ":generator"));

    public static <T extends Entity> RegistryHolder<EntityType<?>, EntityType<T>> register(String id, Supplier<EntityType.Builder<T>> builderSupplier) {
        return ENTITY_TYPES.register(id, () -> builderSupplier.get().build(MODID + ":" + id));
    }

}
