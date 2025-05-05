package mc.jeryn.dev.statues.common.level.structures;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import mc.jeryn.dev.statues.WeepingAngels;
import mc.jeryn.dev.statues.registry.DeferredRegister;
import mc.jeryn.dev.statues.registry.RegistryHolder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class WAStructures {

    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(WeepingAngels.MODID, Registries.STRUCTURE_TYPE);
    public static final RegistryHolder<StructureType<?>, StructureType<?>> CATACOMB = STRUCTURES.register("catacombs", () -> typeConvert(CatacombStructure.CODEC));


    private static <S extends Structure> StructureType<S> typeConvert(Codec<S> codec) {
        return () -> (MapCodec<S>) codec;
    }


}