package me.swirtzly.angels.utils;

import me.swirtzly.angels.client.PlayerMovingSound;
import net.minecraft.client.Minecraft;
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

    @OnlyIn(Dist.CLIENT)
	public static <T extends TileEntity> void bindTESR(Class<T> tileEntityClass, TileEntityRenderer<? super T> specialRenderer) {
		ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, specialRenderer);
	}

    @OnlyIn(Dist.CLIENT)
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
}
