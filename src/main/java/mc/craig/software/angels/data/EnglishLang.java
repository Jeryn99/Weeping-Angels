package mc.craig.software.angels.data;

import mc.craig.software.angels.WAConfiguration;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.util.WADamageSources;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.data.LanguageProvider;

public class EnglishLang extends LanguageProvider {

    public EnglishLang(DataGenerator dataGenerator) {
        super(dataGenerator, WeepingAngels.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {

        // ==== Entity ====
        add(WAEntities.WEEPING_ANGEL.get(), "Weeping Angel");
        add(WAEntities.ANOMALY.get(), "Anomaly");

        // ==== Items ====
        add(WAItems.DISC_SALLY.get(), "Music Disc");
        add(WAItems.DISC_TIME_PREVAILS.get(), "Music Disc");
        add("item.weeping_angels.music_disc_sally.desc", "Sally Sparrow");
        add("item.weeping_angels.music_disc_time_prevails.desc", "Time Prevails");

        // ==== Creative Tab ====
        add("itemGroup." + WeepingAngels.MODID, "Weeping Angels");

        // ==== Damage Sources =====
        add(WADamageSources.GENERATOR, "%s somehow got caught up in a generator");
        add(WADamageSources.PUNCH_STONE, "%s punched stone too hard...");
        add(WADamageSources.SNAPPED_NECK, "%s had their neck snapped by a Weeping Angel...");

        // ==== Config Values ====
        addConfig(WAConfiguration.CONFIG.hurtType, "Hurt Type?");
        addConfig(WAConfiguration.CONFIG.stalkRange, "Stalk Range");
        addConfig(WAConfiguration.CONFIG.teleportChance, "Teleport Chance");
        addConfig(WAConfiguration.CONFIG.teleportRange, "Teleport Range");
        addConfig(WAConfiguration.CONFIG.bannedDimensions, "Banned Dimensions");
        addConfig(WAConfiguration.CONFIG.blockBreaking, "Block Griefing?");

    }

    public void addConfig(ForgeConfigSpec.ConfigValue waConfiguration, String message){
        add("config.weeping_angels." + waConfiguration.getPath().get(1), message);
    }

    public void add(DamageSource damageSource, String message){
         add("death.attack." + damageSource.getMsgId(), message);
    }
}
