package me.suff.mc.angels.common.world.structures;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.JigsawFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;

public class CatacombStructure extends JigsawFeature {

    protected static final String[] variants = new String[]{"flat", "clean", "broken", "normal"};

    public CatacombStructure(Codec<JigsawConfiguration> p_66150_, int p_66151_, boolean p_66152_, boolean p_66153_) {
        super(p_66150_, p_66151_, p_66152_, p_66153_);
    }
}