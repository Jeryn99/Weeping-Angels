package mc.craig.software.angels.common.blocks;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.items.WAItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class WABlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WeepingAngels.MODID);


    private static <T extends Block> RegistryObject<T> register(String id, Supplier<T> blockSupplier, CreativeModeTab itemGroup) {
        RegistryObject<T> registryObject = BLOCKS.register(id, blockSupplier);
        WAItems.ITEMS.register(id, () -> new BlockItem(registryObject.get(), new Item.Properties().tab(itemGroup)));
        return registryObject;
    }

}
