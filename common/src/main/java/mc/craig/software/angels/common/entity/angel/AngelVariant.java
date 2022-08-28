package mc.craig.software.angels.common.entity.angel;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mc.craig.software.angels.WeepingAngels;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

public class AngelVariant {

    public static AngelVariant STONE, BASALT, DIRT, COPPER, MOSSY, RUSTED, RUSTED_NO_ARM, RUSTED_NO_WING, RUSTED_NO_HEAD, QUARTZ, LAPIS_LAZULI, IRON, GOLD, EMERALD, DIAMOND;

    public static void init() {
        STONE = registerVariant(new ResourceLocation(WeepingAngels.MODID, "normal"), new ItemStack(Blocks.STONE));
        BASALT = registerVariant(new ResourceLocation(WeepingAngels.MODID, "basalt"), new ItemStack(Blocks.BASALT));
        COPPER = registerVariant(new ResourceLocation(WeepingAngels.MODID, "copper"), new ItemStack(Blocks.COPPER_ORE));
        DIRT = registerVariant(new ResourceLocation(WeepingAngels.MODID, "dirt"), new ItemStack(Blocks.DIRT));
        MOSSY = registerVariant(new ResourceLocation(WeepingAngels.MODID, "mossy"), new ItemStack(Blocks.MOSSY_COBBLESTONE));
        RUSTED = registerVariant(new ResourceLocation(WeepingAngels.MODID, "rusted"), new ItemStack(Blocks.MOSSY_COBBLESTONE));
        RUSTED_NO_ARM = registerVariant(new ResourceLocation(WeepingAngels.MODID, "rusted_no_arm"), new ItemStack(Blocks.MOSSY_COBBLESTONE));
        RUSTED_NO_WING = registerVariant(new ResourceLocation(WeepingAngels.MODID, "rusted_no_wing"), new ItemStack(Blocks.MOSSY_COBBLESTONE));
        RUSTED_NO_HEAD = registerVariant(new ResourceLocation(WeepingAngels.MODID, "rusted_no_head"), new ItemStack(Blocks.MOSSY_COBBLESTONE));
        QUARTZ = registerVariant(new ResourceLocation(WeepingAngels.MODID, "quartz"), new ItemStack(Blocks.QUARTZ_PILLAR));
        LAPIS_LAZULI = registerVariant(new ResourceLocation(WeepingAngels.MODID, "lapis_lazuli"), new ItemStack(Blocks.LAPIS_ORE));
        IRON = registerVariant(new ResourceLocation(WeepingAngels.MODID, "iron"), new ItemStack(Blocks.IRON_ORE));
        GOLD = registerVariant(new ResourceLocation(WeepingAngels.MODID, "gold"), new ItemStack(Blocks.GOLD_ORE));
        EMERALD = registerVariant(new ResourceLocation(WeepingAngels.MODID, "emerald"), new ItemStack(Blocks.EMERALD_ORE));
        DIAMOND = registerVariant(new ResourceLocation(WeepingAngels.MODID, "diamond"), new ItemStack(Blocks.DIAMOND_ORE));
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

    public static final Map<ResourceLocation, AngelVariant> VARIANTS = Util.make(new Object2ObjectOpenHashMap<>(), (objectOpenHashMap) -> {
        objectOpenHashMap.defaultReturnValue(AngelVariant.STONE);
    });

    public static AngelVariant getVariantForPos(WeepingAngel weepingAngel) {
        int i = weepingAngel.level.random.nextInt(VARIANTS.size());
        return VARIANTS.values().toArray(new AngelVariant[0])[i];
    }

    public static AngelVariant getVariant(ResourceLocation resourceLocation){
        if(VARIANTS.containsKey(resourceLocation)) {
            return VARIANTS.get(resourceLocation);
        }
        return STONE;
    }

    public static AngelVariant registerVariant(ResourceLocation resourceLocation, ItemStack itemStack) {
        return registerVariant(resourceLocation, new AngelVariant(resourceLocation, itemStack));
    }

    public static AngelVariant registerVariant(ResourceLocation resourceLocation, AngelVariant angelVariant) {
        if (VARIANTS.containsKey(resourceLocation)) {
            VARIANTS.replace(resourceLocation, angelVariant);
        }
        VARIANTS.put(resourceLocation, angelVariant);
        return angelVariant;
    }

}
