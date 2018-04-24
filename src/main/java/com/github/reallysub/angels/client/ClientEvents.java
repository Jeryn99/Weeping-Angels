package com.github.reallysub.angels.client;

import java.lang.reflect.Field;
import java.util.List;

import com.github.reallysub.angels.common.WAObjects;
import com.github.reallysub.angels.utils.LimbManipulationUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEvents {
	
	@SubscribeEvent
	public static void onRenderPlayerPre(RenderPlayerEvent.Pre e) {
		EntityPlayer player = e.getEntityPlayer();
		if (player.getHeldItemMainhand().getItem() == WAObjects.WAItems.timeyWimeyDetector) {
			
			if (player.getPrimaryHand() == EnumHandSide.RIGHT) {
				LimbManipulationUtil.getLimbManipulator(e.getRenderer(), LimbManipulationUtil.Limb.RIGHT_ARM).setAngles(-45, 0, 15);
			} else {
				LimbManipulationUtil.getLimbManipulator(e.getRenderer(), LimbManipulationUtil.Limb.LEFT_ARM).setAngles(-45, 0, -15);
			}
		}
	}
	
	@SubscribeEvent
	public static void onRenderPlayerPost(RenderPlayerEvent.Post event) {
		@SuppressWarnings("rawtypes")
		RenderLivingBase renderer = (RenderLivingBase) Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(event.getEntityPlayer());
		List<LayerRenderer<AbstractClientPlayer>> layerList = ReflectionHelper.getPrivateValue(RenderLivingBase.class, renderer, 4);
		try {
			for (LayerRenderer<AbstractClientPlayer> layer : layerList) {
				for (Field field : layer.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					if (field.getType() == ModelBiped.class) {
						for (ModelRenderer modelRenderer : ((ModelBiped) field.get(layer)).boxList) {
							if (modelRenderer instanceof LimbManipulationUtil.CustomModelRenderer) {
								((LimbManipulationUtil.CustomModelRenderer) modelRenderer).reset();
							}
						}
					} else if (field.getType() == ModelPlayer.class) {
						for (ModelRenderer modelRenderer : ((ModelBiped) field.get(layer)).boxList) {
							if (modelRenderer instanceof LimbManipulationUtil.CustomModelRenderer) {
								((LimbManipulationUtil.CustomModelRenderer) modelRenderer).reset();
							}
						}
					}
				}
			}
			for (ModelRenderer modelRenderer : event.getRenderer().getMainModel().boxList) {
				if (modelRenderer instanceof LimbManipulationUtil.CustomModelRenderer) {
					((LimbManipulationUtil.CustomModelRenderer) modelRenderer).reset();
				}
			}
		} catch (IllegalAccessException ignored) {}
	}
	
}
