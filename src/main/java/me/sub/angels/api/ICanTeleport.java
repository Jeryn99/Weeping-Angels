package me.sub.angels.api;

public interface ICanTeleport {

    /**
     * To block a angel from teleporting players to your dimension
     * implement this interface and return false, else
     * you can return true/false at your digression
     */
    boolean shouldTeleport();
}
