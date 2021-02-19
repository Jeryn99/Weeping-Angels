package me.suff.mc.angels;

import com.oroarmor.config.Config;
import me.suff.mc.angels.client.renderer.entiity.WeepingAngelRenderer;
import me.suff.mc.angels.client.renderer.tile.CoffinTileRenderer;
import me.suff.mc.angels.client.renderer.tile.PlinthTileRenderer;
import me.suff.mc.angels.client.renderer.tile.StatueTileRender;
import me.suff.mc.angels.common.entity.EntitySpawns;
import me.suff.mc.angels.common.entity.QuantumLockBaseEntity;
import me.suff.mc.angels.common.entity.WeepingAngelEntity;
import me.suff.mc.angels.common.objects.WABlocks;
import me.suff.mc.angels.common.objects.WAItems;
import me.suff.mc.angels.common.objects.WATiles;
import me.suff.mc.angels.common.world.features.WAFeatures;
import me.suff.mc.angels.common.world.structure.WAStructures;
import me.suff.mc.angels.util.AngelUtils;
import me.suff.mc.angels.util.Constants;
import me.suff.mc.angels.util.WAConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WeepingAngels implements ModInitializer, ClientModInitializer {

    public static final Config CONFIG = new WAConfig();

    public static final EntityType< QuantumLockBaseEntity > WEEPING_ANGEL = Registry.register(Registry.ENTITY_TYPE, new Identifier(Constants.MODID, "weeping_angel"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, (EntityType.EntityFactory< QuantumLockBaseEntity >) (type, world) -> new WeepingAngelEntity(world)).dimensions(EntityDimensions.fixed(0.6F, 1.95F)).build());

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(WEEPING_ANGEL, WeepingAngelEntity.createAttributes());
        WAStructures.init();
        WAConfig.init();
        EntitySpawns.init();
        WABlocks.init();
        WATiles.init();
        WAFeatures.init();
        onInitializeClient();
    }

    @Override
    public void onInitializeClient() {
        FabricModelPredicateProviderRegistry.register(WAItems.DETECTOR, new Identifier("angle"), (stack, world, entity) -> AngelUtils.RAND.nextInt(17));
        EntityRendererRegistry.INSTANCE.register(WEEPING_ANGEL, (dispatcher, context) -> new WeepingAngelRenderer(dispatcher));
        BlockEntityRendererRegistry.INSTANCE.register(WATiles.STATUE_TILE, StatueTileRender::new);
        BlockEntityRendererRegistry.INSTANCE.register(WATiles.PLINTH_TILE, PlinthTileRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(WATiles.COFFIN_TILE, CoffinTileRenderer::new);
    }
}
