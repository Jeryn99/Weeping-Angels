package me.suff.mc.angels.client.renders.entities;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class AngelCrackedLayer extends RenderLayer<WeepingAngelEntity, EntityModel<WeepingAngelEntity>> {
    private static final Map<WeepingAngelEntity.Cracks, ResourceLocation> resourceLocations = ImmutableMap.of(WeepingAngelEntity.Cracks.LOW, new ResourceLocation(WeepingAngels.MODID, "textures/entities/crack_low.png"), WeepingAngelEntity.Cracks.MEDIUM, new ResourceLocation(WeepingAngels.MODID, "textures/entities/crack_medium.png"), WeepingAngelEntity.Cracks.HIGH, new ResourceLocation(WeepingAngels.MODID, "textures/entities/crack_high.png"));

    public AngelCrackedLayer(RenderLayerParent<WeepingAngelEntity, EntityModel<WeepingAngelEntity>> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, WeepingAngelEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (!pLivingEntity.isInvisible()) {
            WeepingAngelEntity.Cracks weepCracks = pLivingEntity.getCrackiness();
            if (weepCracks != WeepingAngelEntity.Cracks.NONE) {
                ResourceLocation resourcelocation = resourceLocations.get(weepCracks);
                renderColoredCutoutModel(this.getParentModel(), resourcelocation, pMatrixStack, pBuffer, pPackedLight, pLivingEntity, 1.0F, 1.0F, 1.0F);
            }
        }
    }

}