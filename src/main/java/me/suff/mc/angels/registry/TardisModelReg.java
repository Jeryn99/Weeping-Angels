package me.suff.mc.angels.registry;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.tileentities.NewTardisBlocks;
import me.suff.mc.angels.client.renders.tileentities.AbPropRender;
import me.suff.mc.angels.common.tileentities.STiles;
import me.suff.mc.angels.utils.EnumDoorTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.tardis.mod.client.models.interiordoors.TTCapsuleInteriorModel;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = WeepingAngels.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)


public class TardisModelReg {
    @SubscribeEvent
    public static void Register(FMLClientSetupEvent event) {
        // Render Stuff
        event.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(NewTardisBlocks.exterior_abprop.get(), RenderType.translucent());
        });
        //Exteriors
        ClientRegistry.bindTileEntityRenderer(STiles.exterior_abprop.get(), AbPropRender::new);
        EnumDoorTypes.ABPROP.setInteriorDoorModel(new TTCapsuleInteriorModel());

    }
}
