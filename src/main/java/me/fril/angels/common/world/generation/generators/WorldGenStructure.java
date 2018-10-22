package me.fril.angels.common.world.generation.generators;

import me.fril.angels.WeepingAngels;
import me.fril.angels.utils.IStructure;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class WorldGenStructure extends WorldGenerator implements IStructure {

    public static String structureName;

    public WorldGenStructure(String name) {
        this.structureName = name;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        this.generateStructure(worldIn,position);
        return true;
    }

    public static void generateStructure(World world, BlockPos pos){
        MinecraftServer mcServer = world.getMinecraftServer();
        TemplateManager manager = WORLD_SERVER.getStructureTemplateManager();
        ResourceLocation location = new ResourceLocation(WeepingAngels.MODID, structureName);
        Template template = manager.get(mcServer,location);

        if (template != null){
            IBlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos,state,state,3);
            template.addBlocksToWorldChunk(world,pos,SETTINGS);
        }
    }

    @Override
    public String toString() {
        return "Structure : "+ structureName;
    }
}
