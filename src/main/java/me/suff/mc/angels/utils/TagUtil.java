package me.suff.mc.angels.utils;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.stream.Stream;

//I wrote this for convenience, it sucks and needs to be improved
public class TagUtil {

    public static Iterable getValues(Registry registry, TagKey tagKey) {
        return registry.getTagOrEmpty(tagKey);
    }


    public static Stream getAllTagsForObject(Registry registry, ResourceKey resourceKey) {
        return registry.getHolderOrThrow(resourceKey).tags();
    }

    public static boolean hasTag(IForgeRegistry registry, TagKey<?> objectKey, ForgeRegistryEntry<?> tagkey) {
        return registry.tags().getTag(objectKey).contains(tagkey);
    }

}
