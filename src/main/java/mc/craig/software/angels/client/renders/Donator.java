package mc.craig.software.angels.client.renders;

import com.google.gson.JsonObject;
import mc.craig.software.angels.common.entities.AngelType;

import java.util.UUID;

public class Donator {

    private String mcName;
    private String uuid;
    private String vipType;
    private boolean wingsPerked;
    private String wingsVariant;
    private String wingsModel;

    public Donator(String mcName, String uuid, String vipType, boolean wingsPerked, String wingsVariant, String wingsModel) {
        this.mcName = mcName;
        this.uuid = uuid;
        this.vipType = vipType;
        this.wingsPerked = wingsPerked;
        this.wingsVariant = wingsVariant;
        this.wingsModel = wingsModel;
    }

    public String getName() {
        return mcName;
    }

    public AngelType getPureWings() {
        for (AngelType value : AngelType.values()) {
            if (wingsVariant.equalsIgnoreCase(value.name())) {
                return value;
            }
        }
        return AngelType.DISASTER_MC;
    }

    public UUID getUuid() {
        return UUID.fromString(uuid);
    }

    public String getVariant() {
        return wingsVariant;
    }

    public String getWings() {
        return wingsModel;
    }

    public boolean isPerked() {
        return wingsPerked;
    }
}
