package craig.software.mc.angels.common.level.biomemodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import craig.software.mc.angels.WeepingAngels;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public record BiomeFeatureModifier(HolderSet<Biome> biomes, GenerationStep.Decoration generationStage,
                                   HolderSet<PlacedFeature> features) implements BiomeModifier {

    public static final ResourceLocation ADD_FEATURE = new ResourceLocation(WeepingAngels.MODID, "feature_add");
    public static final String ORE_NAME = "kontron_ore";

    private static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER = RegistryObject.create(ADD_FEATURE, ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, WeepingAngels.MODID);

    public static Codec<BiomeFeatureModifier> makeCodec() {
        return RecordCodecBuilder.create(builder -> builder.group(
                Biome.LIST_CODEC.fieldOf("biomes").forGetter(BiomeFeatureModifier::biomes),
                Codec.STRING.comapFlatMap(BiomeFeatureModifier::generationStageFromString, GenerationStep.Decoration::toString).fieldOf("generation_stage").forGetter(BiomeFeatureModifier::generationStage),
                PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(BiomeFeatureModifier::features)
        ).apply(builder, BiomeFeatureModifier::new));
    }

    private static DataResult<GenerationStep.Decoration> generationStageFromString(String name) {
        try {
            return DataResult.success(GenerationStep.Decoration.valueOf(name));
        } catch (Exception e) {
            return DataResult.error("Not a decoration stage: " + name);
        }
    }

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD && this.biomes.contains(biome)) {
            BiomeGenerationSettingsBuilder generation = builder.getGenerationSettings();
            this.features.forEach(holder -> generation.addFeature(this.generationStage, holder));
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return SERIALIZER.get();
    }
}