package me.suff.mc.angels.client.renders;

import com.google.gson.JsonObject;
import me.suff.mc.angels.common.entities.AngelType;

import java.util.UUID;

public class Donator {

    private final String name;
    public UUID uuid;
    public String variant;
    public String wings;
    public boolean perked;

    public Donator(JsonObject jsonObject) {
        try {
            this.uuid = UUID.fromString(jsonObject.get("uuid").getAsString());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        this.variant = jsonObject.get("variant").getAsString();
        this.wings = jsonObject.get("wings").getAsString();
        this.perked = jsonObject.get("perked").getAsBoolean();
        this.name = jsonObject.get("name").getAsString();
    }

    public String getName() {
        return name;
    }

    public AngelType getPureWings() {
        for (AngelType value : AngelType.values()) {
            if (wings.equalsIgnoreCase(value.name())) {
                return value;
            }
        }
        return AngelType.DISASTER_MC;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getVariant() {
        return variant;
    }

    public String getWings() {
        return wings;
    }

    public boolean isPerked() {
        return perked;
    }
}
