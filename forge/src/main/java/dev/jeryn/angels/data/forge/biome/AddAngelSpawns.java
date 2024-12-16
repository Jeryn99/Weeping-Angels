package dev.jeryn.angels.data.forge.biome;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.jeryn.angels.WAConfiguration;
import dev.jeryn.angels.WAEntitySpawns;
import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.common.WAEntities;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public record AddAngelSpawns(HolderSet<Biome> biomes) implements BiomeModifier {
    public static final ResourceLocation WEEPING_ANGEL_SPAWNS = new ResourceLocation(WeepingAngels.MODID, "spawns/weeping_angels");
    private static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER = RegistryObject.create(WEEPING_ANGEL_SPAWNS, ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, WeepingAngels.MODID);

    public static Codec<AddAngelSpawns> makeCodec() {
        return RecordCodecBuilder.create(builder -> builder.group(
                Biome.LIST_CODEC.fieldOf("biomes").forGetter(AddAngelSpawns::biomes)).apply(builder, AddAngelSpawns::new));
    }

    @Override
    public void modify(Holder<Biome> biomeHolder, Phase phase, Builder builder) {
        Biome biome = biomeHolder.get();
        if (phase == Phase.ADD && WAEntitySpawns.canSpawnInThisBiome(biome)) {
            builder.getMobSpawnSettings().addSpawn(WAEntitySpawns.getSpawnType(biome), new MobSpawnSettings.SpawnerData(WAEntities.WEEPING_ANGEL.get(), WAEntitySpawns.getSpawnWeight(biome), WAEntitySpawns.getSpawnMin(biome), WAEntitySpawns.getSpawnMax(biome)));
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return SERIALIZER.get();
    }

}