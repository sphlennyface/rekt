package pl.sphgames.rpg10;

public class Whatever {

	private LevelEncoder levelEncoder_;
	private int wallsId;
	private int surfaceId;
	
	
	
	public Whatever() {
		levelEncoder_ = new LevelEncoder();
		setLevel(3,1);		
	}
	
	void setLevel(int wi, int si) {
		wallsId = wi;
		int[] doors = new int[] {0,1,1,0};
		surfaceId = si;	
		levelEncoder_.createWorld(wallsId, doors, surfaceId);
	}
	
	
}
