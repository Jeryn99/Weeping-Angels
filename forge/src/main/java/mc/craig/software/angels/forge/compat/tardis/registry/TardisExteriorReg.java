package mc.craig.software.angels.forge.compat.tardis.registry;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.forge.compat.tardis.EnumDoorTypes;
import mc.craig.software.angels.forge.compat.tardis.WATexVariants;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import net.tardis.mod.Tardis;
import net.tardis.mod.exterior.AbstractExterior;
import net.tardis.mod.exterior.TwoBlockBasicExterior;
import net.tardis.mod.misc.DoorSounds;
import net.tardis.mod.registries.ExteriorRegistry;

import java.util.function.Supplier;

public class TardisExteriorReg {
    public static final DeferredRegister<AbstractExterior> EXTERIORS = DeferredRegister.create(new ResourceLocation(Tardis.MODID, "exterior"), WeepingAngels.MODID);
    public static Supplier<IForgeRegistry<AbstractExterior>> EXTERIOR_REGISTRY = ExteriorRegistry.EXTERIOR_REGISTRY;

    public static final RegistryObject<AbstractExterior> ABPROP = EXTERIORS.register("2005exterior", () -> new TwoBlockBasicExterior(() -> TardisBlocks.EXTERIOR_2005.get().defaultBlockState(), false, true, EnumDoorTypes.ABPROP, DoorSounds.WOOD, new ResourceLocation(WeepingAngels.MODID, "textures/gui/exteriors/abexterior.png"), WATexVariants.PTB));

}
