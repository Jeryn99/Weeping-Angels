package me.suff.mc.angels.registry;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.tileentities.NewTardisBlocks;
import me.suff.mc.angels.utils.EnumDoorTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.tardis.mod.exterior.AbstractExterior;
import net.tardis.mod.exterior.TwoBlockBasicExterior;
import net.tardis.mod.misc.DoorSounds;

public class TardisExteriorReg {
    public static final DeferredRegister<AbstractExterior> EXTERIORS = DeferredRegister.create(AbstractExterior.class, WeepingAngels.MODID);

    public static final RegistryObject<AbstractExterior> ABPROP = EXTERIORS.register("2005exterior", () -> new TwoBlockBasicExterior(() -> NewTardisBlocks.exterior_abprop.get().defaultBlockState(), true, EnumDoorTypes.ABPROP, DoorSounds.WOOD,new ResourceLocation(WeepingAngels.MODID, "textures/monitor_image_path_here")));



}

