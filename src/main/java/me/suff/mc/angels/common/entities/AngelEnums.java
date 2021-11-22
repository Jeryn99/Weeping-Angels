package me.suff.mc.angels.common.entities;

/*
 * This enum contains the angel types for use with AngelSpawnerItem
 */
public class AngelEnums {

    public enum AngelType {
        VILLAGER("Villager"), DISASTER_MC("Disaster"), ED("Ed"), ED_ANGEL_CHILD("Cherub"), A_DIZZLE("A_Dizzle"), DYING("Dying");
        private final String readable;

        AngelType(String readable) {
            this.readable = readable;
        }

        public static AngelType next(AngelType type) {
            int index = type.ordinal();
            int nextIndex = index + 1;
            AngelType[] angels = AngelType.values();
            nextIndex %= angels.length;
            return angels[nextIndex];
        }

        public String getReadable() {
            return readable;
        }

        public boolean canHoldThings() {
            return this == DISASTER_MC;
        }
    }

}
