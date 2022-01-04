package me.suff.mc.angels.compat.tardis.registry;

import me.suff.mc.angels.WeepingAngels;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tardis.mod.blocks.exteriors.ExteriorBlock;
import net.tardis.mod.itemgroups.TItemGroups;

import java.util.function.Supplier;

public class NewTardisBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WeepingAngels.MODID);


    public static RegistryObject<Block> EXTERIOR_ABPROP = register("exterior_abprop", () -> setUpBlock(new ExteriorBlock()));


    private static Block setUpBlock(Block block) {
        return block;
    }

    public static <T extends Block> RegistryObject<T> register(String id, Supplier<T> blockSupplier) {
        return BLOCKS.register(id, blockSupplier);
    }



}