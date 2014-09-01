package pl.sphgames.rpg10;


import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import pl.sphgames.rpg10.Game.CHARACTER;


public class MainMenuPointer {
	private int x_, y_;
	private BufferedImage cursor;
	private SELECTION selected;
	private int cursorMovement;
	private enum SELECTION {
		NEWGAME,
		RANDOM,
		EXIT
	};
	
	public MainMenuPointer() {
		selected = SELECTION.NEWGAME;
		x_ = 300;
		y_ = 200;
		cursorMovement = 70;
		
		loadGraphics();
	}
	
	private void loadGraphics() {
		try {
            cursor = ImageIO.read(new File("cursor.png"));
		}
		catch (IOException ex) {
			Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void update() {
		handleMovement();
	}
	
	
	private boolean isPossibleUp() {
		if (y_ > 200)
			return true;
		return false;
		
	}
	
	private boolean isPossibleDown() {
		if (y_ < 390)
			return true;
		return false;
	}
	
	private void getSelection(int y) {
		switch (y) {
		case 200:
			selected = SELECTION.NEWGAME;
			Game.charChosen = CHARACTER.GANDALF;
		break;
		
		case 270:
			selected = SELECTION.NEWGAME;
			Game.charChosen = CHARACTER.LEGOLAS;
		break;
		
		case 340:
			selected = SELECTION.RANDOM;
		break;
		
		case 410:
			selected = SELECTION.EXIT;
		break;
		default:
			
		break;
		}
	}
	
	private void goodNightSweetPrince() {
		try {
            Thread.sleep(100);
      } catch (InterruptedException ex) { }
	}
	
	private void handleMovement() {
		 if(Canvas.keyboardKeyState(KeyEvent.VK_W)) {
			 if(isPossibleUp()) {
				 y_ -= cursorMovement;
				 goodNightSweetPrince();
				 getSelection(y_);
			 }
			 
		 }
		 if(Canvas.keyboardKeyState(KeyEvent.VK_S)) {
			 if(isPossibleDown()) {
				 y_ += cursorMovement;
				 goodNightSweetPrince();
				 getSelection(y_);
			 }
			 
		 }
		 if(Canvas.keyboardKeyState(KeyEvent.VK_ENTER)) {
			 handleInput();
		 }
	}
	
	private void handleInput() {
		switch (selected) {
		case NEWGAME:
			Framework.newGame();
		break;
		
		case RANDOM:
			
		break;
		
		case EXIT:
			
		break;
		default:
			
		break;
		}
	}
	
	public void Draw(Graphics2D g2d) {
		g2d.drawImage(cursor, x_, y_, null);
	}
	
	
}
