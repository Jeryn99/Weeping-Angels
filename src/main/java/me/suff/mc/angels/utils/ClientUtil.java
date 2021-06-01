package me.suff.mc.angels.utils;

import me.suff.mc.angels.client.renders.entities.AngelRender;
import me.suff.mc.angels.client.renders.entities.AnomalyRender;
import me.suff.mc.angels.client.renders.entities.CGRender;
import me.suff.mc.angels.client.renders.tileentities.CGTileRender;
import me.suff.mc.angels.client.renders.tileentities.PlinthTileRender;
import me.suff.mc.angels.client.renders.tileentities.SnowArmTileRender;
import me.suff.mc.angels.client.renders.tileentities.StatueRender;
import me.suff.mc.angels.common.entities.AnomalyEntity;
import me.suff.mc.angels.common.entities.ChronodyneGeneratorEntity;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.tileentities.ChronodyneGeneratorTile;
import me.suff.mc.angels.common.tileentities.PlinthTile;
import me.suff.mc.angels.common.tileentities.SnowArmTile;
import me.suff.mc.angels.common.tileentities.StatueTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientUtil {

    public static < T extends TileEntity > void bindTESR(Class< T > tileEntityClass, TileEntityRenderer< ? super T > specialRenderer) {
        ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, specialRenderer);
    }

    public static < T extends Entity > void bindEntityRender(Class< T > entityClass, IRenderFactory< ? super T > renderFactory) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
    }

    @OnlyIn(Dist.CLIENT)
    public static void playSound(SoundEvent soundIn, float volumeSfx) {
        if (Minecraft.getInstance().world == null) return;
        Minecraft.getInstance().getSoundHandler().play(SimpleSound.master(soundIn, volumeSfx));
    }

    public static void doClientStuff() {
        ClientUtil.bindTESR(SnowArmTile.class, new SnowArmTileRender());
        ClientUtil.bindTESR(ChronodyneGeneratorTile.class, new CGTileRender());
        ClientUtil.bindTESR(PlinthTile.class, new PlinthTileRender());
        ClientUtil.bindTESR(StatueTile.class, new StatueRender());

        ClientUtil.bindEntityRender(WeepingAngelEntity.class, AngelRender::new);
        ClientUtil.bindEntityRender(AnomalyEntity.class, AnomalyRender::new);
        ClientUtil.bindEntityRender(ChronodyneGeneratorEntity.class, (EntityRendererManager p_i50956_1_) -> new CGRender(p_i50956_1_, Minecraft.getInstance().getItemRenderer(), 12));
    }

}
