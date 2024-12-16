package dev.jeryn.angels.client.models.entity.angel;

import dev.jeryn.angels.common.blockentity.StatueBlockEntity;
import dev.jeryn.angels.common.entity.angel.WeepingAngel;
import dev.jeryn.angels.common.entity.angel.ai.AngelEmotion;
import dev.jeryn.angels.common.entity.angel.ai.AngelVariant;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import static dev.jeryn.angels.client.models.entity.angel.AliceAngelModel.*;

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

    public static boolean isBlockPosBehindPlayer(Player player, BlockPos blockPos) {
        // Get the player's facing direction as a normalized vector
        Vec3 playerFacing = player.getLookAngle().normalize();

        // Get the vector from the player to the BlockPos
        Vec3 playerPos = player.position();
        Vec3 toBlockPos = new Vec3(blockPos.getX() + 0.5 - playerPos.x, blockPos.getY() + 0.5 - playerPos.y, blockPos.getZ() + 0.5 - playerPos.z).normalize();

        // Calculate the dot product of the two vectors
        double dotProduct = playerFacing.dot(toBlockPos);

        // If the dot product is less than 0, the BlockPos is behind the player
        return dotProduct < 0;
    }

    public AnimationDefinition poseForId(int index) {
        return getAnimationDefinition(index);
    }

    public static AnimationDefinition getAnimationDefinition(int index) {
        return switch (index) {
            case 1 -> AliceAngelModel.IDLE1;
            case 2 -> IDLE2;
            case 3 -> IDLE3;
            case 4 -> IDLE4;
            case 5 -> IDLE5;
            case 6 -> IDLE6;
            case 7 -> IDLE7;
            case 8 -> ANGRY1;
            case 9 -> ANGRY2;
            case 10 -> ANGRY3;
            case 11 -> ANGRY4;
            case 12 -> ANGRY5;
            case 0 -> ANGRY6;
            default -> IDLE2;
        };
    }
}
