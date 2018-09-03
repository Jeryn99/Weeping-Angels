package me.sub.angels.utils;

import com.google.gson.*;
import me.sub.angels.WeepingAngels;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = WeepingAngels.MODID)
public class WorldJsonUtils {

    public static WorldJsonUtils INSTANCE = new WorldJsonUtils();

    public void generateJson(World world, int chunkX, int chunkY, int chunkZ, String name) {
        int[][][] blockArray = new int[16][16][16];
        BlockPos origin = new BlockPos(chunkX << 4, chunkY << 4, chunkZ << 4);
        ArrayList<JsonBlock> states = new ArrayList<>();
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                for (int z = 0; z < 16; z++) {
                    IBlockState state = world.getBlockState(origin.add(new BlockPos(x, y, z)));
                    JsonBlock block = new JsonBlock(state.getBlock().getRegistryName().getResourceDomain(), state.getBlock().getRegistryName().getResourcePath(), state.getBlock().getMetaFromState(state));
                    if (!states.contains(block)) states.add(block);
                    blockArray[x][y][z] = states.indexOf(block);
                }
            }
        }

        File dir = new File(FMLCommonHandler.instance().getSavesDirectory().getParentFile(), "json-structures");
        dir.mkdirs();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            File jsonFile = new File(dir, name + ".json");
            jsonFile.createNewFile();
            PrintWriter writer = new PrintWriter(new FileWriter(jsonFile));
            writer.write(gson.toJson(new JsonChunk(states, blockArray)));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fromJson(World world, int chunkX, int chunkY, int chunkZ, String name, boolean loadFromResources) {
        try {
            BlockPos origin = new BlockPos(chunkX << 4, chunkY << 4, chunkZ << 4);
            ArrayList<JsonBlock> states = new ArrayList<>();

            JsonParser parser = new JsonParser();
            JsonObject object;
            if (!loadFromResources) {
                File dir = new File(FMLCommonHandler.instance().getSavesDirectory().getParentFile(), "json-structures");
                dir.mkdirs();
                File jsonFile = new File(dir, name + ".json");
                object = parser.parse(new FileReader(jsonFile)).getAsJsonObject();
            } else {
                InputStream stream = getClass().getClassLoader().getResourceAsStream("assets/" + WeepingAngels.MODID + "/chunks/" + name + ".json");
                object = parser.parse(new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))).getAsJsonObject();
            }

            for (JsonElement blocks : object.get("blocks").getAsJsonArray()) {
                JsonObject resourceElement = blocks.getAsJsonObject();
                JsonBlock block = new JsonBlock(resourceElement.get("domain").toString(), resourceElement.get("path").toString(), resourceElement.get("meta").getAsInt());
                if (!states.contains(block)) states.add(block);
            }

            JsonArray array = object.getAsJsonArray("blockArray");
            for (int x = 0; x < 16; x++) {
                JsonArray array2 = array.get(x).getAsJsonArray();
                for (int y = 0; y < 16; y++) {
                    JsonArray array3 = array2.get(y).getAsJsonArray();
                    for (int z = 0; z < 16; z++) {
                        JsonBlock b = states.get(array3.get(z).getAsInt());
                        String s = (b.domain + ":" + b.path).replace("\"", "");
                        BlockPos pos = origin.add(new BlockPos(x, y, z));
                        Block block = Block.getBlockFromName(s);
                        IBlockState state = block.getStateFromMeta(b.meta);
                        world.setBlockState(pos, state, 3);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    public static class JsonChunk {
        private ArrayList<JsonBlock> blocks;
        private int[][][] blockArray;

        JsonChunk(ArrayList<JsonBlock> blocks, int[][][] blockArray) {
            this.blocks = blocks;
            this.blockArray = blockArray;
        }
    }

    @SuppressWarnings("unused")
    public static class JsonBlock {

        private String domain, path;
        private int meta;

        JsonBlock(String domain, String path, int meta) {
            this.domain = domain;
            this.path = path;
            this.meta = meta;
        }

        @Override
        public boolean equals(Object obj) {
            JsonBlock block = (JsonBlock) obj;
            return this.domain.equals(block.domain) && this.path.equals(block.path) && this.meta == block.meta;
        }
    }

    public static class GenerateJsonCommand extends CommandBase {

        @Override
        public String getName() {
            return "tojson";
        }

        @Override
        public String getUsage(ICommandSender sender) {
            return "/tojson <name>";
        }

        @Override
        public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
            if (!(sender.getCommandSenderEntity() instanceof EntityPlayer))
                throw new CommandException("Can only use this command as a player", (Object[]) args);
            if (args.length == 0) throw new CommandException("No arguments given", (Object[]) args);
            EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();

            INSTANCE.generateJson(player.world, (int) player.posX >> 4, (int) player.posY >> 4, (int) player.posZ >> 4, args[0]);
        }

    }

    public static class BuildJsonCommand extends CommandBase {

        @Override
        public String getName() {
            return "buildjson";
        }

        @Override
        public String getUsage(ICommandSender sender) {
            return "/buildjson <name>";
        }

        @Override
        public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
            if (!(sender.getCommandSenderEntity() instanceof EntityPlayer))
                throw new CommandException("Can only use regeneration debug commands as player", (Object[]) args);
            if (args.length == 0) throw new CommandException("No arguments given", (Object[]) args);
            EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();

            INSTANCE.fromJson(player.world, (int) player.posX >> 4, (int) player.posY >> 4, (int) player.posZ >> 4, args[0], false);
        }

    }
	
}
