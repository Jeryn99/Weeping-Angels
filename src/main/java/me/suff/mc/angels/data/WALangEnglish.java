package me.suff.mc.angels.data;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.WAObjects;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.data.LanguageProvider;

public class WALangEnglish extends LanguageProvider {

    public WALangEnglish(DataGenerator gen) {
        super(gen, WeepingAngels.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        /* Blocks */
        add(WAObjects.Blocks.SNOW_ANGEL.get(), "Angel hiding in Snow");
        add(WAObjects.Blocks.STATUE.get(), "Angel Statue");
        add(WAObjects.Blocks.PLINTH.get(), "Angel Plinth");
        add(WAObjects.Blocks.KONTRON_ORE.get(), "Kontron Ore");
        add(WAObjects.Blocks.COFFIN.get(), "Coffin");

        /* Items */
        add(WAObjects.Items.ANGEL_SPAWNER.get(), "Weeping Angel (%s)");
        add(WAObjects.Items.CHRONODYNE_GENERATOR.get(), "Chronodyne Generator");
        add(WAObjects.Items.KONTRON_INGOT.get(), "Kontron Ingot");
        add(WAObjects.Items.TIMEY_WIMEY_DETECTOR.get(), "Timey Wimey Detector");
        add(WAObjects.Items.CHISEL.get(), "Chisel");

        add(WAObjects.Items.SALLY.get(), "Music Disc");
        add(WAObjects.Items.TIME_PREVAILS.get(), "Music Disc");
        add("item.weeping_angels.music_disc_sally.desc", "Sally Sparrow");
        add("item.weeping_angels.music_disc_time_prevails.desc", "Time Prevails");

        /* Entities */
        add(WAObjects.EntityEntries.WEEPING_ANGEL.get(), "Weeping Angel");
        add(WAObjects.EntityEntries.CHRONODYNE_GENERATOR.get(), "Chronodyne Generator");
        add(WAObjects.EntityEntries.ANOMALY.get(), "Anomaly");

        /* Tooltips */
        add("tooltip.weeping_angels.chisel", TextFormatting.BLUE.toString() +"Used to change appearance of Decorative Angel Blocks");
        add("tooltip.weeping_angels.chisel_right_click",TextFormatting.BLUE.toString() + "- Right click to change angel pose");
        add("tooltip.weeping_angels.chisel_sneak", TextFormatting.BLUE.toString() +"- Sneak + Right click to Change Angel Type");
        add("tooltip.weeping_angels.punch_variant", TextFormatting.BLUE.toString() +  "- Left click to change variant");

        /* Damage Sources */
        add("source.weeping_angels.backintime", "%s was sent back in time by a Angel!");
        add("source.weeping_angels.neck_snap", "%s had their neck snapped by a Angel!");
        add("source.weeping_angels.punch_stone", "%s broke their bones by punching stone...");

        /* Item Groups */
        add("itemGroup.angels", "Weeping Angels");

        /* Config */
        add("config.weeping_angels.teleportRange", "Teleport range");
        add("config.weeping_angels.teleport_entities", "Teleport Entities?");
        add("config.weeping_angels.angel_locking", "Angel on Angel, Quantum locking");
        add("config.weeping_angels.angeldimteleport", "Angel inter-dimensional teleporting");
        add("config.weeping_angels.block_break_range", "Block break Range");
        add("config.weeping_angels.chicken_go_boom", "Chicken Explosions?");
        add("config.weeping_angels.max_spawn", "Angel - Maximum spawn");
        add("config.weeping_angels.spawn_probability", "Spawn Probability");
        add("config.weeping_angels.min_spawn", "Angel - Minimum Spawn");
        add("config.weeping_angels.angel.block_break", "Block breaking");
        add("config.weeping_angels.blowout_torch", "Blow out torch");
        add("config.weeping_angels.genCatacombs", "Gen catacombs?");
        add("config.weeping_angels.chanceGenCatacombs", "Chance to generate a catacomb");
        add("config.weeping_angels.teleport_instant", "Just teleport & No Damage?");
        add("config.weeping_angels.spawntype", "Spawn type");
        add("config.weeping_angels.angel_speed", "Angel Speed");
        add("config.weeping_angels.disallowed_blocks", "A list of blocks angels should NOT break.");
        add("config.weeping_angels.disallowed_dimensions", "A list of dimensions angels should NOT go to.");
        add("config.weeping_angels.angel_move_sound", "Toggle movement sounds for the angels");
        add("config.weeping_angels.angel_seen_sound", "Toggle seen sounds for the angels");
        add("config.weeping_angels.teleport_type", "The Teleport type");
        add("config.weeping_angels.allowed_spawn_dimensions", "A list of dimensions where angels SHOULD spawn");
        add("config.weeping_angels.ql", "Angel on Angel freezing");
        add("config.weeping_angels.pickaxe_only", "Pickaxes and generators only?");
        add("config.weeping_angels.angel_xp_value", "XP gained from Angels");
        add("config.weeping_angels.angel_damage", "Damaged gained from angels");
        add("config.weeping_angels.around_player_range", "Angel target range");
        add("config.weeping_angels.moveSpeed", "Angel move Speed");
        add("config.weeping_angels.transparent_blocks", "Angel Observable Blocks");

        add("category.weeping_angels.angels", "Angels");
        add("category.weeping_angels.spawn", "Spawn Rates");
        add("category.weeping_angels.worldgen", "World Gen");

        /* Sounds */
        add("sound.weeping_angels.angel_seen", "Angel seen");
        add("sound.weeping_angels.child_run", "Child running");
        add("sound.weeping_angels.laughing_child", "Laughing Child");
        add("sound.weeping_angels.light_break", "Light break");
        add("sound.weeping_angels.angel_teleport", "Angel Teleports an Entity");
        add("sound.weeping_angels.stone_scrap", "Stone scrapings");
        add("sound.weeping_angels.ding", "Ding!");
        add("sound.weeping_angels.blow", "Blow");
        add("sound.weeping_angels.angel_death", "Angel crumbles to death");
        add("sound.weeping_angels.projector", "Whirr");

        add("texvar.weeping_angels.yellow_windows", "2005 - Yellow Windows");
        add("texvar.weeping_angels.blue_windows", "2005 - Blue Windows");


    }
}
