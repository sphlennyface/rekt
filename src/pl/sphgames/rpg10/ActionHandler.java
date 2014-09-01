package pl.sphgames.rpg10;

public class ActionHandler {

	private Player player_;
	private LevelManager levelManager_;
	private ObjectHandler objectHandler_;
	private int nextLevel;
	
	public ActionHandler() {
	}
	
	public void handleActions() {
		if (player_.isSwitchingLevel(player_.x, player_.y)) {
			nextLevel = player_.getLastActionHelper();
			changeLevel(nextLevel);
		}
	}
	
	public void passPlayer(Player player) {
		player_ = player;
	}
	
	public void passLevelManager(LevelManager levelManager) {
		levelManager_ = levelManager;
	}
	
	public void passObjectHandler(ObjectHandler objectHandler) {
		objectHandler_ = objectHandler;
	}
	
	
	private void changeLevel(int level ) {
		objectHandler_.clearArray();
		levelManager_.switchLevel(nextLevel);
		Game.getNewList(objectHandler_.getList());		
		player_.move();
	}
}
