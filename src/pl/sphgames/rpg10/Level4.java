package pl.sphgames.rpg10;

public class Level4 extends LevelEncoder{
	
	private int wallsId;
	private int surfaceId;
	
	public Level4() {
		
		drawWorld();
	}
	
	public void drawWorld() {
		wallsId = 3;			//LEWO   //GORA   ///PRAWO     ////DOL
		int[] doors = new int[] {2,       0,			0,			3};
		surfaceId = 1;	
		createWorld(wallsId, doors, surfaceId);
	}
}
