package mc.craig.software.angels.common;

import mc.craig.software.angels.WeepingAngels;
import net.minecraft.world.entity.decoration.Motive;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/* Created by Craig on 07/02/2021 */
public class WAPaintings {
    public static final DeferredRegister<Motive> PAINTINGS = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, WeepingAngels.MODID);

    public static final RegistryObject<Motive> HAVE_THE_PHONE_BOX = PAINTINGS.register("have_the_phone_box", () -> new Motive(64, 56));
    public static final RegistryObject<Motive> ANGEL = PAINTINGS.register("angel", () -> new Motive(64, 56));
    public static final RegistryObject<Motive> ANGEL_NETHER = PAINTINGS.register("angel_nether", () -> new Motive(64, 56));
}
