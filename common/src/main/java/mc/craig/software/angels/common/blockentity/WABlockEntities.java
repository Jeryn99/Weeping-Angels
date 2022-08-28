package mc.craig.software.angels.common.blockentity;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.blocks.WABlocks;
import mc.craig.software.angels.registry.DeferredRegistry;
import mc.craig.software.angels.registry.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class WABlockEntities {

    public static final DeferredRegistry<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegistry.create(WeepingAngels.MODID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);

    public static final RegistrySupplier<BlockEntityType<CoffinBlockEntity>> COFFIN = BLOCK_ENTITY_TYPES.register("coffin", () -> BlockEntityType.Builder.of(CoffinBlockEntity::new, WABlocks.COFFIN.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<StatueBlockEntity>> STATUE = BLOCK_ENTITY_TYPES.register("statue", () -> BlockEntityType.Builder.of(StatueBlockEntity::new, WABlocks.STATUE.get()).build(null));


}
