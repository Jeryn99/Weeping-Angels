package me.suff.mc.angels.client.renders;

import com.google.gson.JsonObject;
import me.suff.mc.angels.common.entities.AngelType;

public class Donator {

    public String uuid;
    public String variant;
    public String wings;
    public boolean perked;

    public Donator(JsonObject jsonObject) {
        this.uuid = jsonObject.get("uuid").getAsString();
        this.variant = jsonObject.get("variant").getAsString();
        this.wings = jsonObject.get("wings").getAsString();
        this.perked = jsonObject.get("perked").getAsBoolean();
    }

    public AngelType getPureWings() {
        for (AngelType value : AngelType.values()) {
            if (wings.equalsIgnoreCase(value.name())) {
                return value;
            }
        }
        return AngelType.DISASTER_MC;
    }

    public String getUuid() {
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
