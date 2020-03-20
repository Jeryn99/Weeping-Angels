package me.swirtzly.angels.common.events;

import me.swirtzly.angels.WeepingAngels;
import me.swirtzly.angels.common.WAObjects;
import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import me.swirtzly.angels.config.WAConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.Random;

@Mod.EventBusSubscriber(modid = WeepingAngels.MODID)
public class EventHandler {

    private static WorldGenMinable genCrystal = new WorldGenMinable(WAObjects.Blocks.KONTRON_ORE.getDefaultState(), 3);

    /**
     * Update checker thing, tells the player that the mods out of date if they're on a old build
     */
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {
        EntityPlayer player = e.player;
        if (!player.world.isRemote && WAConfig.angels.enableUpdateChecker) {
            ForgeVersion.CheckResult version = ForgeVersion.getResult(Loader.instance().activeModContainer());
            if (version.status.equals(ForgeVersion.Status.OUTDATED)) {
                TextComponentString url = new TextComponentString(TextFormatting.AQUA + TextFormatting.BOLD.toString() + "UPDATE");
                url.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://minecraft.curseforge.com/projects/weeping-angels-mod"));
                url.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("Open URL")));

                player.sendMessage(new TextComponentString(TextFormatting.GOLD + "[Weeping Angels] : ").appendSibling(url));
                String changes = version.changes.get(version.target);
                player.sendMessage(new TextComponentString(TextFormatting.GOLD + "Changes: " + TextFormatting.BLUE + changes));
            }
        }
    }

    @SubscribeEvent
    public static void onBiomeDecorated(DecorateBiomeEvent.Pre e) {
        World world = e.getWorld();
        Random rand = e.getRand();
        if (world.getBiome(e.getPos()).isSnowyBiome()) {
            if (rand.nextInt(5) <= 3) {
                generateArms(world, e.getPos());
            }
        }
        if (!WAConfig.worldGen.genOres) return;
        int blockY = rand.nextInt(64);
        int blockX = e.getChunkPos().x * 16 + (rand.nextInt(16) + 8);
        int blockZ = e.getChunkPos().z * 16 + (rand.nextInt(16) + 8);
        BlockPos pos = new BlockPos(blockX, blockY, blockZ);

        if (world.provider.getDimension() == 0 && rand.nextBoolean() && !world.getBiome(e.getPos()).isSnowyBiome()) {
            if (blockY > 3 && blockY < 60) {
                genCrystal.generate(world, rand, pos);
            }
        }
    }

    private static void generateArms(World world, BlockPos position) {
        if (!WAConfig.worldGen.arms) return;
        BlockPos pos = new BlockPos(position.add(new BlockPos(8, 0, 8)));
        if ((!world.provider.isNether() || pos.getY() < 255) && world.getBiome(position).isSnowyBiome()) {
            if (world.getBlockState(pos).getBlock() == Blocks.SNOW || world.getBlockState(pos).getBlock() == Blocks.SNOW_LAYER)
                world.setBlockState(pos, WAObjects.Blocks.ARM.getDefaultState(), 1);
        }
    }

    @SubscribeEvent
    public static void cancelDamage(LivingAttackEvent e) {
        if (!WAConfig.angels.pickaxeOnly) return;
        if (WAConfig.angels.hardcoreMode) { e.setCanceled(true); return;}
        
        Entity source = e.getSource().getTrueSource();
        DamageSource dSource = e.getSource();
        
        if (source instanceof EntityLivingBase) {
            EntityLivingBase attacker = (EntityLivingBase) source;
            EntityLivingBase victim = e.getEntityLiving();
            
            if (victim instanceof EntityWeepingAngel) {
                ItemStack item = attacker.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
                boolean isPic = item.getItem() instanceof ItemPickaxe || item.getItem().getRegistryName().toString().contains("pickaxe") || item.canHarvestBlock(Blocks.OBSIDIAN.getDefaultState());
                Item pick = item.getItem();
                if (attacker instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) attacker;
                    if (!player.getCooldownTracker().hasCooldown(pick)) {
                        if (WAConfig.angels.pickaxeCooldown) {
                            player.getCooldownTracker().setCooldown(pick, WAConfig.angels.pickaxeCooldownTicks);
                        }
                    }
                }

                e.setCanceled(!isPic);
                if (!isPic && !dSource.isProjectile()) {
                    attacker.attackEntityFrom(WAObjects.STONE, 2F);
                    victim.playSound(SoundEvents.BLOCK_STONE_BREAK, 1.0F, 1.0F);
                }
                else if (pick != Items.DIAMOND_PICKAXE && victim.world.getDifficulty() == EnumDifficulty.HARD) {
                        e.setCanceled(true);
                }
                else if (!(source instanceof Entity)) {
                        e.setCanceled(true);
                    }
                }

        }
        }

    @SubscribeEvent
    public static void onLootTablesLoaded(LootTableLoadEvent event) {
        if (event.getName().getNamespace().contains("chests")) {
            final LootPool pool2 = event.getTable().getPool("pool2");
            if (pool2 != null) {
                pool2.addEntry(new LootEntryItem(WAObjects.Items.CHRONODYNE_GENERATOR, 10, 0, new LootFunction[]{new SetCount(new LootCondition[0], new RandomValueRange(1, 5))}, new LootCondition[0], "weeping-angels:generators"));
            }
        }
    }

    @SubscribeEvent
    public static void onSpawn(LivingSpawnEvent.CheckSpawn e) {
        if (e.getEntity() instanceof EntityWeepingAngel) {
            e.setResult(Event.Result.DENY);
            for (int i : WAConfig.spawn.dimensionWhitelist) {
                if (i == e.getWorld().provider.getDimension()) {
                    e.setResult(Event.Result.DEFAULT);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onMissingMappingItem(RegistryEvent.MissingMappings<Item> e) {
        for (RegistryEvent.MissingMappings.Mapping<Item> map : e.getAllMappings()) {
            if (map.key.toString().equals("weeping-angels:unlit_torch")) {
                WeepingAngels.LOGGER.warn("This world contains a removed item, removing: " + map.key);
                map.ignore();
            }
        }
    }

}
