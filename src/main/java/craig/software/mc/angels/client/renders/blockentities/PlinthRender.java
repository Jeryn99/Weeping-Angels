package craig.software.mc.angels.client.renders.blockentities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import craig.software.mc.angels.client.models.entity.IAngelModel;
import craig.software.mc.angels.client.models.entity.SantaHat;
import craig.software.mc.angels.client.models.entity.WAModels;
import craig.software.mc.angels.client.poses.WeepingAngelPose;
import craig.software.mc.angels.client.renders.entities.SeasonalLayer;
import craig.software.mc.angels.common.blockentities.PlinthBlockEntity;
import craig.software.mc.angels.common.blocks.StatueBlock;
import craig.software.mc.angels.common.entities.WeepingAngel;
import craig.software.mc.angels.utils.ClientUtil;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import static craig.software.mc.angels.common.blocks.PlinthBlock.CLASSIC;

public class PlinthRender implements BlockEntityRenderer<PlinthBlockEntity>, BlockEntityRendererProvider<PlinthBlockEntity> {

    private final SantaHat<Entity> model;

    public PlinthRender(Context p_173571_) {
        model = new SantaHat<>(p_173571_.bakeLayer(WAModels.SANTA_HAT));
    }

    @Override
    public void render(PlinthBlockEntity plinthBlockEntity, float partialTicks, @NotNull PoseStack matrixStack, @NotNull MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (plinthBlockEntity.getHasSpawned()) return;

        matrixStack.pushPose();
        matrixStack.translate(0.5F, 2.5F, 0.5F);
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180F));
        BlockState blockstate = plinthBlockEntity.getBlockState();
        float rotation = 22.5F * (float) blockstate.getValue(StatueBlock.ROTATION);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(rotation));
        EntityModel<WeepingAngel> angel = ClientUtil.getModelForAngel(plinthBlockEntity.getAngelType());
        ResourceLocation texture = DefaultPlayerSkin.getDefaultSkin();

        if (plinthBlockEntity.getBlockState().getValue(CLASSIC)) {
            matrixStack.translate(0, 0.5, 0);
        }

        WeepingAngelPose pose = plinthBlockEntity.getPose();
        if (angel instanceof IAngelModel angelModel) {
            angelModel.setAngelPose(pose);
            angelModel.toggleHurt(true);
            texture = angelModel.getTextureForPose(plinthBlockEntity, pose);
            angel.setupAnim(null, 0, 0, 0, 0, 0);

            matrixStack.pushPose();
            SeasonalLayer.santaHat(matrixStack, bufferIn, combinedLightIn, model, angel, plinthBlockEntity.getVariant());
            matrixStack.popPose();

        }
        angel.renderToBuffer(matrixStack, bufferIn.getBuffer(RenderType.entityTranslucent(texture)), combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);
        matrixStack.popPose();

    }

    @Override
    public @NotNull BlockEntityRenderer<PlinthBlockEntity> create(@NotNull Context p_173571_) {
        return new PlinthRender(p_173571_);
    }
}
