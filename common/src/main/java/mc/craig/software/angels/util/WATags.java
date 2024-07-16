package mc.craig.software.angels.util;

import mc.craig.software.angels.WeepingAngels;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;

public class WATags {

    public static TagKey<Block> NO_BREAKING = makeBlock(WeepingAngels.MODID, "no_breaking");
    public static TagKey<Item> STEALABLE_ITEMS = makeItem(WeepingAngels.MODID, "stealable_items");
    public static TagKey<Item> ATTACK_OVERRIDES = makeItem(WeepingAngels.MODID, "attack/weeping_angels");
    public static TagKey<Biome> ANGEL_SPAWNS = makeBiome(WeepingAngels.MODID, "spawns/weeping_angels");
    public static TagKey<EntityType<?>> ANOMALYS = makeEntityType(WeepingAngels.MODID, "anomaly");

    public static TagKey<Biome> CATACOMB_STRUCTURE_BIOMES = makeBiome(WeepingAngels.MODID, "has_structure/catacombs");

    private static TagKey<Item> makeItem(String domain, String path) {
        return TagKey.create(Registries.ITEM, ResourceLocation.tryBuild(domain, path));
    }

    private static TagKey<Block> makeBlock(String domain, String path) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.tryBuild(domain, path));
    }

    private static TagKey<EntityType<?>> makeEntityType(String domain, String path) {
        return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.tryBuild(domain, path));
    }

    private static TagKey<Structure> makeStructure(String domain, String path) {
        return TagKey.create(Registries.STRUCTURE, ResourceLocation.tryBuild(domain, path));
    }

    private static TagKey<Biome> makeBiome(String domain, String path) {
        return TagKey.create(Registries.BIOME, ResourceLocation.tryBuild(domain, path));
    }

}
