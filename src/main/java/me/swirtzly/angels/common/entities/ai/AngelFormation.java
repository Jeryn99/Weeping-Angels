package me.swirtzly.angels.common.entities.ai;

import me.swirtzly.angels.common.entities.EntityQuantumLockBase;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;

/**
 * Singleton - Spectre0987
 */
public class AngelFormation {

    private ArrayList<Vec3d> offsets = new ArrayList<Vec3d>();

    public AngelFormation(ArrayList<Vec3d> offsets) {
        offsets.add(0, new Vec3d(0, 0, 0));
        this.offsets = offsets;
    }

    public Vec3d getPlace(EntityQuantumLockBase commander, EntityQuantumLockBase drone) {
        int index = 0;
        for (EntityQuantumLockBase dal : commander.getFollowers()) {
            if (dal.getUniqueID().equals(drone.getUniqueID())) {
                if (offsets.size() > index)
                    return offsets.get(index);
            }
            ++index;
        }
        return null;
    }

}
