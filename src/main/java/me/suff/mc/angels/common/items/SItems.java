package me.suff.mc.angels.common.items;

import me.suff.mc.angels.WeepingAngels;
import net.minecraft.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SItems {
    public static final DeferredRegister<Item> ITEMS;

    public static <T extends Item> T createItem(T item) {
        return item;
    }

    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WeepingAngels.MODID);
    }
}

