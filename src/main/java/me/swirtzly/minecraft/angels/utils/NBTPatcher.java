package me.swirtzly.minecraft.angels.utils;

import me.swirtzly.minecraft.angels.common.entities.AngelEnums;
import net.minecraft.nbt.CompoundNBT;

public class NBTPatcher {

    public static void angelaToVillager(CompoundNBT compoundNBT, String replaceValue) {
        if (compoundNBT.contains(replaceValue)) {
            String type = compoundNBT.getString(replaceValue);
            if (type.equals("ANGELA")) {
                compoundNBT.putString(replaceValue, AngelEnums.AngelType.VILLAGER.name());
            }
        }
    }

}
