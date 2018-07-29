package me.sub.angels.common.misc;

public class AngelEnums {
	
	public enum AngelType {
		ANGEL_ONE(0, false), ANGEL_TWO(1, false), ANGEL_CHILD(-1, true);
		
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
