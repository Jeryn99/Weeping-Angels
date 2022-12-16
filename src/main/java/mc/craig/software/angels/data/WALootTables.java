package mc.craig.software.angels.data;

import com.google.gson.*;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WAObjects;
import mc.craig.software.angels.compat.tardis.registry.NewTardisBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Supplier;

public class WALootTables implements IDataProvider {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final DataGenerator generator;


    public WALootTables(DataGenerator gen) {
        this.generator = gen;
    }

    public static Path getPath(Path path, ResourceLocation rl) {
        return path.resolve("data/" + rl.getNamespace() + "/loot_tables/blocks/" + rl.getPath() + ".json");
    }

    @Override
    public void run(DirectoryCache cache) throws IOException {
        Path path = this.generator.getOutputFolder();

        for (Block block : ForgeRegistries.BLOCKS.getValues()) {
            if (block.getRegistryName().getNamespace().equalsIgnoreCase(WeepingAngels.MODID) && block != WAObjects.Blocks.KONTRON_ORE.get() && block != NewTardisBlocks.EXTERIOR_2005.get()) {
                this.generateSelfTable(block, cache, path);
            }
        }
    }

    @Override
    public String getName() {
        return "Loot Tables";
    }

    public void generateSelfTable(Block block, DirectoryCache cache, Path base) throws IOException {
        this.generateTable(cache, getPath(base, block.getRegistryName()), () -> this.createBlockDropSelf(block));
    }

    public void generateTable(DirectoryCache cache, Path path, Supplier<JsonElement> element) throws IOException {
        IDataProvider.save(GSON, cache, element.get(), path);
    }

    public JsonElement createBlockDropGuarantied(Block block, ResourceLocation drop) {
        JsonObject root = new JsonObject();
        root.add("type", new JsonPrimitive("minecraft:block"));

        JsonArray pool = new JsonArray();

        JsonObject first = new JsonObject();

        first.add("name", new JsonPrimitive(block.getRegistryName().toString()));
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
        return this.createBlockDropGuarantied(block, block.getRegistryName());
    }

}