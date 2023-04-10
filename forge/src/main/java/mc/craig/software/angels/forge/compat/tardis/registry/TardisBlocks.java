package mc.craig.software.angels.forge.compat.tardis.registry;

import mc.craig.software.angels.WeepingAngels;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tardis.mod.blocks.exteriors.ExteriorBlock;

import java.util.function.Supplier;

public class TardisBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WeepingAngels.MODID);


    public static RegistryObject<Block> EXTERIOR_2005 = register("2005_exterior", () -> setUpBlock(new ExteriorBlock()));


    private static Block setUpBlock(Block block) {
        return block;
    }

    public static <T extends Block> RegistryObject<T> register(String id, Supplier<T> blockSupplier) {
        return BLOCKS.register(id, blockSupplier);
    }

}
