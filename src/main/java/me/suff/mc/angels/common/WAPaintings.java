package me.suff.mc.angels.common;

import me.suff.mc.angels.WeepingAngels;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/* Created by Craig on 07/02/2021 */
public class WAPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, WeepingAngels.MODID);

    public static final RegistryObject<PaintingVariant> HAVE_THE_PHONE_BOX = PAINTINGS.register("have_the_phone_box", () -> new PaintingVariant(64, 56));
    public static final RegistryObject<PaintingVariant> ANGEL = PAINTINGS.register("angel", () -> new PaintingVariant(64, 56));
    public static final RegistryObject<PaintingVariant> ANGEL_NETHER = PAINTINGS.register("angel_nether", () -> new PaintingVariant(64, 56));
}
