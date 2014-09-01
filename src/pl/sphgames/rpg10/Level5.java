package pl.sphgames.rpg10;

public class Level5 extends LevelEncoder{
	
	private int wallsId;
	private int surfaceId;
	
	public Level5() {
		
		drawWorld();
	}
	
	public void drawWorld() {
		wallsId = 3;			//LEWO   //GORA   ///PRAWO     ////DOL
		int[] doors = new int[] {0,       3,			0,			6};
		surfaceId = 1;	
		createWorld(wallsId, doors, surfaceId);
	}
}
