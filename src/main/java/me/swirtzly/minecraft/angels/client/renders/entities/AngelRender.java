package me.swirtzly.minecraft.angels.client.renders.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.swirtzly.minecraft.angels.client.models.entity.*;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class AngelRender extends MobRenderer<WeepingAngelEntity, EntityModel<WeepingAngelEntity>> {

	private final EntityModel<WeepingAngelEntity> modelOne = new ModelAngel<>();
	private final EntityModel<WeepingAngelEntity> modelTwo = new ModelAngelEd<WeepingAngelEntity>();
	private final EntityModel<WeepingAngelEntity> modelChild = new ModelAngelChild<WeepingAngelEntity>();
	private final EntityModel<WeepingAngelEntity> modelClassic = new ModelClassicAngel();
	private final EntityModel<WeepingAngelEntity> modelMel = new ModelAngelMel<WeepingAngelEntity>();
	private final EntityModel<WeepingAngelEntity> modelAngela = new ModelAngela<WeepingAngelEntity>();
	private final EntityModel<WeepingAngelEntity> modelAngela2 = new ModelAngelaAngel();

	public AngelRender(EntityRendererManager manager) {
		super(manager, new ModelAngelEd<WeepingAngelEntity>(), 0.0F);
		addLayer(new CrackLayer(this));
	}

	@Override
	protected float getOverlayProgress(WeepingAngelEntity livingEntityIn, float partialTicks) {
		return 0;
	}

	@Override
	public void render(WeepingAngelEntity angel, float p_225623_2_, float p_225623_3_, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int p_225623_6_) {
		ItemStack key = angel.getHeldItemMainhand();

		matrixStack.push();
		float offset = MathHelper.cos(angel.ticksExisted * 0.1F) * -0.09F;
		matrixStack.scale(0.5F, 0.5F, 0.5F);
		matrixStack.translate(0, 4.5, 0);
		matrixStack.translate(0, offset, 0);
		renderItem(angel, key, ItemCameraTransforms.TransformType.FIXED, false, matrixStack, iRenderTypeBuffer, p_225623_6_);
		matrixStack.pop();

		switch (angel.getAngelType()) {
			case -1:
				entityModel = modelChild;
				break;
			case 0:
				entityModel = modelOne;
				break;
			case 1:
				entityModel = modelTwo;
				break;
			case 2:
				entityModel = modelClassic;
				break;
			case 3:
				entityModel = modelMel;
				break;
			case 4:
				entityModel = modelAngela;
				break;
			case 5:
				entityModel = modelAngela2;
				break;
		}

		super.render(angel, p_225623_2_, p_225623_3_, matrixStack, iRenderTypeBuffer, p_225623_6_);
	}

	@Override
	public ResourceLocation getEntityTexture(WeepingAngelEntity weepingAngelEntity) {
		IAngelModel iAngelModel = (IAngelModel) entityModel;
		return iAngelModel.getTextureForPose(weepingAngelEntity);
	}

	private void renderItem(LivingEntity livingEntityIn, ItemStack itemStackIn, ItemCameraTransforms.TransformType transformTypeIn, boolean leftHand, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn) {
		if (!itemStackIn.isEmpty()) {
			Minecraft.getInstance().getFirstPersonRenderer().renderItemSide(livingEntityIn, itemStackIn, transformTypeIn, leftHand, matrixStackIn, bufferIn, combinedLightIn);
		}
	}

}
