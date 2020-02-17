package me.swirtzly.angels.utils;

import me.swirtzly.angels.client.PlayerMovingSound;
import me.swirtzly.angels.client.renders.entities.AngelRender;
import me.swirtzly.angels.client.renders.entities.AnomalyRender;
import me.swirtzly.angels.client.renders.entities.CGRender;
import me.swirtzly.angels.client.renders.tileentities.CGTileRender;
import me.swirtzly.angels.client.renders.tileentities.PlinthTileRender;
import me.swirtzly.angels.client.renders.tileentities.SnowArmTileRender;
import me.swirtzly.angels.client.renders.tileentities.StatueRender;
import me.swirtzly.angels.common.entities.AnomalyEntity;
import me.swirtzly.angels.common.entities.ChronodyneGeneratorEntity;
import me.swirtzly.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.angels.common.tileentities.ChronodyneGeneratorTile;
import me.swirtzly.angels.common.tileentities.PlinthTile;
import me.swirtzly.angels.common.tileentities.SnowArmTile;
import me.swirtzly.angels.common.tileentities.StatueTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import java.util.function.Supplier;

public class ClientUtil {

	public static <T extends TileEntity> void bindTESR(Class<T> tileEntityClass, TileEntityRenderer<? super T> specialRenderer) {
		ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, specialRenderer);
	}

	public static <T extends Entity> void bindEntityRender(Class<T> entityClass, IRenderFactory<? super T> renderFactory) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
	}


    @OnlyIn(Dist.CLIENT)
    public static void playSound(Object object, SoundEvent soundIn, SoundCategory categoryIn, boolean repeat, Supplier<Boolean> stopCondition, float volumeSfx) {
        Minecraft.getInstance().getSoundHandler().play(new PlayerMovingSound(object, soundIn, categoryIn, repeat, stopCondition, volumeSfx));
    }

    @OnlyIn(Dist.CLIENT)
    public static void playSound(PlayerMovingSound playerMovingSound) {
        Minecraft.getInstance().getSoundHandler().play(playerMovingSound);
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
