package me.suff.mc.angels.utils;

import com.google.gson.JsonObject;
import me.suff.mc.angels.common.entities.AngelType;

public class Donator {

    private String cate;
    private String uuid;
    private String variant;
    private String wings;
    private String name;
    private boolean perked;

    public Donator(JsonObject jsonObject) {
        this.uuid = jsonObject.get("uuid").getAsString();
        this.variant = jsonObject.get("variant").getAsString();
        this.wings = jsonObject.get("wings").getAsString();
        this.perked = jsonObject.get("perked").getAsBoolean();
        this.name = jsonObject.get("name").getAsString();
    }

    public Donator(JsonObject dev, String category) {
        this(dev);
        this.cate = category;
    }

    public String getCate() {
        return cate;
    }

    public AngelType getPureWings() {
        for (AngelType value : AngelType.values()) {
            if (wings.equalsIgnoreCase(value.name())) {
                return value;
            }
        }
        return AngelType.DISASTER_MC;
    }

    public String getName() {
        return name;
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

    public void setWings(String wings) {
        this.wings = wings;
    }

    public boolean isPerked() {
        return perked;
    }
}
