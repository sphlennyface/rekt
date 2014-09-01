package pl.sphgames.rpg10;

public class Level3 extends LevelEncoder{
	
	private int wallsId;
	private int surfaceId;
	
	public Level3() {
		
		drawWorld();
	}
	
	public void drawWorld() {
		wallsId = 1;			//LEWO   //GORA   ///PRAWO     ////DOL
		int[] doors = new int[] {1,       4,			0,			5};
		surfaceId = 1;	
		createWorld(wallsId, doors, surfaceId);
		
		createTerrain(5,3,1,7,false,false,Action.NONE,-1,4,TEXTURE.DIRT);
		createTerrain(3,3,1,7,false,false,Action.NONE,-1,4,TEXTURE.DIRT);
	}
}
