package com.github.reallysub.angels.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.github.reallysub.angels.WeepingAngels;
import com.github.reallysub.angels.client.RenderAngel;
import com.github.reallysub.angels.client.models.ModelAngel;
import com.github.reallysub.angels.common.entities.EntityAngel;
import com.google.common.collect.Lists;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = WeepingAngels.MODID)
public class InitEvents {
	
	private static List<SoundEvent> SOUNDS = new ArrayList<>();
	
	public static final SoundEvent angelSeen = addSound("angel_seen");
	public static final SoundEvent stone_scrap = addSound("stone_scrap");
	
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
				EntityRegistry.addSpawn(EntityAngel.class, 100, 2, 6, EnumCreatureType.MONSTER, biome);
			}
		}
	}
	
	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> e) {
		SoundEvent[] sounds = SOUNDS.toArray(new SoundEvent[SOUNDS.size()]);
		e.getRegistry().registerAll(sounds);
		SOUNDS.clear();
	}
	
	// Entities
	private static int entityID = 0;
	
	private static <E extends Entity> EntityEntryBuilder<E> createBuilder(String name) {
		EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
		ResourceLocation registryName = new ResourceLocation(WeepingAngels.MODID + ":" + name);
		return builder.id(registryName, entityID++).name(registryName.toString().replaceAll(WeepingAngels.MODID + ":", ""));
	}
	
	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
		EntityEntry[] entries = { createBuilder("weepingAngel").entity(EntityAngel.class).egg(124, 156).tracker(80, 3, false).build() };
		event.getRegistry().registerAll(entries);
		
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			setUpRenders();
		}
	}
	
	@SideOnly(Side.CLIENT)
	private static void setUpRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityAngel.class, manager -> new RenderAngel(manager, new ModelAngel(), 1.0F));
	}
}
