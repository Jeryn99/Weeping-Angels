package me.suff.mc.angels.common.entities;

import java.util.ArrayList;
import java.util.Arrays;
import me.suff.mc.angels.common.variants.AbstractVariant;
import me.suff.mc.angels.common.variants.AngelVariants;
import net.minecraftforge.fml.RegistryObject;

import static me.suff.mc.angels.common.variants.AngelVariants.NORMAL;
import static me.suff.mc.angels.common.variants.AngelVariants.RUSTED;

public enum AngelType {

    DISASTER_MC("Disaster", true), DOCTOR("Doctor", true, NORMAL.get()), ED("Ed"), CHERUB("Cherub"), A_DIZZLE("A_Dizzle"), DYING("Dying"), SPARE_TIME("SpareTimeVA", true, NORMAL.get(), RUSTED.get()), VILLAGER("Villager"), VIO_1("Violet"), VIO_2("Violet");

    private final String readable;
    private final boolean canHold;
    private final ArrayList<AbstractVariant> data = new ArrayList();

    AngelType(String readable) {
        this.readable = readable;
        this.canHold = false;
        data.add(NORMAL.get());
    }

    AngelType(String readable, boolean canHold, AbstractVariant... w) {
        this.readable = readable;
        this.canHold = canHold;
        data.addAll(Arrays.asList(w));

        if (w.length == 0) {
            for (RegistryObject<AbstractVariant> entry : AngelVariants.VARIANTS.getEntries()) {
                data.add(entry.get());
            }
        }
    }

    public static AngelType next(AngelType type) {
        int index = type.ordinal();
        int nextIndex = index + 1;
        AngelType[] angels = AngelType.values();
        nextIndex %= angels.length;
        return angels[nextIndex];
    }

    public static AngelType get(String type) {
        for (AngelType value : AngelType.values()) {
            if (value.name().equalsIgnoreCase(type)) {
                return value;
            }
        }
        return DISASTER_MC;
    }

    public ArrayList<AbstractVariant> getSupportedVariants() {
        return data;
    }

    public AbstractVariant getRandom() {
        int index = (int) (Math.random() * data.size());
        return data.get(index);
    }

    public String getReadable() {
        return readable;
    }

    public boolean canHoldThings() {
        return canHold;
    }
}
