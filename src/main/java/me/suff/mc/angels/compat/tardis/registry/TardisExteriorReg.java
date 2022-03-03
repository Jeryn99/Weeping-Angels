package me.suff.mc.angels.compat.tardis.registry;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.compat.tardis.EnumDoorTypes;
import me.suff.mc.angels.compat.tardis.WATexVariants;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.tardis.mod.exterior.AbstractExterior;
import net.tardis.mod.exterior.TwoBlockBasicExterior;
import net.tardis.mod.misc.DoorSounds;

public class TardisExteriorReg {

    public static final DeferredRegister<AbstractExterior> EXTERIORS = DeferredRegister.create(AbstractExterior.class, WeepingAngels.MODID);

    public static final RegistryObject<AbstractExterior> ABPROP = EXTERIORS.register("2005exterior", () -> new TwoBlockBasicExterior(() -> NewTardisBlocks.EXTERIOR_2005.get().defaultBlockState(), false, EnumDoorTypes.ABPROP, DoorSounds.WOOD, new ResourceLocation(WeepingAngels.MODID, "textures/gui/exteriors/abexterior.png"), WATexVariants.PTB));


}

