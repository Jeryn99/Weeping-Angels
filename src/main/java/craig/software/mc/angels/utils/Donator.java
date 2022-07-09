package craig.software.mc.angels.utils;

import com.google.gson.JsonObject;
import craig.software.mc.angels.common.entities.AngelType;
import java.util.Objects;
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

        JsonObject wingData = jsonObject.getAsJsonObject("wings");
        this.variant = wingData.get("variant").getAsString();
        this.wings = wingData.get("model").getAsString();
        this.perked = wingData.get("perked").getAsBoolean();
        this.name = jsonObject.get("mc_name").getAsString();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donator donator = (Donator) o;
        return perked == donator.perked && name.equals(donator.name) && uuid.equals(donator.uuid) && variant.equals(donator.variant) && wings.equals(donator.wings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, uuid, variant, wings, perked);
    }

    @Override
    public String toString() {
        return "Donator{" +
                "name='" + name + '\'' +
                ", uuid=" + uuid +
                ", variant='" + variant + '\'' +
                ", wings='" + wings + '\'' +
                ", perked=" + perked +
                '}';
    }

    public void setWings(String name) {
        this.wings = name;
    }
}