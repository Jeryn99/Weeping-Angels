package craig.software.mc.angels.common.variants;

import craig.software.mc.angels.WeepingAngels;
import craig.software.mc.angels.common.entities.WeepingAngel;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class AngelTypes {

    public static final DeferredRegister<AngelVariant> VARIANTS = DeferredRegister.create(new ResourceLocation(WeepingAngels.MODID, "angel_types"), WeepingAngels.MODID);
    public static Supplier<IForgeRegistry<AngelVariant>> VARIANTS_REGISTRY = VARIANTS.makeRegistry(() -> new RegistryBuilder<AngelVariant>().setMaxID(Integer.MAX_VALUE - 1));

    public static final RegistryObject<AngelVariant> GOLD = VARIANTS.register("gold", () -> new OreVariant(() -> new ItemStack(Blocks.GOLD_ORE), 25));
    public static final RegistryObject<AngelVariant> DIAMOND = VARIANTS.register("diamond", () -> new OreVariant(() -> new ItemStack(Blocks.DIAMOND_ORE), 5));
    public static final RegistryObject<AngelVariant> IRON = VARIANTS.register("iron", () -> new OreVariant(() -> new ItemStack(Blocks.IRON_ORE), 50));
    public static final RegistryObject<AngelVariant> MOSSY = VARIANTS.register("mossy", () -> new BaseVariant(() -> new ItemStack(Blocks.MOSSY_COBBLESTONE), 45));
    public static final RegistryObject<AngelVariant> NORMAL = VARIANTS.register("normal", () -> new BaseVariant(() -> new ItemStack(Blocks.COBBLESTONE), 45));
    public static final RegistryObject<AngelVariant> BASALT = VARIANTS.register("basalt", () -> new OreVariant(() -> new ItemStack(Blocks.BASALT), 30));
    public static final RegistryObject<AngelVariant> RUSTED = VARIANTS.register("rusted", () -> new BaseVariant(() -> new ItemStack(Blocks.GRANITE), 45));
    public static final RegistryObject<AngelVariant> RUSTED_NO_ARM = VARIANTS.register("rusted_no_arm", () -> new BaseVariant(() -> new ItemStack(Blocks.GRANITE), 45));
    public static final RegistryObject<AngelVariant> RUSTED_NO_WING = VARIANTS.register("rusted_no_wing", () -> new BaseVariant(() -> new ItemStack(Blocks.GRANITE), 45));
    public static final RegistryObject<AngelVariant> RUSTED_NO_HEAD = VARIANTS.register("rusted_no_head", () -> new BaseVariant(() -> new ItemStack(Blocks.GRANITE), 45).setHeadless(true));
    public static final RegistryObject<AngelVariant> DIRT = VARIANTS.register("dirt", () -> new BaseVariant(() -> new ItemStack(Blocks.DIRT), 10));
    public static final RegistryObject<AngelVariant> EMERALD = VARIANTS.register("emerald", () -> new OreVariant(() -> new ItemStack(Blocks.EMERALD_ORE), 20));
    public static final RegistryObject<AngelVariant> COPPER = VARIANTS.register("copper", () -> new OreVariant(() -> new ItemStack(Blocks.COPPER_ORE), 20));
    public static final RegistryObject<AngelVariant> LAPIS = VARIANTS.register("lapis_lazuli", () -> new OreVariant(() -> new ItemStack(Blocks.LAPIS_ORE), 10));
    public static final RegistryObject<AngelVariant> QUARTZ = VARIANTS.register("quartz", () -> new OreVariant(() -> new ItemStack(Blocks.NETHER_QUARTZ_ORE), 30));


    // WeightHandler
    public static WeightedHandler NETHER_WEIGHTED = new WeightedHandler();
    public static WeightedHandler ORE_WEIGHTED = new WeightedHandler();
    public static WeightedHandler NORMAL_VARIANTS = new WeightedHandler();

    public static void updateWeighted() {

        // Nether Variants
        NETHER_WEIGHTED.addEntry(AngelTypes.BASALT.get(), AngelTypes.QUARTZ.get());

        // Ore Variants
        for (RegistryObject<AngelVariant> object : VARIANTS.getEntries()) {
            if(object.get() instanceof OreVariant oreVariant){
                ORE_WEIGHTED.addEntry(oreVariant);
            }
        }

        // Standard Variants
        for (RegistryObject<AngelVariant> object : VARIANTS.getEntries()) {
            if(object.get() instanceof BaseVariant baseVariant){
                if(baseVariant != BASALT.get()) {
                    NORMAL_VARIANTS.addEntry(baseVariant);
                }
            }
        }
    }


    public static @NotNull AngelVariant getGoodVariant(WeepingAngel weepingAngel, ServerLevelAccessor serverWorld, DifficultyInstance difficultyInstance, MobSpawnType spawnReason, SpawnGroupData livingEntityData, CompoundTag compoundNBT) {

        Holder<Biome> biome = serverWorld.getLevel().getBiome(weepingAngel.blockPosition());
        RandomSource randomSource = weepingAngel.level.getRandom();

        // If Angel spawns in the nether, we want only nether variants
        if (biome.is(BiomeTags.IS_NETHER)) {
            return NETHER_WEIGHTED.getRandom();
        }

        if (biome.is(BiomeTags.IS_OVERWORLD)) {
            boolean oreChance = randomSource.nextBoolean() && weepingAngel.blockPosition().getY() < 200;
            return oreChance ? ORE_WEIGHTED.getRandom() : NORMAL_VARIANTS.getRandom();
        }

        return AngelTypes.NORMAL.get();
    }
}
