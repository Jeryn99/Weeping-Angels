package me.suff.mc.angels.common.entities;

/*
 * This enum contains the angel types for use with AngelSpawnerItem
 */
public class AngelEnums {

    public enum AngelType {
        ANGEL_ONE(0), ANGEL_TWO(1), ANGEL_CHILD(-1, true), ANGEL_THREE(2), ANGEL_FOUR(3), ANGEL_FIVE(4), ANGEL_SIX(5);
        private final boolean isChild;
        private final int id;

        AngelType(int id) {
            this.id = id;
            this.isChild = false;
        }

        AngelType(int id, boolean isChild) {
            this.id = id;
            this.isChild = isChild;
        }

        public int getId() {
            return id;
        }

        public boolean isChild() {
            return isChild;
        }
    }

}
