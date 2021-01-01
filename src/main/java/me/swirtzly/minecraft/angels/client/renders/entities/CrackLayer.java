package me.swirtzly.minecraft.angels.client.renders.entities;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.common.entities.AngelEnums;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class CrackLayer extends LayerRenderer<WeepingAngelEntity, EntityModel<WeepingAngelEntity>> {
    private static final Map<WeepingAngelEntity.Cracks, ResourceLocation> CRACKS_RESOURCE_LOCATION_MAP = ImmutableMap.of(WeepingAngelEntity.Cracks.LOW, new ResourceLocation(WeepingAngels.MODID, "textures/entities/cracks/crackiness_low.png"), WeepingAngelEntity.Cracks.MEDIUM, new ResourceLocation(WeepingAngels.MODID, "textures/entities/cracks/crackiness_medium.png"), WeepingAngelEntity.Cracks.HIGH, new ResourceLocation(WeepingAngels.MODID, "textures/entities/cracks/crackiness_high.png"));
    public CrackLayer(IEntityRenderer<WeepingAngelEntity, EntityModel<WeepingAngelEntity>> iEntityRenderer) {
        super(iEntityRenderer);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, WeepingAngelEntity angelEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!angelEntity.isInvisible()) {
            WeepingAngelEntity.Cracks cracks = angelEntity.calc();
            if (cracks != WeepingAngelEntity.Cracks.NONE) {
                ResourceLocation resourcelocation = CRACKS_RESOURCE_LOCATION_MAP.get(cracks);
                renderCutoutModel(this.getEntityModel(), resourcelocation, matrixStackIn, bufferIn, packedLightIn, angelEntity, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}