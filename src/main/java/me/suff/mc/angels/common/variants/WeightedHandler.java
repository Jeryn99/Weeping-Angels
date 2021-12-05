package me.suff.mc.angels.common.variants;

import me.suff.mc.angels.common.entities.WeepingAngel;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.utils.AngelUtil;

import java.util.ArrayList;
import java.util.List;

public class WeightedHandler {

    private final List<Entry> entries = new ArrayList<>();
    private double accumulatedWeight;

    public void addEntry(AbstractVariant abstractVariant) {
        accumulatedWeight += abstractVariant.getRarity();
        Entry e = new Entry();
        e.abstractVariant = abstractVariant;
        e.accumulatedWeight = accumulatedWeight;
        entries.add(e);
    }

    public AbstractVariant getRandom(WeepingAngel weepingAngel) {
        double r = AngelUtil.RAND.nextDouble() * accumulatedWeight;
        for (Entry entry : entries) {
            if (entry.accumulatedWeight >= r && WAConfig.CONFIG.isVariantPermitted(entry.abstractVariant)) {
                if (weepingAngel == null || weepingAngel.getVariant() == null) {
                    return entry.abstractVariant;
                } else if (weepingAngel.getVariant().canVariantBeUsed(weepingAngel)) {
                    return entry.abstractVariant;
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
        AbstractVariant abstractVariant;
    }
}