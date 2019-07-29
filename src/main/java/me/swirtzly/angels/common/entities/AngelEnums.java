package me.swirtzly.angels.common.entities;

/*
 * This enum contains the angel types for use with ItemAngelSpawner
 */
public class AngelEnums {
	
	public enum AngelType {
		ANGEL_ONE(0, false),
		ANGEL_TWO(1, false),
		ANGEL_CHILD(-1, true),
		ANGEL_THREE(2, false),
		ANGEL_FOUR(3, false);
		
		private final boolean isChild;
		private final int id;
		
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
