package mc.craig.software.angels.fabric.util;

import com.google.common.collect.Lists;
import mc.craig.software.angels.WAConfiguration;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.server.commands.TeleportCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

import java.util.ArrayList;

public class TeleporterImpl {

    public static ServerLevel getRandomDimension(RandomSource rand, ServerLevel serverLevel) {
        Iterable<ServerLevel> dimensions = serverLevel.getServer().getAllLevels();
        ArrayList<ServerLevel> allowedDimensions = Lists.newArrayList(dimensions);

        for (ServerLevel dimension : dimensions) {
            for (String dimName : WAConfiguration.CONFIG.bannedDimensions.get()) {
                if (dimension.dimension().location().toString().equalsIgnoreCase(dimName)) {
                    allowedDimensions.remove(dimension);
                }
            }
        }
        allowedDimensions.remove(serverLevel.getServer().getLevel(Level.NETHER));
        return allowedDimensions.get(rand.nextInt(allowedDimensions.size()));
    }

}
