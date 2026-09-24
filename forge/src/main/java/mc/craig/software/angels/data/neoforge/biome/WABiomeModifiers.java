package mc.craig.software.angels.data.neoforge.biome;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mc.craig.software.angels.WeepingAngels;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class WABiomeModifiers {

    public static final DeferredRegister<MapCodec<? extends BiomeModifier>> SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, WeepingAngels.MODID);

    public static final DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<AddAngelSpawns>> ANGEL_SPAWNS = SERIALIZERS.register("angel_spawns", () -> RecordCodecBuilder.mapCodec(builder -> builder.group(
            Biome.LIST_CODEC.fieldOf("biomes").forGetter(AddAngelSpawns::biomes)
    ).apply(builder, AddAngelSpawns::new)));

    public static final DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<AddSnowAngels>> SNOW_ANGELS = SERIALIZERS.register("snow_angels", () -> RecordCodecBuilder.mapCodec(builder -> builder.group(
            Biome.LIST_CODEC.fieldOf("biomes").forGetter(AddSnowAngels::biomes),
            PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(AddSnowAngels::features),
            GenerationStep.Decoration.CODEC.fieldOf("step").forGetter(AddSnowAngels::step)
    ).apply(builder, AddSnowAngels::new)));

    public static void register(IEventBus modEventBus) {
        SERIALIZERS.register(modEventBus);
    }
}
