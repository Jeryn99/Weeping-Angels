package me.swirtzly.angels.compat;

import java.util.ArrayList;

/**
 * Created by Swirtzly
 * on 01/03/2020 @ 15:20
 */
public class CompatManager {

    private static ArrayList<ICompat> COMPAT = new ArrayList<>();

    public static ArrayList<ICompat> getCompatiblityModules() {
        return COMPAT;
    }

    public static void registerCompat(ICompat compat) {
        COMPAT.add(compat);
    }
}
