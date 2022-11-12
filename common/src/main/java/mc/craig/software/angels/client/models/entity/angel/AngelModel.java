package mc.craig.software.angels.client.models.entity.angel;

import mc.craig.software.angels.common.blockentity.StatueBlockEntity;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.common.entity.angel.ai.AngelEmotion;
import mc.craig.software.angels.common.entity.angel.ai.AngelVariant;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.resources.ResourceLocation;

import static mc.craig.software.angels.client.models.entity.angel.AliceAngelModel.*;

public abstract class AngelModel extends HierarchicalModel<WeepingAngel> {
    public Iterable<ModelPart> getWings() {
        return null;
    }

    public abstract ModelPart getHead();

    public ResourceLocation texture(AngelEmotion angelEmotion, AngelVariant angelVariant) {
        return DefaultPlayerSkin.getDefaultSkin();
    }

    public void animateTile(StatueBlockEntity statueBlockEntity) {

    }
//TODO: Fix this
//    public AnimationDefinition poseForId(int index) {
//        return getAnimationDefinition(index);
//    }
//TODO: Fix this
//    public static AnimationDefinition getAnimationDefinition(int index) {
//        return switch (index) {
//            case 1 -> AliceAngelModel.IDLE1;
//            case 2 -> IDLE2;
//            case 3 -> IDLE3;
//            case 4 -> IDLE4;
//            case 5 -> IDLE5;
//            case 6 -> IDLE6;
//            case 7 -> IDLE7;
//            case 8 -> ANGRY1;
//            case 9 -> ANGRY2;
//            case 10 -> ANGRY3;
//            case 11 -> ANGRY4;
//            case 12 -> ANGRY5;
//            case 0 -> ANGRY6;
//            default -> IDLE2;
//        };
//    }
}
