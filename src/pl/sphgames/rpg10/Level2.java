package pl.sphgames.rpg10;

import pl.sphgames.rpg10.LevelEncoder.TEXTURE;

public class Level2 extends LevelEncoder{
	
	private int wallsId;
	private int surfaceId;

	
	public Level2() {
		
		drawWorld();
	}
	
	public void drawWorld() {
		wallsId = 1;			//LEWO   //GORA   ///PRAWO     ////DOL
		int[] doors = new int[] {0,       0,			4,			1};
		surfaceId = 6;	
		createWorld(wallsId, doors, surfaceId);
		
		
		
		for (int y = 2; y <= 5; y++)
			new Object(3,y,false,false,Action.NONE,-1,OBJECTTYPE.CRATE,1);
		
		for (int y = 2; y <= 5; y++)
			new Object(5,y,false,false,Action.NONE,-1,OBJECTTYPE.CRATE,1);
		
		createTerrain(4,4,4,4,false,true,Action.NONE,-1,1,TEXTURE.WATER);
		

		
		
	}
}
