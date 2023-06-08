package mc.craig.software.angels.data;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.events.CommonEvents;
import mc.craig.software.angels.utils.AngelUtil;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class WABiomeGen extends TagsProvider<Biome> {

    public WABiomeGen(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, BuiltinRegistries.BIOME, WeepingAngels.MODID, helper);
    }

    @Override
    protected void addTags() {


        for (Biome biome : ForgeRegistries.BIOMES) {
            Holder<Biome> biomeHolder = Holder.direct(biome);
            Biome.BiomeCategory category = Biome.getBiomeCategory(biomeHolder);


            for (BiomeDictionary.Type biomeType : CommonEvents.BIOME_TYPES) {
                if (BiomeDictionary.hasType(ResourceKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName()), biomeType)) {
                    add(AngelUtil.STRUCTURE_SPAWNS, biome);
                }
            }

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