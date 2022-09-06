package mc.craig.software.angels.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.angels.client.models.ModelRegistration;
import mc.craig.software.angels.client.models.entity.angel.AliceAngelModel;
import mc.craig.software.angels.client.models.entity.angel.AngelModel;
import mc.craig.software.angels.client.render.entity.layers.AngelItemLayer;
import mc.craig.software.angels.client.render.entity.layers.WeepingAngelCrackinessLayer;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.common.entity.angel.ai.AngelVariant;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

public class WeepingAngelRenderer extends LivingEntityRenderer<WeepingAngel, AngelModel> {

    private AngelVariant textureVariant = AngelVariant.STONE;

    public WeepingAngelRenderer(EntityRendererProvider.Context context) {
        super(context, new AliceAngelModel(context.bakeLayer(ModelRegistration.ALICE_ANGEL)), 0F);
        this.addLayer(new WeepingAngelCrackinessLayer(this));
        this.addLayer(new AngelItemLayer(this)); //TODO arms...
      //TODO Rotations & Positions  this.addLayer(new SeasonalLayer(this));
    }

    @Override
    public void render(WeepingAngel pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.textureVariant = pEntity.getVariant();
        this.model = getModel();
        if (pEntity.deathTime > 0) {

            if (textureVariant.getDrops().getItem() instanceof BlockItem blockItem) {

                if (pEntity.deathTime < 10) {
                    for (int i = 0; i < 10; i++) {
                        pEntity.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockItem.getBlock().defaultBlockState()), pEntity.getX(), pEntity.getY() + 1, pEntity.getZ(), 0.0D, 0.1D, 0.2D);
                    }
                }

                for (int i = 0; i < 10; i++) {
                    pEntity.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockItem.getBlock().defaultBlockState()), pEntity.getX(), pEntity.getY(), pEntity.getZ(), 0.0D, 0.1D, 0.2D);
                }
                return;
            }
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Nullable
    @Override
    protected RenderType getRenderType(WeepingAngel pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        return pGlowing ? RenderType.outline(getTextureLocation(pLivingEntity)) : RenderType.entityTranslucentCull(getTextureLocation(pLivingEntity));
    }

    @Override
    public AngelModel getModel() {
        return ModelRegistration.getModelFor(textureVariant);
    }

    @Override
    protected void setupRotations(WeepingAngel pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        if (pEntityLiving.deathTime > 0) return;
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
    }

    @Override
    public ResourceLocation getTextureLocation(WeepingAngel weepingAngel) {
        AngelVariant variant = weepingAngel.getVariant();
        return getModel().texture(weepingAngel.getEmotion(), variant);
    }

    @Override
    protected boolean shouldShowName(WeepingAngel weepingAngel) {
        return false;
    }
}
