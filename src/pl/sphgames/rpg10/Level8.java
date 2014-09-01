package pl.sphgames.rpg10;

public class Level8 extends LevelEncoder{
	
	private int wallsId;
	private int surfaceId;
	
	public Level8() {
		
		drawWorld();
	}
	
	public void drawWorld() {
		wallsId = 3;			//LEWO   //GORA   ///PRAWO     ////DOL
		int[] doors = new int[] {0,       0,			1,			0};
		surfaceId = 1;	
		createWorld(wallsId, doors, surfaceId);
		createTerrain(1,1,14,4,false,false,Action.NONE,-1,2,TEXTURE.DIRT);
		createTerrain(1,7,14,4,false,false,Action.NONE,-1,2,TEXTURE.DIRT);
	}
}
