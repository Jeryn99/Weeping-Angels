package mc.craig.software.angels.compat.tardis;

import mc.craig.software.angels.compat.tardis.interiordoors.AbPropIntDoorModel;
import mc.craig.software.angels.compat.tardis.registry.NewTardisBlocks;
import mc.craig.software.angels.compat.tardis.registry.TardisTiles;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class TardisClientStuff {

    public static void clientStuff() {
        // Render Stuff
        RenderTypeLookup.setRenderLayer(NewTardisBlocks.EXTERIOR_2005.get(), RenderType.translucent());
        //Exteriors
        ClientRegistry.bindTileEntityRenderer(TardisTiles.EXTERIOR_2005.get(), AbPropRender::new);
        EnumDoorTypes.ABPROP.setInteriorDoorModel(new AbPropIntDoorModel());
    }
}
