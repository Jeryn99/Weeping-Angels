package mc.craig.software.angels.client.render.entity.layers;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.models.entity.angel.WeepingAngelModel;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class WeepingAngelCrackinessLayer extends RenderLayer<WeepingAngel, WeepingAngelModel> {
    private static final Map<WeepingAngel.Crackiness, ResourceLocation> resourceLocations = ImmutableMap.of(WeepingAngel.Crackiness.LOW, new ResourceLocation(WeepingAngels.MODID, "textures/entity/angel/alice/cracks/low_cracks.png"), WeepingAngel.Crackiness.MEDIUM, new ResourceLocation(WeepingAngels.MODID, "textures/entity/angel/alice/cracks/medium_cracks.png"), WeepingAngel.Crackiness.HIGH, new ResourceLocation(WeepingAngels.MODID, "textures/entity/angel/alice/cracks/high_cracks.png"));

    public WeepingAngelCrackinessLayer(RenderLayerParent<WeepingAngel, WeepingAngelModel> pRenderer) {
        super(pRenderer);
    }

    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, WeepingAngel pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (!pLivingEntity.isInvisible()) {
            WeepingAngel.Crackiness weepingAngelCracks = pLivingEntity.getCrackiness();
            if (weepingAngelCracks != WeepingAngel.Crackiness.NONE) {
                ResourceLocation resourcelocation = resourceLocations.get(weepingAngelCracks);
                renderColoredCutoutModel(this.getParentModel(), resourcelocation, pMatrixStack, pBuffer, pPackedLight, pLivingEntity, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}