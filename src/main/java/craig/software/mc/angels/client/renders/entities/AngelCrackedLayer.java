package craig.software.mc.angels.client.renders.entities;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.Map;
import craig.software.mc.angels.WeepingAngels;
import craig.software.mc.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AngelCrackedLayer extends LayerRenderer<WeepingAngelEntity, EntityModel<WeepingAngelEntity>> {
    private static final Map<WeepingAngelEntity.Cracks, ResourceLocation> resourceLocations = ImmutableMap.of(WeepingAngelEntity.Cracks.LOW, new ResourceLocation(WeepingAngels.MODID, "textures/entities/crack_low.png"), WeepingAngelEntity.Cracks.MEDIUM, new ResourceLocation(WeepingAngels.MODID, "textures/entities/crack_medium.png"), WeepingAngelEntity.Cracks.HIGH, new ResourceLocation(WeepingAngels.MODID, "textures/entities/crack_high.png"));

    public AngelCrackedLayer(IEntityRenderer<WeepingAngelEntity, EntityModel<WeepingAngelEntity>> renderer) {
        super(renderer);
    }

    @Override
    public void render(MatrixStack pMatrixStack, IRenderTypeBuffer pBuffer, int pPackedLight, WeepingAngelEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (!pLivingEntity.isInvisible()) {
            WeepingAngelEntity.Cracks weepCracks = pLivingEntity.getCrackiness();
            if (weepCracks != WeepingAngelEntity.Cracks.NONE) {
                ResourceLocation resourcelocation = resourceLocations.get(weepCracks);
                renderColoredCutoutModel(this.getParentModel(), resourcelocation, pMatrixStack, pBuffer, pPackedLight, pLivingEntity, 1.0F, 1.0F, 1.0F);
            }
        }
    }

}