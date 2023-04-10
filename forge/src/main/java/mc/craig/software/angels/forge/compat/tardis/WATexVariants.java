package mc.craig.software.angels.forge.compat.tardis;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.blockentity.CoffinBlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.tardis.mod.misc.TexVariant;

import java.util.Locale;

public class WATexVariants {
    public static final ResourceLocation BLU = new ResourceLocation(WeepingAngels.MODID, "textures/exteriors/ab_door_blue.png");
    public static final ResourceLocation YELLOW = new ResourceLocation(WeepingAngels.MODID, "textures/exteriors/ab_door_yellow.png");
    public static final ResourceLocation YELLOW_DARKER = new ResourceLocation(WeepingAngels.MODID, "textures/exteriors/ab_door_yellow_darker.png");
    public static final ResourceLocation WAR = new ResourceLocation(WeepingAngels.MODID, "textures/exteriors/ab_door_war.png");

    public static ResourceLocation getCoffinTexture(CoffinBlockEntity.Coffin coffin) {
        return new ResourceLocation(WeepingAngels.MODID, "textures/tiles/coffin/" + coffin.name().toLowerCase(Locale.ENGLISH) + ".png");
    }
    public static final TexVariant[] PTB = {
            new TexVariant(getCoffinTexture(CoffinBlockEntity.Coffin.PTB), "blue_windows").addInteriorDoorVariant(BLU),
            new TexVariant(getCoffinTexture(CoffinBlockEntity.Coffin.PTB_2), "yellow_windows").addInteriorDoorVariant(YELLOW),
            new TexVariant(getCoffinTexture(CoffinBlockEntity.Coffin.PTB_3), "battle").addInteriorDoorVariant(WAR),
            new TexVariant(getCoffinTexture(CoffinBlockEntity.Coffin.PTB_4), "damaged").addInteriorDoorVariant(WAR),
            new TexVariant(getCoffinTexture(CoffinBlockEntity.Coffin.PTB_5), "yellow_darker").addInteriorDoorVariant(YELLOW_DARKER)
    };
}
