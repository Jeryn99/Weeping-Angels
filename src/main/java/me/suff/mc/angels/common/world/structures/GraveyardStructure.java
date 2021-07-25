package me.suff.mc.angels.common.world.structures;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.IglooFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.IglooPieces;

public class GraveyardStructure extends StructureFeature<NoneFeatureConfiguration> {


    public GraveyardStructure(Codec<NoneFeatureConfiguration> p_67039_) {
        super(p_67039_);
    }

    @Override
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return IglooFeature.FeatureStart::new;
    }
}
