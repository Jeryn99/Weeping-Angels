package mc.craig.software.angels.client.renders.entities;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.entities.WeepingAngel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class AngelCrackedLayer extends RenderLayer<WeepingAngel, EntityModel<WeepingAngel>> {
    private static final Map<WeepingAngel.Cracks, ResourceLocation> resourceLocations = ImmutableMap.of(WeepingAngel.Cracks.LOW, new ResourceLocation(WeepingAngels.MODID, "textures/entities/crack_low.png"), WeepingAngel.Cracks.MEDIUM, new ResourceLocation(WeepingAngels.MODID, "textures/entities/crack_medium.png"), WeepingAngel.Cracks.HIGH, new ResourceLocation(WeepingAngels.MODID, "textures/entities/crack_high.png"));

    public AngelCrackedLayer(RenderLayerParent<WeepingAngel, EntityModel<WeepingAngel>> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, WeepingAngel pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (!pLivingEntity.isInvisible()) {
            WeepingAngel.Cracks weepCracks = pLivingEntity.getCrackiness();
            if (weepCracks != WeepingAngel.Cracks.NONE) {
                ResourceLocation resourcelocation = resourceLocations.get(weepCracks);
                renderColoredCutoutModel(this.getParentModel(), resourcelocation, pMatrixStack, pBuffer, pPackedLight, pLivingEntity, 1.0F, 1.0F, 1.0F);
            }
        }
    }

}