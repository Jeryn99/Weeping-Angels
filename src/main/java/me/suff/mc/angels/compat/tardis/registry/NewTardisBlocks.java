package me.suff.mc.angels.compat.tardis.registry;

import java.util.function.Supplier;
import me.suff.mc.angels.WeepingAngels;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tardis.mod.blocks.exteriors.ExteriorBlock;

public class NewTardisBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WeepingAngels.MODID);


    public static RegistryObject<Block> EXTERIOR_2005 = register("2005_exterior", () -> setUpBlock(new ExteriorBlock()));


    private static Block setUpBlock(Block block) {
        return block;
    }

    public static <T extends Block> RegistryObject<T> register(String id, Supplier<T> blockSupplier) {
        return BLOCKS.register(id, blockSupplier);
    }


}