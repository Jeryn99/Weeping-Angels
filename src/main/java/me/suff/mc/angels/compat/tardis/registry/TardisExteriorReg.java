package me.suff.mc.angels.compat.tardis.registry;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.renders.tileentities.CoffinRenderer;
import me.suff.mc.angels.common.tileentities.CoffinTile;
import me.suff.mc.angels.compat.tardis.interiordoors.AbPropIntDoorModel;
import me.suff.mc.angels.utils.EnumDoorTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.tardis.mod.exterior.AbstractExterior;
import net.tardis.mod.exterior.TwoBlockBasicExterior;
import net.tardis.mod.misc.DoorSounds;
import net.tardis.mod.misc.TexVariant;

public class TardisExteriorReg {

    public static final TexVariant[] PTB = {
            new TexVariant(CoffinRenderer.getTexture(CoffinTile.Coffin.PTB), "blue_windows").addInteriorDoorVariant(AbPropIntDoorModel.BLU),
            new TexVariant(CoffinRenderer.getTexture(CoffinTile.Coffin.PTB_2), "yellow_windows").addInteriorDoorVariant(AbPropIntDoorModel.YELLOW),
            new TexVariant(CoffinRenderer.getTexture(CoffinTile.Coffin.PTB_3), "battle").addInteriorDoorVariant(AbPropIntDoorModel.WAR)
    };

    public static final DeferredRegister<AbstractExterior> EXTERIORS = DeferredRegister.create(AbstractExterior.class, WeepingAngels.MODID);

    public static final RegistryObject<AbstractExterior> ABPROP = EXTERIORS.register("2005exterior", () -> new TwoBlockBasicExterior(() -> NewTardisBlocks.EXTERIOR_2005.get().defaultBlockState(), true, EnumDoorTypes.ABPROP, DoorSounds.WOOD, new ResourceLocation(WeepingAngels.MODID, "textures/gui/exteriors/abexterior.png"), PTB));


}

