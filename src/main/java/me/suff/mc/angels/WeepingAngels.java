package me.suff.mc.angels;

import com.oroarmor.config.Config;
import com.oroarmor.config.command.ConfigCommand;
import me.suff.mc.angels.client.renderer.WeepingAngelRenderer;
import me.suff.mc.angels.common.entity.QuantumLockBaseEntity;
import me.suff.mc.angels.common.entity.WeepingAngelEntity;
import me.suff.mc.angels.common.world.WAStructures;
import me.suff.mc.angels.util.Constants;
import me.suff.mc.angels.util.WAConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WeepingAngels implements ModInitializer {

    public static final Config CONFIG = new WAConfig();


    public static final EntityType< QuantumLockBaseEntity > WEEPING_ANGEL = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Constants.MODID, "weeping_angel"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, (EntityType.EntityFactory< QuantumLockBaseEntity >) (type, world) -> new WeepingAngelEntity(world)).dimensions(EntityDimensions.fixed(0.6F, 1.95F)).build()
    );

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(WEEPING_ANGEL, WeepingAngelEntity.createAttributes());
        EntityRendererRegistry.INSTANCE.register(WEEPING_ANGEL, (dispatcher, context) -> new WeepingAngelRenderer(dispatcher));
        CONFIG.readConfigFromFile();
        CONFIG.saveConfigToFile();
        CommandRegistrationCallback.EVENT.register(new ConfigCommand(CONFIG)::register);
        WAStructures.init();
    }
}
