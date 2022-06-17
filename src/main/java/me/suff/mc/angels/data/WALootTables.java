package me.suff.mc.angels.data;

import com.google.gson.*;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.WAObjects;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Supplier;

public class WALootTables extends LootTableProvider {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final DataGenerator generator;


    public WALootTables(DataGenerator gen) {
        super(gen);
        this.generator = gen;
    }

    public static Path getPath(Path path, ResourceLocation rl) {
        return path.resolve("data/" + rl.getNamespace() + "/loot_tables/blocks/" + rl.getPath() + ".json");
    }

    @Override
    public void run(@NotNull CachedOutput cache) {
        Path path = this.generator.getOutputFolder();

        for (Block block : ForgeRegistries.BLOCKS.getValues()) {
            if (ForgeRegistries.BLOCKS.getKey(block).getNamespace().equalsIgnoreCase(WeepingAngels.MODID) && block != WAObjects.Blocks.KONTRON_ORE.get()) {

                try {
                    this.generateSelfTable(block, cache, path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public @NotNull String getName() {
        return "Loot Tables";
    }

    public void generateSelfTable(Block block, CachedOutput cache, Path base) throws IOException {
        this.generateTable(cache, getPath(base, ForgeRegistries.BLOCKS.getKey(block)), () -> this.createBlockDropSelf(block));
    }

    public void generateTable(CachedOutput cache, Path path, Supplier<JsonElement> element) throws IOException {
        DataProvider.saveStable(cache, element.get(), path);
    }

    public JsonElement createBlockDropGuarantied(Block block, ResourceLocation drop) {
        JsonObject root = new JsonObject();
        root.add("type", new JsonPrimitive("minecraft:block"));

        JsonArray pool = new JsonArray();

        JsonObject first = new JsonObject();

        first.add("name", new JsonPrimitive(ForgeRegistries.BLOCKS.getKey(block).toString()));
        first.add("rolls", new JsonPrimitive(1));

        JsonArray entries = new JsonArray();

        JsonObject entry = new JsonObject();

        entry.add("type", new JsonPrimitive("minecraft:item"));
        entry.add("name", new JsonPrimitive(drop.toString()));

        entries.add(entry);

        first.add("entries", entries);

        pool.add(first);

        root.add("pools", pool);

        return root;
    }

    public JsonElement createBlockDropSelf(Block block) {
        return this.createBlockDropGuarantied(block, ForgeRegistries.BLOCKS.getKey(block));
    }

}