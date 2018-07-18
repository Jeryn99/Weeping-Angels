package me.sub.angels.api;

public interface IBreakException {

    /**
     * Angels breaks blocks with a light level above 7
     * which is a problem, as your mod may have light blocks that shouldn't be broken!
     * so you just need to implement this and determine true (if you want to allow it based on conditions) or false!
     */
    boolean shouldBreak();
}
