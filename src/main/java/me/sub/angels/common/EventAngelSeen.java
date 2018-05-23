package me.sub.angels.common;

import me.sub.angels.common.entities.EntityAngel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.eventhandler.Event;

public class EventAngelSeen extends Event {

	EntityLivingBase entity;
	EntityAngel angel;

	public EventAngelSeen(EntityLivingBase entity, EntityAngel angel) {
		this.entity = entity;
		this.angel = angel;
	}
	
	public EntityAngel getAngel() {
		return angel;
	}

	public EntityLivingBase getViewer() {
		return entity;
	}
}
