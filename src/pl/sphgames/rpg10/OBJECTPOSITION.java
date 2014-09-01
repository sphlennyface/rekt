package pl.sphgames.rpg10;

public enum OBJECTPOSITION {
	NORTH,
	NORTHWEST,
	WEST,
	SOUTHWEST,
	SOUTH,
	SOUTHEAST,
	EAST,
	NORTHEAST,
	WHOLE;
	
	public static String getPos(OBJECTPOSITION pos) {
		
		switch (pos) {
			case NORTH:
				return "N";
			case NORTHWEST:				
				return "NW";
			case WEST:
				return "W";
			case SOUTHWEST:
				return "SW";
			case SOUTH:
				return "S";
			case SOUTHEAST:
				return "SE";
			case EAST:
				return "E";
			case NORTHEAST:
				return "NE";
			case WHOLE:
				return "WHOLE";
			default:	
				return "N";
		}
		
	}
}
