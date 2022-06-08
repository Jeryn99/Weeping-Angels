package me.suff.mc.angels.data;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;

public class WABiomeGen extends TagsProvider<Biome> {

    public WABiomeGen(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, BuiltinRegistries.BIOME, WeepingAngels.MODID, helper);
    }

    @Override
    protected void addTags() {


        this.tag(AngelUtil.STRUCTURE_SPAWNS).addTags(BiomeTags.IS_FOREST, BiomeTags.IS_HILL, BiomeTags.IS_MOUNTAIN, BiomeTags.IS_TAIGA);

//TODO
/*        for (Biome biome : ForgeRegistries.BIOMES) {


            Holder<Biome> biomeHolder = Holder.direct(biome);
            Biome.BiomeCategory category = Biome.getBiomeCategory(biomeHolder);


            for (BiomeDictionary.Type biomeType : CommonEvents.BIOME_TYPES) {
                if (BiomeDictionary.hasType(ResourceKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName()), biomeType)) {
                    add(AngelUtil.STRUCTURE_SPAWNS, biome);
                }
            }

          *//*  if(category == Biome.BiomeCategory.NETHER || category == Biome.BiomeCategory.FOREST || category == Biome.BiomeCategory.PLAINS || biome.getPrecipitation() == Biome.Precipitation.SNOW) {
                add(AngelUtil.ANGEL_SPAWNS, biome);
            }*//*
        }*/
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