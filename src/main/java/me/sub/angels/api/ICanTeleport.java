package me.sub.angels.api;

@Deprecated //Will be gone in 1.13
public interface ICanTeleport {

	/**
	 * To block a angel from teleporting players to your dimension implement this interface and return false, else you can return true/false at your digression
	 */
	boolean shouldTeleport();
}
