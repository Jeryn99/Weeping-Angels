package mc.craig.software.angels.data.forge.biome;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mc.craig.software.angels.WAConfiguration;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WAEntities;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
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
    public void modify(Holder<Biome> biome, Phase phase, Builder builder) {
        if (phase == Phase.ADD && this.biomes.contains(biome)) {
            builder.getMobSpawnSettings().addSpawn(WAConfiguration.SPAWNS.spawnType.get(), new MobSpawnSettings.SpawnerData(WAEntities.WEEPING_ANGEL.get(), WAConfiguration.SPAWNS.spawnWeight.get(), WAConfiguration.SPAWNS.minCount.get(), WAConfiguration.SPAWNS.maxCount.get()));
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return SERIALIZER.get();
    }

}