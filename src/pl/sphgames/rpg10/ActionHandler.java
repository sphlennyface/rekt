package pl.sphgames.rpg10;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import pl.sphgames.rpg10.Framework.GameState;

public class ActionHandler {

	public Player player_;
	public LevelManager levelManager_;
	public ObjectHandler objectHandler_;
	public int nextLevel;
	
	public ActionHandler() {
      
	}
	
	public void handleActions() {
		//programowanie xD
		if (player_.isSwitchingLevel(player_.x, player_.y)) {
			
			// PAUZA
			// FADOWANIE
			new SplashScreen(true,0.00f,this);		
						
			// KONIEC FADOWANIA
			
			// ZMIANY POZIOMOWE
		
			// KONIEC ZMIAN POZIOMOWYCH
			
			
			// UNFADOWANIE
		
			// KONIEC UNFADOWANIA
			
			//RIZUM
			
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
	
	
	public void changeLevel(int level ) {
		Game.clearBulletsArray();
		AI.clearArray();
		System.out.printf("level level = " + level);
		World.createPathsArray();
		Game.timerList.clear();
		objectHandler_.clearArray();
		levelManager_.switchLevel(level);
		Game.getNewList(objectHandler_.getList());		
		player_.move();

	}
}
