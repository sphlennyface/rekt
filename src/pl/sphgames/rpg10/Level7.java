package pl.sphgames.rpg10;

public class Level7 extends LevelEncoder{
	
	private int wallsId;
	private int surfaceId;
	
	public Level7() {
		
		drawWorld();
	}
	
	public void drawWorld() {
		wallsId = 3;			//LEWO   //GORA   ///PRAWO     ////DOL
		int[] doors = new int[] {6,       0,			0,			0};
		surfaceId = 1;	
		createWorld(wallsId, doors, surfaceId);
	}
}
