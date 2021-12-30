package me.suff.mc.angels.common.tileentities;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.items.SItems;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tardis.mod.blocks.exteriors.ExteriorBlock;
import net.tardis.mod.itemgroups.TItemGroups;
import net.tardis.mod.misc.TexVariant;
import net.tardis.mod.texturevariants.TextureVariants;

import java.util.function.Supplier;

public class NewTardisBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WeepingAngels.MODID);


    public static RegistryObject<Block> exterior_abprop = register("exterior_abprop", () -> setUpBlock(new ExteriorBlock()), false);


    private static Block setUpBlock(Block block) {
        return block;
    }

    public static <T extends Block> RegistryObject<T> register(String id, Supplier<T> blockSupplier, ItemGroup itemGroup) {
        RegistryObject<T> registryObject = BLOCKS.register(id, blockSupplier);
        SItems.ITEMS.register(id, () -> new BlockItem(registryObject.get(), (new Item.Properties()).tab(itemGroup)));
        return registryObject;
    }

    public static <T extends Block> RegistryObject<T> register(String id, Supplier<T> blockSupplier) {
        RegistryObject<T> registryObject = BLOCKS.register(id, blockSupplier);
        SItems.ITEMS.register(id, () -> new BlockItem(registryObject.get(), (new Item.Properties()).tab(TItemGroups.FUTURE)));
        return registryObject;
    }

    public static <T extends Block> RegistryObject<T> register(String id, Supplier<T> blockSupplier, boolean hasItem) {
        RegistryObject<T> registryObject = BLOCKS.register(id, blockSupplier);
        if (hasItem) {
            SItems.ITEMS.register(id, () -> new BlockItem(registryObject.get(),new Item.Properties().tab(TItemGroups.FUTURE)));
        }

        return registryObject;
    }
}