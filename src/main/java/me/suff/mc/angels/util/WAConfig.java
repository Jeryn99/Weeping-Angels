package me.suff.mc.angels.util;

/* Created by Craig on 18/02/2021 */
//TODO THIS DOESNT WORK
public class WAConfig {

    public static SimpleConfig CONFIG = SimpleConfig.of( "weeping_angels" ).provider(namespace -> "weeping_angels").request();

    public int stalkRange = CONFIG.getOrDefault( "stalkRange", 25 );

    public SimpleConfig getConfig() {
        return CONFIG;
    }

    public int getStalkRange() {
        return stalkRange;
    }
}
