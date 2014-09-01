package pl.sphgames.rpg10;

public class Level1 extends LevelEncoder{
	
	private int wallsId;
	private int surfaceId;
	
	public Level1() {
		
		drawWorld();
	}
	
	public void drawWorld() {
		wallsId = 1;			//LEWO   //GORA   ///PRAWO     ////DOL
		int[] doors = new int[] {8,       2,			3,			0};
		surfaceId = 3;	
		createWorld(wallsId, doors, surfaceId);
		
		new Object(1,6,true,true,Action.NONE,-1,OBJECTTYPE.TREE,3);
		
		new Object(1,4,true,true,Action.NONE,-1,OBJECTTYPE.TREE,3);
		
		new Object(13,6,true,true,Action.NONE,-1,OBJECTTYPE.TREE,3);
		
		new Object(13,4,true,true,Action.NONE,-1,OBJECTTYPE.TREE,3);
		
		new Object(5,4,true,true,Action.NONE,-1,OBJECTTYPE.TREE,4);
		new Object(8,9,true,true,Action.NONE,-1,OBJECTTYPE.TREE,4);
		
		new Object(6,1,true,true,Action.NONE,-1,OBJECTTYPE.TREE,3);
		
		new Object(8,1,true,true,Action.NONE,-1,OBJECTTYPE.TREE,3);
		
			new Object(2,2,false,false,Action.NONE,-1,OBJECTTYPE.CRATE,1);
		//new Object(12,7,true,true,Action.NONE,-1,OBJECTTYPE.TREE,1);
	}
}
