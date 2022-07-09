package craig.software.mc.angels.common.entities;

import craig.software.mc.angels.common.variants.AngelVariants;
import craig.software.mc.angels.common.variants.AngelVariant;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

import static craig.software.mc.angels.common.variants.AngelVariants.NORMAL;
import static craig.software.mc.angels.common.variants.AngelVariants.RUSTED;

public enum WeepingAngelTypes {

    DISASTER_MC("Disaster", true, new ArrayList<>()), DOCTOR("Doctor", true, List.of(NORMAL.get())), ED("Ed"), ED_ANGEL_CHILD("Cherub"), A_DIZZLE("A_Dizzle"), DYING("Dying"), SPARE_TIME("SpareTimeVA", true, List.of(NORMAL.get(), RUSTED.get())), VILLAGER("Villager");

    private final String readable;
    private final boolean canHold;
    private final ArrayList<AngelVariant> data = new ArrayList<>();

    WeepingAngelTypes(String readable) {
        this.readable = readable;
        this.canHold = false;
        data.add(NORMAL.get());
    }

    WeepingAngelTypes(String readable, boolean canHold, List<AngelVariant> w) {
        this.readable = readable;
        this.canHold = canHold;
        data.addAll(w);

        if (w.isEmpty()) {
            for (RegistryObject<AngelVariant> entry : AngelVariants.VARIANTS.getEntries()) {
                data.add(entry.get());
            }
        }
    }

    public static WeepingAngelTypes next(WeepingAngelTypes type) {
        int index = type.ordinal();
        int nextIndex = index + 1;
        WeepingAngelTypes[] angels = WeepingAngelTypes.values();
        nextIndex %= angels.length;
        return angels[nextIndex];
    }

    public ArrayList<AngelVariant> getSupportedVariants() {
        return data;
    }

    public AngelVariant getRandom(){
        int index = (int)(Math.random() * data.size());
        return data.get(index);
    }

    public String getReadable() {
        return readable;
    }

    public boolean canHoldThings() {
        return canHold;
    }
}
