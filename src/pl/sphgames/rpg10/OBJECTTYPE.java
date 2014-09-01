package pl.sphgames.rpg10;

public enum OBJECTTYPE {
	CRATE,
	TREE;


public static String getType(OBJECTTYPE type) {
	
	switch (type) {
		case CRATE:
			return "crate";
		case TREE:
			return "tree";
		default:	
			return "N";
	}
	
}

}
