package mc.craig.software.angels.donators;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mc.craig.software.angels.WeepingAngels;
import net.minecraft.client.Minecraft;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class DonationChecker {

    protected static HashSet<Donator> modDonators = new HashSet<>();


    public static HashSet<Donator> getModDonators() {
        return modDonators;
    }

    public static void update() {
        modDonators.addAll(DonationChecker.getRemoteDonators());
        WeepingAngels.LOGGER.debug("Updated Donators: " + modDonators);
    }

    public static Optional<Donator> getDonatorData(Player player) {
        String playersUuid = player.getStringUUID();

        for (Donator person : modDonators) {
            if (playersUuid.equals(person.getUuid())) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    public static Runnable DONATOR_RUNNABLE = DonationChecker::update;

    public static void checkForUpdate(TickEvent.ClientTickEvent event) {
        Calendar rightNow = Calendar.getInstance();
        int minutes = rightNow.get(Calendar.MINUTE);
        int second = rightNow.get(Calendar.SECOND);
        if ((minutes == 0 || minutes == 39) && second == 1) {
            Minecraft.getInstance().submitAsync(DONATOR_RUNNABLE);
        }
    }

    public static ArrayList<Donator> getRemoteDonators() {
        ArrayList<Donator> donators = new ArrayList<>();
        JsonObject result = null;
        try {
            result = getResponse(new URL("https://mc-api.craig.software/vips"));
        } catch (IOException e) {
            WeepingAngels.LOGGER.info("Issue retrieving Donators! Server may be down or overwhelmed");
            e.printStackTrace();
        }

        JsonArray vips = result.getAsJsonArray("data");
        for (JsonElement vip : vips) {
            Donator donator = new Donator(vip.getAsJsonObject());
            donators.add(donator);
        }

        return donators;
    }

    public static JsonObject getResponse(URL url) throws IOException {
        HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();
        uc.connect();
        uc = (HttpsURLConnection) url.openConnection();
        uc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36");
        InputStream inputStream = uc.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        JsonObject finalData = GsonHelper.parse(br);
        uc.disconnect();
        return finalData;
    }

}