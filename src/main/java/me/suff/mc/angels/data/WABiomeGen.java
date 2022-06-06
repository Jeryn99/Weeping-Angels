package me.suff.mc.angels.data;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WABiomeGen extends TagsProvider<Biome> {

    public WABiomeGen(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, BuiltinRegistries.BIOME, WeepingAngels.MODID, helper);
    }

    @Override
    protected void addTags() {
        for (Biome biome : ForgeRegistries.BIOMES) {
            add(AngelUtil.STRUCTURE_SPAWNS, biome);

            Holder<Biome> biomeHolder = Holder.direct(biome);
            Biome.BiomeCategory category = Biome.getBiomeCategory(biomeHolder);
            
            if(category == Biome.BiomeCategory.NETHER || category == Biome.BiomeCategory.FOREST || category == Biome.BiomeCategory.PLAINS || biome.getPrecipitation() == Biome.Precipitation.SNOW) {
                add(AngelUtil.ANGEL_SPAWNS, biome);
            }
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