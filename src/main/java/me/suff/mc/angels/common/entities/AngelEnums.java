package me.suff.mc.angels.common.entities;

/*
 * This enum contains the angel types for use with AngelSpawnerItem
 */
public class AngelEnums {

    public enum AngelType {
        VILLAGER("Villager"), ANGELA_MC("Angela"), ED("Ed"), CHERUB("Cherub"), VIO_1("Violet"), VIO_2("Violet (2)"), A_DIZZLE("A_Dizzle");
        private final String readable;

        AngelType(String readable) {
            this.readable = readable;
        }

        public String getReadable() {
            return readable;
        }
    }

}
