package me.suff.mc.angels;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oroarmor.config.Config;
import me.suff.mc.angels.common.entity.ChronoEntity;
import me.suff.mc.angels.common.entity.EntitySpawns;
import me.suff.mc.angels.common.entity.PortalEntity;
import me.suff.mc.angels.common.entity.WeepingAngelEntity;
import me.suff.mc.angels.common.objects.WABlocks;
import me.suff.mc.angels.common.objects.WAItems;
import me.suff.mc.angels.common.objects.WATiles;
import me.suff.mc.angels.common.world.features.WAFeatures;
import me.suff.mc.angels.common.world.structure.WAStructures;
import me.suff.mc.angels.util.Constants;
import me.suff.mc.angels.util.WAConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WeepingAngels implements ModInitializer {

    public static final Config CONFIG = new WAConfig();
    public static final Identifier spawnPacket = new Identifier(Constants.MODID, "spawn_packet");

    public static final EntityType< WeepingAngelEntity > WEEPING_ANGEL = Registry.register(Registry.ENTITY_TYPE, new Identifier(Constants.MODID, "weeping_angel"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, (EntityType.EntityFactory< WeepingAngelEntity >) (type, world) -> new WeepingAngelEntity(world)).dimensions(EntityDimensions.fixed(0.6F, 1.95F)).build());
    public static final EntityType< ChronoEntity > CHRONO = Registry.register(Registry.ENTITY_TYPE, new Identifier(Constants.MODID, "chrono"), FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType.EntityFactory< ChronoEntity >) (type, world) -> new ChronoEntity(WeepingAngels.CHRONO, world)).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build());
    public static final EntityType< PortalEntity > PORTAL = Registry.register(Registry.ENTITY_TYPE, new Identifier(Constants.MODID, "portal"), FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType.EntityFactory< PortalEntity >) (type, world) -> new PortalEntity(WeepingAngels.PORTAL, world)).dimensions(EntityDimensions.fixed(1.5F, 1.5F)).build());

    public static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();


    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(WEEPING_ANGEL, WeepingAngelEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(PORTAL, MobEntity.createMobAttributes());
        WAStructures.init();
        WAConfig.init();
        EntitySpawns.init();
        WABlocks.init();
        WATiles.init();
        WAFeatures.init();
    }

}
