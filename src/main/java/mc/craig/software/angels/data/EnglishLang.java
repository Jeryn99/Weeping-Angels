package mc.craig.software.angels.data;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.util.WADamageSources;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.common.data.LanguageProvider;

public class EnglishLang extends LanguageProvider {

    public EnglishLang(DataGenerator dataGenerator) {
        super(dataGenerator, WeepingAngels.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {

        // ===== Entity =====
        add(WAEntities.WEEPING_ANGEL.get(), "Weeping Angel");
        add(WAEntities.ANOMALY.get(), "Anomaly");

        // ===== Items =====
        add(WAItems.TIMEY_WIMEY_DETECTOR.get(), "Timey Wimey Detector");

        // ===== Creative Tab =====
        add("itemGroup." + WeepingAngels.MODID, "Weeping Angels");

        // ==== Damage Sources =====
        add(WADamageSources.GENERATOR, "%s somehow got caught up in a generator");
        add(WADamageSources.PUNCH_STONE, "%s punched stone too hard...");

    }

    public void add(DamageSource damageSource, String message){
         add("death.attack." + damageSource.getMsgId(), message);
    }
}
