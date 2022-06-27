package craig.software.mc.angels.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import craig.software.mc.angels.WeepingAngels;
import craig.software.mc.angels.client.renders.Donator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static craig.software.mc.angels.utils.PlayerUtil.getResponse;

public class DonationUtil {

    public static ArrayList<Donator> getDonators() {
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

}
