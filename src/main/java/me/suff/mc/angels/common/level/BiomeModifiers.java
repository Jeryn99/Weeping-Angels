package me.suff.mc.angels.common.level;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static me.suff.mc.angels.WeepingAngels.MODID;

public class BiomeModifiers {

    // Add Weeping Angels to Biomes
    public record WeepingAngelSpawnsModifier(HolderSet<Biome> biomes, SpawnerData spawn) implements BiomeModifier {
        public static final ResourceLocation WEEPING_ANGEL_SPAWNS = new ResourceLocation(MODID, "spawns/weeping_angels");
        private static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER = RegistryObject.create(WEEPING_ANGEL_SPAWNS, ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MODID);
        public static final String MODIFY_SPAWNS = "spawns/weeping_angels";
        public static Codec<WeepingAngelSpawnsModifier> makeCodec() {
            return RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(WeepingAngelSpawnsModifier::biomes),
                    SpawnerData.CODEC.fieldOf("spawn").forGetter(WeepingAngelSpawnsModifier::spawn)
            ).apply(builder, WeepingAngelSpawnsModifier::new));
        }

        @Override
        public void modify(Holder<Biome> biome, Phase phase, Builder builder) {
            if (phase == Phase.ADD && this.biomes.contains(biome)) {
                builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), this.spawn);
            }
        }

        @Override
        public Codec<? extends BiomeModifier> codec() {
            return SERIALIZER.get();
        }

    }
}