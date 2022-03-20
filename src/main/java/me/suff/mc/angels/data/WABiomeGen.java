package me.suff.mc.angels.data;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class WABiomeGen extends TagsProvider<Biome> {

    public WABiomeGen(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, BuiltinRegistries.BIOME, WeepingAngels.MODID, helper);
    }

    @Override
    protected void addTags() {
        for (Biome s : ForgeRegistries.BIOMES) {
            add(AngelUtil.STRUCTURE_SPAWNS, s);
        }
    }


    public void add(TagKey<Biome> branch, Biome biome) {
        this.tag(branch).add(biome);
    }

    public void add(TagKey<Biome> branch, Biome... biome) {
        this.tag(branch).add(biome);
    }

    public String getName() {
        return "Angel Structure - Biome Tags";
    }
}