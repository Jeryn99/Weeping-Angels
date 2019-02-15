package me.suff.angels.utils;

import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.world.WorldServer;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public interface IStructure {
	WorldServer WORLD_SERVER = ServerLifecycleHooks.getCurrentServer().getWorld(DimensionType.OVERWORLD);
	PlacementSettings SETTINGS = (new PlacementSettings()).setChunk(null).setIgnoreEntities(false).setIgnoreStructureBlock(false).setMirror(Mirror.NONE).setRotation(Rotation.NONE);
}
