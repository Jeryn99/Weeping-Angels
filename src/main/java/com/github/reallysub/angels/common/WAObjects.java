package com.github.reallysub.angels.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.github.reallysub.angels.client.models.ModelAngelEd;
import com.github.reallysub.angels.client.render.entity.RenderAngel;
import com.github.reallysub.angels.client.render.entity.RenderAngelPainting;
import com.github.reallysub.angels.client.render.tiles.RenderSnowArm;
import com.github.reallysub.angels.common.blocks.BlockSnowArm;
import com.github.reallysub.angels.common.entities.EntityAngel;
import com.github.reallysub.angels.common.entities.EntityPainting2;
import com.github.reallysub.angels.common.items.ItemHanging;
import com.github.reallysub.angels.common.tiles.TileSnowArm;
import com.github.reallysub.angels.main.WeepingAngels;
import com.github.reallysub.angels.main.config.Config;
import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class WAObjects {
	
	private static List<SoundEvent> SOUNDS = new ArrayList<>();
	
	public static SoundEvent angelSeen = addSound("angel_seen");
	public static SoundEvent stone_scrap = addSound("stone_scrap");
	public static SoundEvent child_run = addSound("child_run");
	public static SoundEvent laughing_child = addSound("laughing_child");
	public static SoundEvent light_break = addSound("light_break");
	
	public static DamageSource ANGEL = new WADamageSource("was sent back in time by a Angel!");
	public static DamageSource STONE = new WADamageSource("broke their bones by punching stone...");
	public static DamageSource ANGEL_NECK_SNAP = new WADamageSource("has their neck snapped by a Angel!");
	
	private static SoundEvent addSound(String soundName) {
		ResourceLocation sound = new ResourceLocation(WeepingAngels.MODID + ":" + soundName);
		SoundEvent s = new SoundEvent(sound).setRegistryName(soundName);
		SOUNDS.add(s);
		return s;
	}
	
	public static void setUpSpawns() {
		List<Biome> allBiomes = ForgeRegistries.BIOMES.getValues();
		List<Biome> spawn = Lists.newArrayList();
		spawn.addAll(allBiomes);
		Iterator<Biome> iterator = spawn.iterator();
		while (iterator.hasNext()) {
			Biome biome = iterator.next();
			if (biome != null) {
				EntityRegistry.addSpawn(EntityAngel.class, Config.spawnProbability, Config.minimumSpawn, Config.maximumSpawn, EnumCreatureType.CREATURE, biome);
			}
		}
	}
	
	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> e) {
		SoundEvent[] sounds = SOUNDS.toArray(new SoundEvent[SOUNDS.size()]);
		e.getRegistry().registerAll(sounds);
	}
	

	public static Block angelArm = new BlockSnowArm();
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		GameRegistry.registerTileEntity(TileSnowArm.class, ":snowarm");
		event.getRegistry().register(angelArm);
	}
	
	public static void initEntities() {
	
		EntityRegistry.registerModEntity(EntityAngel.class, "angel", 0, WeepingAngels.instance, 80, 3, false);
	
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			setUpRenders();
		}
	}
	
	public static Item angelPainting = createItem(new ItemHanging(), "angel_painting");
	public static Item angelArmItem = createItem(new ItemBlock(angelArm), "arm").setCreativeTab(CreativeTabs.DECORATIONS);;
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		Item[] items = { angelPainting, angelArmItem };
		event.getRegistry().registerAll(items);
	}
	
	@SideOnly(Side.CLIENT)
	private static void setUpRenders() {
		RenderManager manager = Minecraft.getMinecraft().getRenderManager();
		RenderingRegistry.registerEntityRenderingHandler(EntityAngel.class,new RenderAngel(manager, new ModelAngelEd()));
		RenderingRegistry.registerEntityRenderingHandler(EntityPainting2.class, new RenderAngelPainting(manager));
		ClientRegistry.bindTileEntitySpecialRenderer(TileSnowArm.class, new RenderSnowArm());
	}
	
	private static Item createItem(Item item, String name) {
		item.setRegistryName(WeepingAngels.MODID, name);
		item.setUnlocalizedName(name);
		item.setCreativeTab(CreativeTabs.DECORATIONS);
		return item;
	}
	
	public static class WADamageSource extends DamageSource {
		
		private String message = "";
		
		public WADamageSource(String damageTypeIn) {
			super(damageTypeIn);
			this.message = damageTypeIn;
		}
		
		@Override
		public ITextComponent getDeathMessage(EntityLivingBase entity) {
			return new TextComponentString(entity.getName() + " " + this.message);
		}
	}
}
