package me.suff.mc.angels.common.variants;

import me.suff.mc.angels.common.entities.WeepingAngel;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.utils.AngelUtil;

import java.util.ArrayList;
import java.util.List;

public class WeightedHandler {

    private final List<Entry> entries = new ArrayList<>();
    private double accumulatedWeight;

    public void addEntry(AngelVariant angelVariant) {
        accumulatedWeight += angelVariant.getRarity();
        Entry e = new Entry();
        e.angelVariant = angelVariant;
        e.accumulatedWeight = accumulatedWeight;
        entries.add(e);
    }

    public AngelVariant getRandom(WeepingAngel weepingAngel) {
        double r = AngelUtil.RAND.nextDouble() * accumulatedWeight;
        for (Entry entry : entries) {
            if (entry.accumulatedWeight >= r && WAConfig.CONFIG.isVariantPermitted(entry.angelVariant)) {
                if (weepingAngel == null || weepingAngel.getVariant() == null) {
                    return entry.angelVariant;
                } else if (weepingAngel.getVariant().canVariantBeUsed(weepingAngel)) {
                    return entry.angelVariant;
                }
            }
        }
        return AngelTypes.NORMAL.get();
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }

    public List<Entry> getEntries() {
        return entries;
    }

    private static class Entry {
        double accumulatedWeight;
        AngelVariant angelVariant;
    }
}