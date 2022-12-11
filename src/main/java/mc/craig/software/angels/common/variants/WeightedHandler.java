package mc.craig.software.angels.common.variants;

import mc.craig.software.angels.common.entities.WeepingAngelEntity;
import mc.craig.software.angels.config.WAConfig;
import mc.craig.software.angels.utils.AngelUtil;

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

    public AbstractVariant getRandom(WeepingAngelEntity weepingAngelEntity) {
        double r = AngelUtil.RAND.nextDouble() * accumulatedWeight;
        for (Entry entry : entries) {
            if (entry.accumulatedWeight >= r && WAConfig.CONFIG.isVariantPermitted(entry.abstractVariant) && weepingAngelEntity == null || entry.abstractVariant.canVariantBeUsed(weepingAngelEntity)) {
                return entry.abstractVariant;
            }
        }
        return AngelVariants.NORMAL.get();
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }

    private class Entry {
        double accumulatedWeight;
        AbstractVariant abstractVariant;
    }
}