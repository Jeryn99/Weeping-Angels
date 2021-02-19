package me.suff.mc.angels.client.renderer.entiity;

import me.suff.mc.angels.client.models.WeepingAngelModel;
import me.suff.mc.angels.common.entity.WeepingAngelEntity;
import me.suff.mc.angels.enums.WeepingAngelPose;
import me.suff.mc.angels.enums.WeepingAngelVariants;
import me.suff.mc.angels.util.Constants;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/* Created by Craig on 18/02/2021 */
public class WeepingAngelRenderer extends LivingEntityRenderer< WeepingAngelEntity, WeepingAngelModel > {

    private WeepingAngelPose weepingAngelPose = WeepingAngelPose.APPROACH;

    public WeepingAngelRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new WeepingAngelModel(), 1);
    }

    public static Identifier generateTex(WeepingAngelPose pose, WeepingAngelVariants angelVariants) {
        String variant = angelVariants.name().toLowerCase() + "_angel_";
        String location = "textures/entities/angela/";
        location = location + angelVariants.name().toLowerCase().toLowerCase() + "/";
        WeepingAngelPose.Emotion emotion = pose.getEmotion();
        String suffix = emotion.name().toLowerCase();
        if (angelVariants.isHeadless()) {
            suffix = "headless";
        }
        return new Identifier(Constants.MODID, location + variant + suffix + ".png");
    }

    @Override
    public Identifier getTexture(WeepingAngelEntity entity) {
        return generateTex(WeepingAngelPose.getPose(entity.getAngelPose()), WeepingAngelVariants.getVariant(entity.getVarient()));
    }

    public WeepingAngelPose getAngelPose() {
        return weepingAngelPose;
    }

    public void setAngelPose(WeepingAngelPose angelType) {
        weepingAngelPose = angelType;
    }

    @Override
    protected void renderLabelIfPresent(WeepingAngelEntity entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {

    }
}
