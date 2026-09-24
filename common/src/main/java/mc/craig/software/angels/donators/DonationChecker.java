package mc.craig.software.angels.donators;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mc.craig.software.angels.WAConfiguration;
import mc.craig.software.angels.WeepingAngels;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.Player;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class DonationChecker {

    private static final int TIMEOUT_MS = 10_000;

    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor(runnable -> {
        Thread thread = new Thread(runnable, "Weeping Angels Donator Lookup");
        thread.setDaemon(true);
        return thread;
    });

    private static volatile List<Donator> modDonators = List.of();

    private static final AtomicBoolean LOOKUP_IN_FLIGHT = new AtomicBoolean(false);
    private static volatile long lastScheduledMinute = -1;

    public static List<Donator> getModDonators() {
        return modDonators;
    }

    public static void update() {
        if (!isLookupEnabled()) {
            return;
        }

        if (!LOOKUP_IN_FLIGHT.compareAndSet(false, true)) {
            return;
        }

        EXECUTOR.submit(() -> {
            try {
                List<Donator> fetched = getRemoteDonators();
                if (fetched != null) {
                    modDonators = List.copyOf(fetched);
                    WeepingAngels.LOGGER.debug("Updated Donators: {}", modDonators);
                }
            } catch (Exception e) {
                WeepingAngels.LOGGER.warn("Failed to update donators; keeping cached data.", e);
            } finally {
                LOOKUP_IN_FLIGHT.set(false);
            }
        });
    }


    private static boolean isLookupEnabled() {
        try {
            return WAConfiguration.CLIENT.donatorLookup.get();
        } catch (IllegalStateException e) {
            return true;
        }
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

    public static void checkForUpdate() {
        Calendar rightNow = Calendar.getInstance();
        int minutes = rightNow.get(Calendar.MINUTE);
        if (minutes != 0 && minutes != 39) {
            return;
        }

        long minuteKey = System.currentTimeMillis() / 60_000L;
        if (minuteKey != lastScheduledMinute) {
            lastScheduledMinute = minuteKey;
            update();
        }
    }

    public static List<Donator> getRemoteDonators() {
        WeepingAngels.LOGGER.info("Looking up donators!");

        JsonObject result;
        try {
            result = getResponse(new URL("https://api.jeryn.dev/mc/vips"));
        } catch (Exception e) {
            WeepingAngels.LOGGER.warn("Could not retrieve donators; using cached data.");
            return null;
        }

        if (result == null || !result.has("data") || !result.get("data").isJsonArray()) {
            WeepingAngels.LOGGER.warn("No donator data available from server.");
            return null;
        }

        List<Donator> donators = new ArrayList<>();
        JsonArray vips = result.getAsJsonArray("data");
        for (JsonElement vip : vips) {
            if (vip.isJsonObject()) {
                donators.add(new Donator(vip.getAsJsonObject()));
            }
        }
        return donators;
    }


    public static JsonObject getResponse(URL url) throws IOException {
        HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();
        uc.setConnectTimeout(TIMEOUT_MS);
        uc.setReadTimeout(TIMEOUT_MS);
        uc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream(), StandardCharsets.UTF_8))) {
            return GsonHelper.parse(br);
        } finally {
            uc.disconnect();
        }
    }

    public static boolean isXmas() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1 == 12 && calendar.get(Calendar.DATE) >= 24 && calendar.get(Calendar.DATE) <= 26;
    }
}
