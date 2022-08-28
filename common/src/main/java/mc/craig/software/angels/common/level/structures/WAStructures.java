package mc.craig.software.angels.common.level.structures;

import com.mojang.serialization.Codec;
import craig.software.mc.angels.common.level.structures.CatacombStructure;
import mc.craig.software.angels.WeepingAngels;
import net.minecraft.core.Registry;
import mc.craig.software.angels.registry.DeferredRegistry;
import mc.craig.software.angels.registry.RegistrySupplier;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class WAStructures {

    public static final DeferredRegistry<StructureType<?>> STRUCTURES = DeferredRegistry.create(WeepingAngels.MODID, Registry.STRUCTURE_TYPE_REGISTRY);
    public static final RegistrySupplier<StructureType<?>> CATACOMB = STRUCTURES.register("catacombs", () -> typeConvert(CatacombStructure.CODEC));


    private static <S extends Structure> StructureType<S> typeConvert(Codec<S> codec) {
        return () -> codec;
    }


}