package craig.software.mc.angels.common.variants;

import craig.software.mc.angels.config.WAConfig;
import craig.software.mc.angels.utils.AngelUtil;

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

    public void addEntry(AngelVariant... angelVariants) {
        for (AngelVariant angelVariant : angelVariants) {
            accumulatedWeight += angelVariant.getRarity();
            Entry e = new Entry();
            e.angelVariant = angelVariant;
            e.accumulatedWeight = accumulatedWeight;
            entries.add(e);
        }
    }

    public AngelVariant getRandom() {
        double r = AngelUtil.RAND.nextDouble() * accumulatedWeight;
        for (Entry entry : entries) {
            if (entry.accumulatedWeight >= r && WAConfig.CONFIG.isVariantPermitted(entry.angelVariant)) {
                return entry.angelVariant;
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