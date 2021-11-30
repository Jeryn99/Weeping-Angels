package me.suff.mc.angels.common.variants;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.entities.WeepingAngel;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class AngelTypes {

    public static final DeferredRegister<AbstractVariant> VARIANTS = DeferredRegister.create(AbstractVariant.class, WeepingAngels.MODID);
    public static final RegistryObject<AbstractVariant> GOLD = VARIANTS.register("gold", () -> new MiningVariant(() -> new ItemStack(Blocks.GOLD_ORE), 25));
    public static final RegistryObject<AbstractVariant> DIAMOND = VARIANTS.register("diamond", () -> new MiningVariant(() -> new ItemStack(Blocks.DIAMOND_ORE), 5));
    public static final RegistryObject<AbstractVariant> IRON = VARIANTS.register("iron", () -> new MiningVariant(() -> new ItemStack(Blocks.IRON_ORE), 50));
    public static final RegistryObject<AbstractVariant> MOSSY = VARIANTS.register("mossy", () -> new BaseVariant(() -> new ItemStack(Blocks.MOSSY_COBBLESTONE), 45));
    public static final RegistryObject<AbstractVariant> NORMAL = VARIANTS.register("normal", () -> new BaseVariant(() -> new ItemStack(Blocks.COBBLESTONE), 45));
    public static Predicate<WeepingAngel> BANNED_FROM_OVERWORLD = weepingAngelEntity -> {
        if (weepingAngelEntity.level.dimension() != Level.NETHER) {
            weepingAngelEntity.setVarient(AngelTypes.NORMAL.get());
        }
        return false;
    };
    public static final RegistryObject<AbstractVariant> BASALT = VARIANTS.register("basalt", () -> new MiningVariant(() -> new ItemStack(Blocks.BASALT), 30, BANNED_FROM_OVERWORLD));
    public static final RegistryObject<AbstractVariant> RUSTED = VARIANTS.register("rusted", () -> new BaseVariant(() -> new ItemStack(Blocks.GRANITE), 45));
    public static final RegistryObject<AbstractVariant> RUSTED_NO_ARM = VARIANTS.register("rusted_no_arm", () -> new BaseVariant(() -> new ItemStack(Blocks.GRANITE), 45));
    public static final RegistryObject<AbstractVariant> RUSTED_NO_WING = VARIANTS.register("rusted_no_wing", () -> new BaseVariant(() -> new ItemStack(Blocks.GRANITE), 45));
    public static final RegistryObject<AbstractVariant> RUSTED_NO_HEAD = VARIANTS.register("rusted_no_head", () -> new BaseVariant(() -> new ItemStack(Blocks.GRANITE), 45).setHeadless(true));
    public static final RegistryObject<AbstractVariant> DIRT = VARIANTS.register("dirt", () -> new BaseVariant(() -> new ItemStack(Blocks.DIRT), 10));
    public static final RegistryObject<AbstractVariant> EMERALD = VARIANTS.register("emerald", () -> new MiningVariant(() -> new ItemStack(Blocks.EMERALD_ORE), 20));
    public static final RegistryObject<AbstractVariant> COPPER = VARIANTS.register("copper", () -> new MiningVariant(() -> new ItemStack(Blocks.COPPER_ORE), 20));
    public static final RegistryObject<AbstractVariant> LAPIS = VARIANTS.register("lapis_lazuli", () -> new MiningVariant(() -> new ItemStack(Blocks.LAPIS_ORE), 10));
    public static Predicate<WeepingAngel> FREE_REIGN = weepingAngelEntity -> true;
    public static final RegistryObject<AbstractVariant> QUARTZ = VARIANTS.register("quartz", () -> new MiningVariant(() -> new ItemStack(Blocks.NETHER_QUARTZ_ORE), 30, FREE_REIGN));
    public static Predicate<WeepingAngel> BANNED_FROM_NETHER = weepingAngelEntity -> {
        if (weepingAngelEntity.level.dimension() == Level.NETHER) {
            weepingAngelEntity.setVarient(AngelUtil.RAND.nextBoolean() ? AngelTypes.BASALT.get() : AngelTypes.QUARTZ.get());
        }
        return false;
    };
    public static Supplier<IForgeRegistry<AbstractVariant>> VARIANTS_REGISTRY = VARIANTS.makeRegistry("angel_types", () -> new RegistryBuilder<AbstractVariant>().setMaxID(Integer.MAX_VALUE - 1));


}
