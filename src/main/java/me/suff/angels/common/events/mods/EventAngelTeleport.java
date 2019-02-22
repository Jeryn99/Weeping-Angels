package me.suff.angels.common.events.mods;

import me.suff.angels.common.entities.EntityWeepingAngel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class EventAngelTeleport extends PlayerEvent {
	
	/**
	 * This event fires when a weeping angel has teleported a player.
	 */
	
	private final int targetDimension;
	private final EntityWeepingAngel angel;
	private final BlockPos previous, destinationPos;
	
	public EventAngelTeleport(EntityPlayer player, EntityWeepingAngel angel, BlockPos destinationPos, int targetDimension) {
		super(player);
		this.angel = angel;
		previous = player.getPosition();
		this.destinationPos = destinationPos;
		this.targetDimension = targetDimension;
	}
	
	/**
	 * Returns the angel entity
	 */
	public EntityWeepingAngel getAngel() {
		return angel;
	}
	
	/**
	 * Returns the intended destination position
	 */
	public BlockPos getDestination() {
		return destinationPos;
	}
	
	/**
	 * Returns the position of the player BEFORE being teleported
	 */
	public BlockPos getPreviousLocation() {
		return previous;
	}
	
	/**
	 * Returns the intended dimension that the player will be teleported to
	 */
	public int getTargetDimension() {
		return targetDimension;
	}
}
