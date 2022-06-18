package craig.software.mc.angels.common.level;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.common.world.ModifiableStructureInfo;
import net.minecraftforge.common.world.StructureModifier;

public record StructureSpawnModifer(HolderSet<Biome> biomes, MobSpawnSettings.SpawnerData spawn) implements StructureModifier {

    @Override
    public void modify(Holder<Structure> structure, Phase phase, ModifiableStructureInfo.StructureInfo.Builder builder) {
      //TODO
         /*  if (phase == StructureModifier.Phase.ADD && this.biomes.contains(biome)) {
            builder.getMobSpawnSettings().addSpawn(this.spawn.type.getCategory(), this.spawn);
        }
        */
    }

    @Override
    public Codec<? extends StructureModifier> codec() {
        return null;
    }
}
