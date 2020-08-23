package me.swirtzly.minecraft.angels.client.renders.entities;

import javax.annotation.Nullable;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.swirtzly.minecraft.angels.client.models.entity.IAngelModel;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngel;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngelChild;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngelEd;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngelMel;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngela;
import me.swirtzly.minecraft.angels.client.models.entity.ModelAngelaAngel;
import me.swirtzly.minecraft.angels.client.models.entity.ModelClassicAngel;
import me.swirtzly.minecraft.angels.client.renders.entities.layers.CrackLayer;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class AngelRender extends MobRenderer<WeepingAngelEntity, EntityModel<WeepingAngelEntity>> {

	private final EntityModel<WeepingAngelEntity> modelOne = new ModelAngel<WeepingAngelEntity>();
	private final EntityModel<WeepingAngelEntity> modelTwo = new ModelAngelEd<WeepingAngelEntity>();
	private final EntityModel<WeepingAngelEntity> modelChild = new ModelAngelChild<WeepingAngelEntity>();
	private final EntityModel<WeepingAngelEntity> modelClassic = new ModelClassicAngel<WeepingAngelEntity>();
	private final EntityModel<WeepingAngelEntity> modelMel = new ModelAngelMel<WeepingAngelEntity>();
	private final EntityModel<WeepingAngelEntity> modelAngela = new ModelAngela<WeepingAngelEntity>();
	private final EntityModel<WeepingAngelEntity> modelAngela2 = new ModelAngelaAngel();

	@SuppressWarnings("unchecked")
	public AngelRender(EntityRendererManager manager) {
		super(manager, new ModelAngelEd<WeepingAngelEntity>(), 0.0F);
		addLayer(new CrackLayer(this));
	}

	@Override
	public void render(WeepingAngelEntity angel, float p_225623_2_, float p_225623_3_, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int p_225623_6_) {
		ItemStack key = angel.getHeldItemMainhand();

		matrixStack.push();

		// Render key
		matrixStack.push();
		float offset = MathHelper.cos(angel.ticksExisted * 0.1F) * -0.09F;
		matrixStack.scale(0.5F, 0.5F, 0.5F);
		matrixStack.translate(0, -2, 0);
		matrixStack.translate(0, offset, 0);
		renderItem(angel, key, ItemCameraTransforms.TransformType.FIXED);
		matrixStack.pop();

		Minecraft minecraft = Minecraft.getInstance();
		boolean flag = this.isVisible(angel);
		boolean flag1 = !flag && !angel.isInvisibleToPlayer(minecraft.player);
		boolean flag2 = Minecraft.getInstance().func_238206_b_(angel);
		RenderType rendertype = this.func_230496_a_(angel, flag, flag1, flag2);
		if (rendertype != null) {
			IVertexBuilder ivertexbuilder = iRenderTypeBuffer.getBuffer(rendertype);
			switch (angel.getAngelType()) {
				case -1:
					Minecraft.getInstance().getTextureManager().bindTexture(getTexture(modelChild, angel));
					modelChild.render(matrixStack, ivertexbuilder, p_225623_6_, 0, 1.0F, 1.0F, 1.0F, 1.0F);
					break;
				case 0:
					Minecraft.getInstance().getTextureManager().bindTexture(getTexture(modelOne, angel));
					modelOne.render(matrixStack, ivertexbuilder, p_225623_6_, 0, 1.0F, 1.0F, 1.0F, 1.0F);
					break;
				case 1:
					Minecraft.getInstance().getTextureManager().bindTexture(getTexture(modelTwo, angel));
					modelTwo.render(matrixStack, ivertexbuilder, p_225623_6_, 0, 1.0F, 1.0F, 1.0F, 1.0F);
					break;
				case 2:
					Minecraft.getInstance().getTextureManager().bindTexture(getTexture(modelClassic, angel));
					modelClassic.render(matrixStack, ivertexbuilder, p_225623_6_, 0, 1.0F, 1.0F, 1.0F, 1.0F);
					break;
				case 3:
					Minecraft.getInstance().getTextureManager().bindTexture(getTexture(modelMel, angel));
					modelMel.render(matrixStack, ivertexbuilder, p_225623_6_, 0, 1.0F, 1.0F, 1.0F, 1.0F);
					break;
				case 4:
					Minecraft.getInstance().getTextureManager().bindTexture(getTexture(modelAngela, angel));
					modelAngela.render(matrixStack, ivertexbuilder, p_225623_6_, 0, 1.0F, 1.0F, 1.0F, 1.0F);
					break;
				case 5:
					Minecraft.getInstance().getTextureManager().bindTexture(getTexture(modelAngela2, angel));
					modelAngela2.render(matrixStack, ivertexbuilder, p_225623_6_, 0, 1.0F, 1.0F, 1.0F, 1.0F);
					break;
			}
		}
		matrixStack.pop();
	}

	@Override
	public ResourceLocation getEntityTexture(WeepingAngelEntity weepingAngelEntity) {
		return null;
	}


	public ResourceLocation getTexture(EntityModel<WeepingAngelEntity> model, WeepingAngelEntity entity) {
		IAngelModel iAngelModel = (IAngelModel) model;
		return iAngelModel.getTextureForPose(entity);
	}

	private void renderItem(LivingEntity livingEntity, ItemStack itemStack, ItemCameraTransforms.TransformType transformType) {
		if (!p_188358_2_.isEmpty()) {
			Minecraft.getInstance().getFirstPersonRenderer().renderItemSide(livingEntity, itemStack, transformType, false);
		}
	}

}
