package mc.jeryn.dev.statues.client.render.entity;

import mc.jeryn.dev.statues.common.entity.angel.WeepingAngel;
import mc.jeryn.dev.statues.common.entity.angel.ai.AngelVariant;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

@Environment(EnvType.CLIENT)
public class AngelRenderState extends LivingEntityRenderState {

    public WeepingAngel.Crackiness crackiness = WeepingAngel.Crackiness.NONE;
    public AngelVariant currentVariant = AngelVariant.STONE;

}
