package me.suff.mc.angels.common.entities;

import me.suff.mc.angels.common.variants.AbstractVariant;
import me.suff.mc.angels.common.variants.AngelTypes;
import me.suff.mc.angels.common.variants.WeightedHandler;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.ArrayList;
import java.util.List;

import static me.suff.mc.angels.common.variants.AngelTypes.NORMAL;
import static me.suff.mc.angels.common.variants.AngelTypes.RUSTED;

public enum AngelType {

    DISASTER_MC("Disaster", true, new ArrayList<>()), DOCTOR("Doctor", true, List.of(NORMAL.get())), ED("Ed"), ED_ANGEL_CHILD("Cherub"), A_DIZZLE("A_Dizzle"), DYING("Dying"), SPARE_TIME("SpareTimeVA", true, List.of(NORMAL.get(), RUSTED.get())), VILLAGER("Villager");

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

    AngelType(String readable, boolean canHold, List<AbstractVariant> w) {
        this.readable = readable;
        this.canHold = canHold;
        WeightedHandler weightedHandler = new WeightedHandler();
        for (AbstractVariant types : w) {
            weightedHandler.addEntry(types);
        }
        if (w.isEmpty()) {
            for (RegistryObject<AbstractVariant> entry : AngelTypes.VARIANTS.getEntries()) {
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
