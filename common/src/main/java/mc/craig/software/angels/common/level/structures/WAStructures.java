package mc.craig.software.angels.common.level.structures;

import com.mojang.serialization.Codec;
import com.sun.jna.Structure;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.registry.DeferredRegistry;
import mc.craig.software.angels.registry.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.StructureFeature;


public class WAStructures {

    public static final DeferredRegistry<StructureFeature<?>> STRUCTURES = DeferredRegistry.create(WeepingAngels.MODID, Registry.STRUCTURE_FEATURE_REGISTRY);
    public static final RegistrySupplier<StructureFeature<?>> CATACOMB = STRUCTURES.register("catacombs", CatacombStructure::new);



}