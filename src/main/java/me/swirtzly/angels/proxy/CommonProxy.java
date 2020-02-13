package me.swirtzly.angels.proxy;

import me.swirtzly.angels.common.world.WorldGen;

/**
 * Created by Swirtzly
 * on 13/02/2020 @ 11:05
 */
public class CommonProxy implements IProxy {

    @Override
    public void onPre() {
        WorldGen.applyFeatures();
    }

    @Override
    public void onInit() {

    }

    @Override
    public void onPost() {

    }
}
