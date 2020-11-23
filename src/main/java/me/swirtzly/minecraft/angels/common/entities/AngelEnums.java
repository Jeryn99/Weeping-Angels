package me.swirtzly.minecraft.angels.common.entities;

/*
 * This enum contains the angel types for use with AngelSpawnerItem
 */
public class AngelEnums {
	
	public enum AngelType {
		ANGELA, ANGELA_MC, ED, ED_ANGEL_CHILD(true), VIO_1, VIO_2, A_DIZZLE;
		private final boolean isChild;

		AngelType() {
			this.isChild = false;
		}

		AngelType(boolean isChild) {
			this.isChild = isChild;
		}

		public boolean isChild() {
			return isChild;
		}
	}
	
}
