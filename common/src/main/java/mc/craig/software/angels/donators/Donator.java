package mc.craig.software.angels.donators;

import com.google.gson.JsonObject;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.player.Player;

import java.util.Objects;

public class Donator {

    private String name;
    public String uuid;
    public String variant;
    public String wings;
    public boolean perked;

    public int animationTime = 0;
    public AnimationState openState = new AnimationState(), closeState = new AnimationState();

    public Donator(JsonObject jsonObject) {
        if (jsonObject.has("uuid") && !jsonObject.get("uuid").isJsonNull()) {
            this.uuid = jsonObject.get("uuid").getAsString();
        } else {
            this.uuid = "";
        }

        JsonObject wingData = jsonObject.getAsJsonObject("wings");

        if (wingData != null) {
            if (wingData.has("variant") && !wingData.get("variant").isJsonNull()) {
                this.variant = wingData.get("variant").getAsString();
            } else {
                this.variant = "";
            }

            if (wingData.has("model") && !wingData.get("model").isJsonNull()) {
                this.wings = wingData.get("model").getAsString();
            } else {
                this.wings = "";
            }

            if (wingData.has("perked") && !wingData.get("perked").isJsonNull()) {
                this.perked = wingData.get("perked").getAsBoolean();
            } else {
                this.perked = false;
            }
        } else {
            this.variant = "";
            this.wings = "";
            this.perked = false;
        }

        if (jsonObject.has("mc_name") && !jsonObject.get("mc_name").isJsonNull()) {
            this.name = jsonObject.get("mc_name").getAsString();
        } else {
            this.name = "";
        }
    }

    public String getName() {
        return name;
    }

    public void tick(Player player) {
        animationTime++;

        if (player.isFallFlying()) {
            closeState.stop();
            if (!openState.isStarted()) {
                openState.start(player.tickCount);
            }
        }

        if (player.onGround()) {
            openState.stop();
            if (!closeState.isStarted()) {
                closeState.start(player.tickCount);
            }
        }
    }

    public int getAnimationTime() {
        return animationTime;
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
}