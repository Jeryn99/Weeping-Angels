package mc.craig.software.angels.common.entity;

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
            .setUpdateInterval(3)
            .setCustomClientFactory((ent, world) -> WAEntities.WEEPING_ANGEL.get().create(world))
            .setShouldReceiveVelocityUpdates(true)
            .build(MODID + ":weeping_angel"));

}
