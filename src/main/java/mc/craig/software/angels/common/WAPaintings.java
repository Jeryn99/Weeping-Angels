package mc.craig.software.angels.common;

import mc.craig.software.angels.WeepingAngels;
import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/* Created by Craig on 07/02/2021 */
public class WAPaintings {
    public static final DeferredRegister<PaintingType> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, WeepingAngels.MODID);

    public static final RegistryObject<PaintingType> HAVE_THE_PHONE_BOX = PAINTINGS.register("have_the_phone_box", () -> new PaintingType(64, 56));
    public static final RegistryObject<PaintingType> ANGEL = PAINTINGS.register("angel", () -> new PaintingType(64, 56));
    public static final RegistryObject<PaintingType> ANGEL_NETHER = PAINTINGS.register("angel_nether", () -> new PaintingType(64, 56));
}
