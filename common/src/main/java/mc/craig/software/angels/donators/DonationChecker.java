package mc.craig.software.angels.donators;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mc.craig.software.angels.WeepingAngels;
import net.minecraft.client.Minecraft;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.Player;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DonationChecker {

    protected static ArrayList<Donator> modDonators = new ArrayList<>();


    public static ArrayList<Donator> getModDonators() {
        return modDonators;
    }

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void update() {
        executor.submit(() -> {
            modDonators.addAll(DonationChecker.getRemoteDonators());
            WeepingAngels.LOGGER.debug("Updated Donators: {}", modDonators);
        });
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

    public static void checkForUpdate(boolean force) {
        Calendar rightNow = Calendar.getInstance();
        int minutes = rightNow.get(Calendar.MINUTE);
        int second = rightNow.get(Calendar.SECOND);
        if (force || (minutes == 0 || minutes == 40) && second == 1) {
            new Thread(DONATOR_RUNNABLE).run();
        }
    }

    public static ArrayList<Donator> getRemoteDonators() {
        ArrayList<Donator> donators = new ArrayList<>();
        JsonObject result;
        WeepingAngels.LOGGER.info("Looking up donators!");

        try {
            result = getResponse(new URL("https://api.jeryn.dev/mc/vips"));
        } catch (IOException e) {
            WeepingAngels.LOGGER.warn("Could not retrieve donators; using cached data.");
            return getModDonators();
        }

        if (result != null) {
            JsonArray vips = result.getAsJsonArray("data");
            for (JsonElement vip : vips) {
                Donator donator = new Donator(vip.getAsJsonObject());
                donators.add(donator);
            }
        } else {
            WeepingAngels.LOGGER.warn("No donator data available from server.");
        }

        return donators;
    }


    public static JsonObject getResponse(URL url) throws IOException {
        HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();
        uc.connect();
        uc.setConnectTimeout(10);
        uc = (HttpsURLConnection) url.openConnection();
        uc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36");
        InputStream inputStream = uc.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        JsonObject finalData = GsonHelper.parse(br);
        uc.disconnect();
        return finalData;
    }

    public static boolean isXmas() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1 == 12 && calendar.get(Calendar.DATE) >= 24 && calendar.get(Calendar.DATE) <= 26;
    }
}