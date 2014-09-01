package pl.sphgames.rpg10;

public class Level6 extends LevelEncoder{
	
	private int wallsId;
	private int surfaceId;
	
	public Level6() {
		
		drawWorld();
	}
	
	public void drawWorld() {
		wallsId = 3;			//LEWO   //GORA   ///PRAWO     ////DOL
		int[] doors = new int[] {0,       5,			7,			0};
		surfaceId = 1;	
		createWorld(wallsId, doors, surfaceId);
		
	}
}
