package pl.sphgames.rpg10;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

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
			AI.clearArray();
			Game.clearBulletsArray();
			changeLevel(nextLevel);
		}
		if (player_.isOnTileSwitcher(player_.x, player_.y)){
			System.out.println("WBILEM");
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
		Game.timerList.clear();
		objectHandler_.clearArray();
		levelManager_.switchLevel(nextLevel);
		Game.getNewList(objectHandler_.getList());		
		player_.move();

	}
}
