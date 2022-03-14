package me.suff.mc.angels.data;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagManager;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class WAStructureTagGen extends TagsProvider<ConfiguredStructureFeature<?, ?>> {

    public WAStructureTagGen(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, WeepingAngels.MODID, helper, TagManager.getTagDir(BuiltinRegistries.BIOME.key()).substring(5));
    }

    @Override
    protected void addTags() {
        for (ConfiguredStructureFeature<?, ?> structureFeature : BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE) {
            this.tag(AngelUtil.TELEPORT_STRUCTURES).add(structureFeature);
        }
    }



    public String getName() {
        return "Angel Structure - Structure Teleport Tags";
    }
}