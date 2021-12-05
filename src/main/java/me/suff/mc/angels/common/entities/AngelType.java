package me.suff.mc.angels.common.entities;

import me.suff.mc.angels.common.variants.AbstractVariant;
import me.suff.mc.angels.common.variants.AngelVariants;
import me.suff.mc.angels.common.variants.WeightedHandler;
import net.minecraftforge.fml.RegistryObject;

import static me.suff.mc.angels.common.variants.AngelVariants.*;

public enum AngelType {

    DISASTER_MC("Disaster", true, MOSSY.get()), DOCTOR("Doctor", true, NORMAL.get()), ED("Ed"), CHERUB("Cherub"), A_DIZZLE("A_Dizzle"), DYING("Dying"), SPARE_TIME("SpareTimeVA", true, NORMAL.get(), RUSTED.get()), VILLAGER("Villager"), VIO_1("Violet"), VIO_2("Violet");

    private final String readable;
    private final boolean canHold;
    private final WeightedHandler weightedHandler;

    AngelType(String readable) {
        this.readable = readable;
        this.canHold = false;
        WeightedHandler weightedHandler = new WeightedHandler();
        weightedHandler.addEntry(NORMAL.get());
        this.weightedHandler = weightedHandler;
    }

    AngelType(String readable, boolean canHold, AbstractVariant... w) {
        this.readable = readable;
        this.canHold = canHold;
        WeightedHandler weightedHandler = new WeightedHandler();
        for (AbstractVariant types : w) {
            weightedHandler.addEntry(types);
        }
        if (w[0] == MOSSY.get()) { //TODO Really need to make this not be...well this
            for (RegistryObject<AbstractVariant> entry : AngelVariants.VARIANTS.getEntries()) {
                weightedHandler.addEntry(entry.get());
            }
        }
        this.weightedHandler = weightedHandler;
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

    public WeightedHandler getWeightedHandler() {
        return weightedHandler;
    }

    public String getReadable() {
        return readable;
    }

    public boolean canHoldThings() {
        return canHold;
    }
}
