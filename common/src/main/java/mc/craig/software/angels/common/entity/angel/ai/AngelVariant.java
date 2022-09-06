package mc.craig.software.angels.common.entity.angel.ai;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;

import java.util.Collection;
import java.util.Map;

public class AngelVariant {

    // Ore Variants
    public static final Map<ResourceLocation, AngelVariant> ORE_VARIANTS = Util.make(new Object2ObjectOpenHashMap<>(), (objectOpenHashMap) -> objectOpenHashMap.defaultReturnValue(AngelVariant.IRON));
    public static AngelVariant STONE, BASALT, DIRT, COPPER, MOSSY, RUSTED, RUSTED_NO_ARM, RUSTED_NO_WING, RUSTED_NO_HEAD, QUARTZ, LAPIS_LAZULI, IRON, GOLD, EMERALD, DIAMOND;
    // Main Variant Registry
    public static final Map<ResourceLocation, AngelVariant> VARIANTS = Util.make(new Object2ObjectOpenHashMap<>(), (objectOpenHashMap) -> objectOpenHashMap.defaultReturnValue(AngelVariant.STONE));
    public static AngelVariant GAS_STONE, GAS_RUSTED, A_DIZZLE, DOCTOR;

    public static void init() {
        STONE = registerVariant(new ResourceLocation(WeepingAngels.MODID, "normal"), new ItemStack(Blocks.STONE), false);
        DOCTOR = registerVariant(new ResourceLocation(WeepingAngels.MODID, "doctor"), new ItemStack(Blocks.STONE), false);
        BASALT = registerVariant(new ResourceLocation(WeepingAngels.MODID, "basalt"), new ItemStack(Blocks.BASALT), false);
        COPPER = registerVariant(new ResourceLocation(WeepingAngels.MODID, "copper"), new ItemStack(Blocks.COPPER_ORE), true);
        DIRT = registerVariant(new ResourceLocation(WeepingAngels.MODID, "dirt"), new ItemStack(Blocks.DIRT), false);
        MOSSY = registerVariant(new ResourceLocation(WeepingAngels.MODID, "mossy"), new ItemStack(Blocks.MOSSY_COBBLESTONE), false);
        RUSTED = registerVariant(new ResourceLocation(WeepingAngels.MODID, "rusted"), new ItemStack(Blocks.MOSSY_COBBLESTONE), false);
        RUSTED_NO_ARM = registerVariant(new ResourceLocation(WeepingAngels.MODID, "rusted_no_arm"), new ItemStack(Blocks.GRANITE), false);
        RUSTED_NO_WING = registerVariant(new ResourceLocation(WeepingAngels.MODID, "rusted_no_wing"), new ItemStack(Blocks.GRANITE), false);
        RUSTED_NO_HEAD = registerVariant(new ResourceLocation(WeepingAngels.MODID, "rusted_no_head"), new ItemStack(Blocks.GRANITE), false);
        QUARTZ = registerVariant(new ResourceLocation(WeepingAngels.MODID, "quartz"), new ItemStack(Blocks.QUARTZ_PILLAR), false);
        LAPIS_LAZULI = registerVariant(new ResourceLocation(WeepingAngels.MODID, "lapis_lazuli"), new ItemStack(Blocks.LAPIS_ORE), true);
        IRON = registerVariant(new ResourceLocation(WeepingAngels.MODID, "iron"), new ItemStack(Blocks.IRON_ORE), true);
        GOLD = registerVariant(new ResourceLocation(WeepingAngels.MODID, "gold"), new ItemStack(Blocks.GOLD_ORE), true);
        EMERALD = registerVariant(new ResourceLocation(WeepingAngels.MODID, "emerald"), new ItemStack(Blocks.EMERALD_ORE), true);
        DIAMOND = registerVariant(new ResourceLocation(WeepingAngels.MODID, "diamond"), new ItemStack(Blocks.DIAMOND_ORE), true);

        GAS_RUSTED = registerVariant(new ResourceLocation(WeepingAngels.MODID, "gas_rusted"), new ItemStack(Blocks.STONE), false);
        GAS_STONE = registerVariant(new ResourceLocation(WeepingAngels.MODID, "gas_stone"), new ItemStack(Blocks.GRANITE), false);
        A_DIZZLE = registerVariant(new ResourceLocation(WeepingAngels.MODID, "a_dizzle"), new ItemStack(Blocks.GRANITE), false);
    }

    private final ItemStack drops;
    private final ResourceLocation regName;

    public AngelVariant(ResourceLocation resourceLocation, ItemStack drops) {
        this.drops = drops;
        this.regName = resourceLocation;
    }

    public ResourceLocation location() {
        return regName;
    }

    public ItemStack getDrops() {
        return drops;
    }


    // TODO Nicer way
    public static AngelVariant getVariantForPos(WeepingAngel weepingAngel) {
        RandomSource randomSource = weepingAngel.level.random;

        boolean isOrePosition = weepingAngel.blockPosition().getY() < 50 && !weepingAngel.level.canSeeSky(weepingAngel.blockPosition());

        Holder<Biome> currentBiome = weepingAngel.level.getBiome(weepingAngel.blockPosition());
        boolean isNether = currentBiome.is(BiomeTags.IS_NETHER);
        boolean isJungle = currentBiome.is(BiomeTags.IS_JUNGLE);

        if (isJungle) {
            return MOSSY;
        }

        // Nether Related
        if (isNether) {
            return randomSource.nextBoolean() ? QUARTZ : BASALT;
        }

        // Ores
        if (isOrePosition && randomSource.nextInt(100) < 10) {
            return getRandomVariant(ORE_VARIANTS, randomSource);
        }

        // Random value after conditions
        Collection<AngelVariant> variants = VARIANTS.values();
        variants.removeIf(angelTextureVariant -> angelTextureVariant == QUARTZ || angelTextureVariant == MOSSY || angelTextureVariant == BASALT || ORE_VARIANTS.containsKey(angelTextureVariant.regName));
        return variants.stream().skip((int) (variants.size() * Math.random())).findFirst().get();
    }

    public static AngelVariant getRandomVariant(Map<ResourceLocation, AngelVariant> variantMap, RandomSource randomSource) {
        int index = randomSource.nextInt(variantMap.size());
        return variantMap.values().toArray(new AngelVariant[0])[index];
    }

    public static AngelVariant getVariant(ResourceLocation resourceLocation) {
        if (VARIANTS.containsKey(resourceLocation)) {
            return VARIANTS.get(resourceLocation);
        }
        return STONE;
    }

    public static AngelVariant registerVariant(ResourceLocation resourceLocation, ItemStack itemStack, boolean isOre) {
        WeepingAngels.LOGGER.info("Registered: {}", resourceLocation);
        return registerVariant(resourceLocation, new AngelVariant(resourceLocation, itemStack), isOre);
    }

    public static AngelVariant registerVariant(ResourceLocation resourceLocation, AngelVariant angelVariant, boolean isOre) {

        if (isOre) {
            ORE_VARIANTS.put(resourceLocation, angelVariant);
        }

        if (VARIANTS.containsKey(resourceLocation)) {
            VARIANTS.replace(resourceLocation, angelVariant);
        }
        VARIANTS.put(resourceLocation, angelVariant);
        return angelVariant;
    }


}
