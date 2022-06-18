package craig.software.mc.angels.client.renders.blockentities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import craig.software.mc.angels.client.models.entity.IAngelModel;
import craig.software.mc.angels.client.models.entity.SantaHat;
import craig.software.mc.angels.client.models.entity.WAModels;
import craig.software.mc.angels.client.poses.WeepingAngelPose;
import craig.software.mc.angels.client.renders.entities.SeasonalLayer;
import craig.software.mc.angels.common.entities.WeepingAngel;
import craig.software.mc.angels.utils.ClientUtil;
import craig.software.mc.angels.common.blockentities.StatueBlockEntity;
import craig.software.mc.angels.common.blocks.StatueBlock;
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

/**
 * Created by Craig on 17/02/2020 @ 12:18
 */
public class StatueRender implements BlockEntityRenderer<StatueBlockEntity>, BlockEntityRendererProvider<StatueBlockEntity> {


    private final SantaHat<Entity> model;

    public StatueRender(Context p_173571_) {
        model = new SantaHat<>(p_173571_.bakeLayer(WAModels.SANTA_HAT));
    }

    @Override
    public void render(StatueBlockEntity statueBlockEntity, float partialTicks, PoseStack matrixStack, @NotNull MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStack.pushPose();
        matrixStack.translate(0.5F, 1.5F, 0.5F);
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180F));
        BlockState blockstate = statueBlockEntity.getBlockState();
        float rotation = 22.5F * (float) blockstate.getValue(StatueBlock.ROTATION);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(rotation));
        EntityModel<WeepingAngel> angel = ClientUtil.getModelForAngel(statueBlockEntity.getAngelType());
        ResourceLocation texture = DefaultPlayerSkin.getDefaultSkin();


        WeepingAngelPose pose = statueBlockEntity.getPose();
        if (angel instanceof IAngelModel angelModel) {
            angelModel.setAngelPose(pose);
            angelModel.toggleHurt(true);
            texture = angelModel.getTextureForPose(statueBlockEntity, pose);
            angel.setupAnim(null, 0, 0, 0, 0, 0);

            matrixStack.pushPose();
            SeasonalLayer.santaHat(matrixStack, bufferIn, combinedLightIn, model, angel, statueBlockEntity.getVariant());
            matrixStack.popPose();

        }
        angel.renderToBuffer(matrixStack, bufferIn.getBuffer(RenderType.entityTranslucent(texture)), combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);
        matrixStack.popPose();


    }


    @Override
    public @NotNull BlockEntityRenderer<StatueBlockEntity> create(@NotNull Context p_173571_) {
        return new StatueRender(p_173571_);
    }
}
