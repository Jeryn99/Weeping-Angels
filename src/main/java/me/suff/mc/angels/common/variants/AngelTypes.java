package me.suff.mc.angels.common.variants;

import com.google.common.collect.Iterables;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public class AngelTypes {


    public static final DeferredRegister<AbstractVariant> VARIANTS = DeferredRegister.create(AbstractVariant.class, WeepingAngels.MODID);
    public static Supplier<IForgeRegistry<AbstractVariant>> VARIANTS_REGISTRY = VARIANTS.makeRegistry("angel_types", () -> new RegistryBuilder<AbstractVariant>().setMaxID(Integer.MAX_VALUE - 1));

    public static final RegistryObject<AbstractVariant> GOLD = VARIANTS.register("gold", () -> new MiningVariant(() -> new ItemStack(Blocks.GOLD_ORE)));
    public static final RegistryObject<AbstractVariant> DIAMOND = VARIANTS.register("diamond", () -> new MiningVariant(() -> new ItemStack(Blocks.DIAMOND_ORE)));
    public static final RegistryObject<AbstractVariant> IRON = VARIANTS.register("iron", () -> new MiningVariant(() -> new ItemStack(Blocks.IRON_ORE)));
    public static final RegistryObject<AbstractVariant> MOSSY = VARIANTS.register("mossy", () -> new Variant(() -> new ItemStack(Blocks.MOSSY_COBBLESTONE)));
    public static final RegistryObject<AbstractVariant> NORMAL = VARIANTS.register("normal", () -> new Variant(() -> new ItemStack(Blocks.COBBLESTONE)));
    public static final RegistryObject<AbstractVariant> RUSTED = VARIANTS.register("rusted", () -> new Variant(() -> new ItemStack(Blocks.GRANITE)));
    public static final RegistryObject<AbstractVariant> RUSTED_NO_ARM = VARIANTS.register("rusted_no_arm", () -> new Variant(() -> new ItemStack(Blocks.GRANITE)));
    public static final RegistryObject<AbstractVariant> RUSTED_NO_WING = VARIANTS.register("rusted_no_wing", () -> new Variant(() -> new ItemStack(Blocks.GRANITE)));
    public static final RegistryObject<AbstractVariant> RUSTED_NO_HEAD = VARIANTS.register("rusted_no_head", () -> new Variant(() -> new ItemStack(Blocks.GRANITE)).setHeadless(true));
    public static final RegistryObject<AbstractVariant> DIRT = VARIANTS.register("dirt", () -> new Variant(() -> new ItemStack(Blocks.DIRT)));
    public static final RegistryObject<AbstractVariant> EMERALD = VARIANTS.register("emerald", () -> new MiningVariant(() -> new ItemStack(Blocks.EMERALD_ORE)));
    public static final RegistryObject<AbstractVariant> COPPER = VARIANTS.register("copper", () -> new MiningVariant(() -> new ItemStack(Blocks.IRON_ORE)));
    public static final RegistryObject<AbstractVariant> LAPIS = VARIANTS.register("lapis_lazuli", () -> new MiningVariant(() -> new ItemStack(Blocks.LAPIS_ORE)));

    public static AbstractVariant getRandom(){
        return Iterables.get(VARIANTS_REGISTRY.get(), AngelUtil.RAND.nextInt(VARIANTS.getEntries().size()));
    }

}
