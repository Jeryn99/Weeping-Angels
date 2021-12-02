package me.suff.mc.angels.data;

import com.google.gson.*;
import me.suff.mc.angels.common.entities.AngelType;
import me.suff.mc.angels.common.variants.AngelTypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.util.GsonHelper;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.util.function.Supplier;

import static org.apache.http.HttpHeaders.USER_AGENT;

public class DonatorsQuickGen extends LootTableProvider {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final DataGenerator generator;


    public DonatorsQuickGen(DataGenerator gen) {
        super(gen);
        this.generator = gen;
    }

    public static Path getPath(Path path) {
        return path.resolve("data/vips.json");
    }

    @Override
    public void run(HashCache cache) {
        Path path = this.generator.getOutputFolder();

        try {
            this.save(cache, getPath(path), this::people);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "Donators";
    }

    public void save(HashCache cache, Path path, Supplier<JsonElement> element) throws IOException {
        DataProvider.save(GSON, cache, element.get(), path);
    }

    public JsonElement people() {
        JsonObject root = new JsonObject();

        String[] dev = new String[]{
                "9c149673-d6f7-46ee-b04c-005be737d0d9",
                "089e60f8-ce81-4fa1-bade-44c5f7d18f2f",
                "bc8b891e-5c25-4c9f-ae61-cdfb270f1cc1",
                "96511168-1bb3-4ff0-a894-271e42606a39",
                "6e17cac4-6d28-48ca-a112-61f208fbdcd8",
                "bd049f17-7fdd-42aa-bd19-81a60d6b526b",
                "a18f8b9f-fd9a-4229-a3bb-878d5709b118",
                "7798f19a-f757-4b23-9e48-fb449384a0ce",
                "94644c62-f190-4f18-a69a-ad36e7425280",
                "5c615772-0b5c-4fe4-aedd-15e2871ff95f",
                "fa396f29-9e23-479b-93a5-43e0780f1453",
                "bb007a97-bda9-4019-9898-f833d8c42ff7",
                "ba21f64b-35e3-4b4f-b04c-9ceb814ad533",
                "677cf2e9-75c3-4fb4-b7b6-30ddcbee1c01",
                "f63752bd-f2ff-4124-8b7c-8aaf1215902a",
                "21088243-2e67-44b5-83bd-da5a67968ff8",
                "45062bee-d6b4-4778-bbb3-0a07acdf54ea",
                "3f4f2acd-9427-4917-81c3-7b8a3031fffb"
        };
        String[] donators = new String[]{
                "a0b89882-8509-42b2-921b-14facddfb5dc",
                "d560219f-70ab-4224-aa77-393042443065",
                "0926547b-228c-4106-b81b-f5a2fa5a4ea9",
                "486f67c5-2582-476a-bed9-3d30348773f7",
                "4ee8bee2-58bb-4772-8663-b586404b14de",
                "7113a868-ecaa-445a-933e-273523e77051"
        };

        JsonArray donators_json = new JsonArray();
        JsonArray devs = new JsonArray();


        for (String person : dev) {
            createPerson(devs, person);
        }

        for (String person : donators) {
            createPerson(donators_json, person);
        }

        root.add("devs", devs);
        root.add("donators", donators_json);
        return root;
    }



    private void createPerson(JsonArray entries, String person) {
        JsonObject entry = new JsonObject();
        entry.add("uuid", new JsonPrimitive(person));
        entry.add("wings", new JsonPrimitive(AngelType.DISASTER_MC.name()));
        entry.add("variant", new JsonPrimitive(String.valueOf(AngelTypes.NORMAL.get().getRegistryName())));
        entry.add("perked", new JsonPrimitive("false"));
        String name = get(person);
        entry.add("name", new JsonPrimitive(name));
        System.out.println(person + " is " + name);
        entries.add(entry);
    }

    private String get(String s) {
        try {
            return getMcUser(new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "name";
    }

    public static String getMcUser(URL url) throws IOException {
        HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();
        uc.connect();
        uc = (HttpsURLConnection) url.openConnection();
        uc.addRequestProperty("User-Agent", USER_AGENT);
        InputStream inputStream = uc.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        return GsonHelper.parse(br).get("name").getAsString();
    }

}