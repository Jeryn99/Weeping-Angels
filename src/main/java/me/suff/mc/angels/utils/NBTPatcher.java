package me.suff.mc.angels.utils;

import me.suff.mc.angels.common.entities.AngelType;
import net.minecraft.nbt.CompoundNBT;

public class NBTPatcher {

    public static void strip(CompoundNBT compoundNBT, String replaceValue) {
        if (compoundNBT.contains(replaceValue)) {
            String type = compoundNBT.getString(replaceValue);
            if (type.equals("DISASTER_MC")) {
                compoundNBT.putString(replaceValue, AngelType.DISASTER_MC.name());
            }

            if (type.equals("ED_ANGEL_CHILD")) {
                compoundNBT.putString(replaceValue, AngelType.CHERUB.name());
            }
        }
    }

}
