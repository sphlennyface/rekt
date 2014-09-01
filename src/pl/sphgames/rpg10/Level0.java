package pl.sphgames.rpg10;

public class Level0 extends LevelEncoder{
	
	
	///ITS A FUCKING TRAP LOL
	
	private int wallsId;
	private int surfaceId;
	
	public Level0() {
		
		drawWorld();
	}
	
	public void drawWorld() {
		wallsId = 3;			//LEWO   //GORA   ///PRAWO     ////DOL
		int[] doors = new int[] {0,       0,			0,			0};
		surfaceId = 4;	
		createWorld(wallsId, doors, surfaceId);
	}
}
