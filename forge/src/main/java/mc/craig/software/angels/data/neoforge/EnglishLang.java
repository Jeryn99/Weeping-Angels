package mc.craig.software.angels.data.neoforge;

import mc.craig.software.angels.WAConfiguration;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WAConstants;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.WASounds;
import mc.craig.software.angels.common.blocks.WABlocks;
import mc.craig.software.angels.common.entity.angel.ai.AngelVariant;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.util.WADamageSources;
import mc.craig.software.angels.util.WATags;
import net.minecraft.Util;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public class EnglishLang extends LanguageProvider {

    public EnglishLang(DataGenerator dataGenerator) {
        super(dataGenerator.getPackOutput(), WeepingAngels.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {

        // ==== Messages/UI ====
        add(WAConstants.ANOMALIES_DETECTED, "Anomalies Detected: %s");
        add(WAConstants.CHISEL_POSE, "* Interact to change pose");
        add(WAConstants.CHISEL_VARIANT, "* Sneak + Interact to change variant");
        add(WAConstants.ANGEL_EMOTION, "Emotion");
        add(WAConstants.ANGEL_VARIANT, "Variant");
        add(WAConstants.ANGEL_POSES, "Pose");

        // ==== Angel Variants =====
        addAngelVariant(AngelVariant.BASALT, "Alice (Basalt)");
        addAngelVariant(AngelVariant.STONE, "Alice (Stone)");
        addAngelVariant(AngelVariant.COPPER, "Alice (Copper Ore)");
        addAngelVariant(AngelVariant.DIAMOND, "Alice (Diamond Ore)");
        addAngelVariant(AngelVariant.EMERALD, "Alice (Emerald Ore)");
        addAngelVariant(AngelVariant.GOLD, "Alice (Gold Ore)");
        addAngelVariant(AngelVariant.IRON, "Alice (Iron Ore)");
        addAngelVariant(AngelVariant.LAPIS_LAZULI, "Alice (Lapis Ore)");
        addAngelVariant(AngelVariant.MOSSY, "Alice (Mossy)");
        addAngelVariant(AngelVariant.QUARTZ, "Alice (Quartz)");
        addAngelVariant(AngelVariant.RUSTED, "Alice (Rusted)");
        addAngelVariant(AngelVariant.RUSTED_NO_ARM, "Alice (Rusted - No Arm)");
        addAngelVariant(AngelVariant.RUSTED_NO_HEAD, "Alice (Rusted - No Head)");
        addAngelVariant(AngelVariant.RUSTED_NO_WING, "Alice (Rusted - Missing Wing)");
        addAngelVariant(AngelVariant.DIRT, "Alice (Dirt)");
        addAngelVariant(AngelVariant.GAS_RUSTED, "SpareTime (Rusted)");
        addAngelVariant(AngelVariant.GAS_STONE, "SpareTime (Stone)");
        addAngelVariant(AngelVariant.DOCTOR, "The Doctor");
        addAngelVariant(AngelVariant.A_DIZZLE, "a_dizzle");

        // ==== Entity ====
        add(WAEntities.WEEPING_ANGEL.get(), "Weeping Angel");

        // ==== Blocks =====
        add(WABlocks.KONTRON_ORE.get(), "Kontron Ore");
        add(WABlocks.KONTRON_ORE_DEEPSLATE.get(), "Kontron Ore (Deepslate)");
        add(WABlocks.CHRONODYNE_GENERATOR.get(), "Chronodyne Generator");
        add(WABlocks.COFFIN.get(), "Coffin");
        add(WABlocks.STATUE.get(), "Statue");
        add(WABlocks.PLINTH.get(), "Plinth");
        add(WABlocks.SNOW_ANGEL.get(), "Snow Angel");

        // ==== Items ====
        add(WAItems.TIMEY_WIMEY_DETECTOR.get(), "Timey Wimey Detector");
        add(WAItems.KONTRON_INGOT.get(), "Kontron Ingot");
        add(WAItems.ANGEL_SPAWNER.get(), "Spawn Weeping Angel");
        add(WAItems.CHRONODYNE_GENERATOR.get(), "Chronodyne Generator");
        add(WAItems.CHISEL.get(), "Chisel");
        add(WAItems.DISC_SALLY.get(), "Music Disc");
        add(WAItems.DISC_TIME_PREVAILS.get(), "Music Disc");
        add(Util.makeDescriptionId("jukebox_song", ResourceLocation.tryBuild(WeepingAngels.MODID, "sally")), "Sally Sparrow");
        add(Util.makeDescriptionId("jukebox_song",  ResourceLocation.tryBuild(WeepingAngels.MODID, "time_prevails")), "Time Prevails");

        // ==== Creative Tab ====
        add(WAConstants.CREATIVE_TAB, "Weeping Angels");

        // ==== Damage Sources =====

        add(WADamageSources.GENERATOR, "%s was sucked into the vortex...");
        add(WADamageSources.PUNCH_STONE, "%s punched stone too hard...");
        add(WADamageSources.SNAPPED_NECK, "%s had their neck snapped by a Weeping Angel");

        // ==== Sounds ====
        addSound(WASounds.ANGEL_MOCKING.get(), "Angel mocks");
        addSound(WASounds.BLOW.get(), "Angel blows");
        addSound(WASounds.DING.get(), "Ding!");
        addSound(WASounds.DISC_SALLY.get(), "Sally");
        addSound(WASounds.DISC_TIME_PREVAILS.get(), "Time prevails");
        addSound(WASounds.LOCKED.get(), "Locked");
        addSound(WASounds.KNOCK.get(), "Knocking");
        addSound(WASounds.TARDIS_TAKEOFF.get(), "Tardis Takeoff");
        addSound(WASounds.PROJECTOR.get(), "Whirr");
        addSound(WASounds.CRUMBLING.get(), "Crumbling");

        // ==== Config Values ====
        addConfig(WAConfiguration.CONFIG.hurtType, "Hurt Type?");
        addConfig(WAConfiguration.CONFIG.stalkRange, "Stalk Range");
        addConfig(WAConfiguration.CONFIG.teleportChance, "Teleport Chance");
        addConfig(WAConfiguration.CONFIG.teleportRange, "Teleport Range");
        addConfig(WAConfiguration.CONFIG.bannedDimensions, "Banned Dimensions");
        addConfig(WAConfiguration.CONFIG.blockBreaking, "Block Griefing?");

        addTag(() -> WATags.ANGEL_SPAWNS, "Weeping Angel Spawns");
        addTag(() -> WATags.ANOMALYS, "Weeping Angel Anomalies");
        addTag(() -> WATags.ATTACK_OVERRIDES, "Can Attack Weeping Angel With");
        addTag(() -> WATags.NO_BREAKING, "Unbreakable by Weeping Angels");
        addTag(() -> WATags.CATACOMB_STRUCTURE_BIOMES, "Biomes for Catacombs");
        addTag(() -> WATags.STEALABLE_ITEMS, "Weeping Angel can Steal");

    }

    public void addAngelVariant(AngelVariant angelVariant, String lang) {
        add("variant." + WeepingAngels.MODID + "." + angelVariant.location().getPath(), lang);
    }

    public void addSound(SoundEvent soundEvent, String lang) {
        add("subtitle." + WeepingAngels.MODID + "." + soundEvent.getLocation().getPath(), lang);
    }

    public void addConfig(ModConfigSpec.ConfigValue waConfiguration, String message) {
        add("config.weeping_angels." + waConfiguration.getPath().get(1), message);
    }

    public void add(ResourceKey<DamageType> damageSource, String message) {
        add("death.attack." + damageSource.location().getPath(), message);
        add("death.attack." + damageSource.location().getPath() + ".player", message);
    }
}
