package mc.craig.software.angels.common;

import com.google.common.base.Supplier;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.registry.DeferredRegistry;
import mc.craig.software.angels.registry.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import static mc.craig.software.angels.WeepingAngels.MODID;

public class WAEntities {

    public static final DeferredRegistry<EntityType<?>> ENTITY_TYPES = DeferredRegistry.create(MODID, Registry.ENTITY_TYPE_REGISTRY);

    public static final RegistrySupplier<EntityType<WeepingAngel>> WEEPING_ANGEL = ENTITY_TYPES.register("weeping_angel", () -> EntityType.Builder.of((EntityType.EntityFactory<WeepingAngel>) (entityType, level) -> new WeepingAngel(level, entityType), MobCategory.CREATURE).sized(0.6F, 2.9F).build(MODID + ":weeping_angel"));
    public static <T extends Entity> RegistrySupplier<EntityType<T>> register(String id, Supplier<EntityType.Builder<T>> builderSupplier) {
        return ENTITY_TYPES.register(id, () -> builderSupplier.get().build(MODID + ":" + id));
    }

}
