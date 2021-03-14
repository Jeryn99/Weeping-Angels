package me.suff.mc.angels.common.entities.attributes;

import me.suff.mc.angels.WeepingAngels;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WAAttributes {
    public static final DeferredRegister< Attribute > ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, WeepingAngels.MODID);

    public static final RegistryObject< Attribute > BLOCK_BREAK_RANGE = ATTRIBUTES.register("block_break_range", () -> new RangedAttribute("weeping_angels.block_break_range", 15, 0D, 120D).setShouldWatch(true));


}
