package pl.sphgames.rpg10;

public class LevelManager {

	public int currentLevel;
	private LevelEncoder levelEncoder_;
	
	public LevelManager() {
		levelEncoder_ = new LevelEncoder();
		
	}
	
	public void switchLevel(int newLevel) {
		switch (newLevel) {
			case 0:
			new Level0();
			break;
			case 1:
				new Level1();
			break;
			case 2:
				new Level2();
			break;
			case 3:
				new Level3();
			break;
			case 4:
				new Level4();
			break;
			case 5:
				new Level5();
			break;
			case 6:
				new Level6();
			break;
			case 7:
				new Level7();
			break;
			case 8:
				new Level8();
			break;
			case 9:
				//new Level9();
			break;
			default:
				new Level0();
			break;
		}
	}
}
