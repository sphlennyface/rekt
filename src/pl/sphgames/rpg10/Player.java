package pl.sphgames.rpg10;

import java.io.File;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;




public abstract class Player {

	protected Weapon weapon;
	protected int level, health, experience;
	public static int x, y;
	protected double playerSpeed;
	protected BufferedImage playerImgN1, playerImgN2, playerImgE1, playerImgE2, playerImgW1, playerImgW2, playerImgS1, playerImgS2, displayedImage;
	protected float stamina;
	public static Face face;
	protected static int playerSize;
	private static int lastActionHelper;
	private CurrentTiles currentTiles_, currentTile_;
	private EventHandler eventHandler_;
	private Event event_;
	private int hitbox, center;
	private boolean isMoving;
	private int changeFrame;
	private boolean faceChange;
	/** The time since the last frame change took place */
	private long lastFrameChange;
	/** The frame duration in milliseconds, i.e. how long any given frame of animation lasts */
	protected long frameDuration;
	protected Random generator;
	public enum Face {
		NORTH,
		EAST,
		WEST,
		SOUTH
	};

	public Player() {
		createNewPlayer();
		loadImages();
		hitbox = 40;
		center = 64 / 2;
	}


	public void createNewPlayer(){};

	public void loadImages(){};

	public boolean hasShot(long gameTime)
	{
		if(  (Canvas.keyboardKeyState(KeyEvent.VK_SPACE) || Canvas.mouseButtonState(MouseEvent.BUTTON1))    && 
				((gameTime - Bullet.timeOfLastCreatedBullet) >= Bullet.timeBetweenNewBullets) && 
				Weapon.currentAmmo > 0 ) 
		{
			return true;
		} else
			return false;
	}

	public void doShit(){
		if(isMoving == true)
		switch (face) {

		case SOUTH:
			if(isMoving == true && ( displayedImage == playerImgS1 || displayedImage == playerImgS2 )){
				if(faceChange == true){
					int helper = generator.nextInt(2);
					if(helper == 1)
						displayedImage = playerImgS1;
					else
						displayedImage = playerImgS2;
					faceChange = false;
				}
				if(frameDuration < lastFrameChange){
					changeFrame = 1;
					if(changeFrame == 1 && displayedImage == playerImgS1){
						displayedImage = playerImgS2;
						changeFrame = 0;

					}
					if(changeFrame == 1 && displayedImage == playerImgS2){
						displayedImage = playerImgS1;
						changeFrame = 0;
					}
					lastFrameChange = 0;
				}
				lastFrameChange++;
			}
			else{
				displayedImage = playerImgS1;

			}
			break;


		case NORTH:

			if(isMoving == true && ( displayedImage == playerImgN1 || displayedImage == playerImgN2 )){
				if(faceChange == true){
					int helper = generator.nextInt(2);
					if(helper == 1)
						displayedImage = playerImgN1;
					else
						displayedImage = playerImgN2;
					faceChange = false;
				}
				if(frameDuration < lastFrameChange){
					changeFrame = 1;
					if(changeFrame == 1 && displayedImage == playerImgN2){
						displayedImage = playerImgN1;
						changeFrame = 0;

					}
					if(changeFrame == 1 && displayedImage == playerImgN1){
						displayedImage = playerImgN2;
						changeFrame = 0;
					}
					lastFrameChange = 0;
				}
				lastFrameChange++;
			}
			else{
				displayedImage = playerImgN2;

			}
			break;

		case EAST:
			if(isMoving == true && ( displayedImage == playerImgE1 || displayedImage == playerImgE2 )){
				if(faceChange == true){
					int helper = generator.nextInt(2);
					if(helper == 1)
						displayedImage = playerImgE1;
					else
						displayedImage = playerImgE2;
					faceChange = false;
				}
				if(frameDuration < lastFrameChange){
					changeFrame = 1;
					if(changeFrame == 1 && displayedImage == playerImgE1){
						displayedImage = playerImgE2;
						changeFrame = 0;

					}
					if(changeFrame == 1 && displayedImage == playerImgE2){
						displayedImage = playerImgE1;
						changeFrame = 0;
					}
					lastFrameChange = 0;
				}
				lastFrameChange++;
			}
			else{
				displayedImage = playerImgE1;

			}
			break;

		case WEST:
			if(isMoving == true && ( displayedImage == playerImgW1 || displayedImage == playerImgW2 )){
				if(faceChange == true){
					int helper = generator.nextInt(2);
					if(helper == 1)
						displayedImage = playerImgW1;
					else
						displayedImage = playerImgW2;
					faceChange = false;
				}
				if(frameDuration < lastFrameChange){
					changeFrame = 1;
					if(changeFrame == 1 && displayedImage == playerImgW1){
						displayedImage = playerImgW2;
						changeFrame = 0;

					}
					if(changeFrame == 1 && displayedImage == playerImgW2){
						displayedImage = playerImgW1;
						changeFrame = 0;
					}
					lastFrameChange = 0;
				}
				lastFrameChange++;
			}
			else{
				displayedImage = playerImgW1;

			}
			break;


		}
	}
	public void draw(Graphics2D g2d) {
		doShit();
		g2d.drawImage(displayedImage, x, y, null);
		g2d.drawString("Ammo: " + weapon.getCurrentAmmo(), 5, 15);
	}

	public void update()
	{
		handleMovement();
		weapon.update();
	}

	public void passEventHandler(EventHandler eventHandler) {
		eventHandler_ = eventHandler;
	}

	
	
	private boolean newIsWalkable(double x, double y) {
		int x_ = (int) x;
		int y_ = (int) y;
		currentTile_ = this.getCurrentTile(x_ + this.center - this.hitbox/2, y_ + this.center - this.hitbox/2);
		if (!currentTile_.areWalkableTiles())
			return false;
		currentTile_ = this.getCurrentTile(x_ + this.center + this.hitbox/2, y_ + this.center - this.hitbox/2 );
		if (!currentTile_.areWalkableTiles())
			return false;
		currentTile_ = this.getCurrentTile(x_ + this.center + this.hitbox/2, y_ + this.center + this.hitbox/2);
		if (!currentTile_.areWalkableTiles())
			return false;
		currentTile_ = this.getCurrentTile(x_ + this.center - this.hitbox/2, y_ + this.center + this.hitbox/2);
		if (!currentTile_.areWalkableTiles())
			return false;
		return true; 
		
		
	}
		


	public boolean isSwitchingLevel (int x, int y) {
		currentTiles_ = this.getCurrentTiles(x,y);
		if (currentTiles_.areSwitchLevelTiles()) {
			lastActionHelper = currentTiles_.getActionHelper();
			return true;
		}
		return false;
	}
	public boolean isOnTileSwitcher (int x, int y) {
		currentTiles_ = this.getCurrentTiles(x, y);
		if (currentTiles_.areSwitchTileTiles()){
			lastActionHelper = currentTiles_.getActionHelper();
			return true;
		}
		return false;
	}
	public Event getEvent() {
		return event_;
	}

	public boolean isOnEventTile(double x, double y) {
		int x_ = (int) x;
		int y_ = (int) y;
		currentTiles_ = this.getCurrentTiles(x_,y_);
		if (currentTiles_.areEventTiles()) {
			event_ = currentTiles_.getEvent();
			return true;
		}
		return false;
	}

	public int getLastActionHelper() {
		return lastActionHelper;
	}

	public static void move() {
		if (y < 2 * 64) {
			y = 10 * 64;
		}
		else if (y > 10 * 64) {
			y = 64;
		}
		else if (x < 3 * 64) {
			x = 14*64;
		}
		else {
			x = 64;
		}
	}
	
	public CurrentTiles getCurrentTile(int x, int y) {
		double xH = x, yH = y;
		int xH2, yH2;		
		xH2 = x / 64;
		yH2 = y / 64;
		return new CurrentTiles(World.background[xH2][yH2]);
		
	}

	public CurrentTiles getCurrentTiles(int x, int y) {

		double xH = x, yH = y;
		int xH2, yH2;		


		if (xH % 64 == 0 && yH % 64 == 0) {
			xH2 = (int) xH / 64;
			yH2 = (int) yH / 64;

			return new CurrentTiles(World.background[xH2][yH2]);
		}

		else if (xH % 64 == 0) {

			while (yH % 64 != 0) {
				yH--;
			}

			xH2 = (int) xH / 64;
			yH2 = (int) yH / 64;

			return new CurrentTiles(World.background[xH2][yH2],World.background[xH2][yH2 + 1]);				

		}

		else if (yH % 64 == 0) {

			while (xH % 64 != 0) {
				xH--;
			}

			xH2 = (int) xH / 64;
			yH2 = (int) yH / 64;

			return new CurrentTiles(World.background[xH2][yH2],World.background[xH2 + 1][yH2]);						

		}

		else {
			while (xH % 64 != 0) {
				xH--;
			}		
			while (yH % 64 != 0) {
				yH--;
			}

			xH2 = (int) xH / 64;
			yH2 = (int) yH / 64;

			return new CurrentTiles(World.background[xH2][yH2],World.background[xH2 + 1][yH2],
					World.background[xH2][yH2 + 1],World.background[xH2 + 1][yH2 + 1]);

		}
	}







	private void handleMovement() {
		if(Canvas.keyboardKeyState(KeyEvent.VK_W) && Canvas.keyboardKeyState(KeyEvent.VK_CONTROL)) {
			face = Face.NORTH;
		}
		else if(Canvas.keyboardKeyState(KeyEvent.VK_S) && Canvas.keyboardKeyState(KeyEvent.VK_CONTROL)) {
			face = Face.SOUTH;
		}
		else if(Canvas.keyboardKeyState(KeyEvent.VK_A) && Canvas.keyboardKeyState(KeyEvent.VK_CONTROL)) {
			face = Face.WEST;
		}
		else if(Canvas.keyboardKeyState(KeyEvent.VK_D) && Canvas.keyboardKeyState(KeyEvent.VK_CONTROL)) {
			face = Face.EAST;
		}
		else {{ 


			if(Canvas.keyboardKeyState(KeyEvent.VK_W)) {
				face = Face.NORTH;
				for(int i = 0; i < playerSpeed; i++)
					if (newIsWalkable(x, y - 1))
						y -= 1;
				isMoving = true;
			}

			if(Canvas.keyboardKeyState(KeyEvent.VK_S)) {
				face = Face.SOUTH;
				for(int i = 0; i < playerSpeed; i++)
					if (newIsWalkable(x, y + 1))
						y += 1;
				isMoving = true;
			}
			
			if(Canvas.keyboardKeyState(KeyEvent.VK_D)) {
				face = Face.EAST;
				for(int i = 0; i < playerSpeed; i++)
					if (newIsWalkable(x + 1, y))
						x += 1;
				isMoving = true;
			}

			if(Canvas.keyboardKeyState(KeyEvent.VK_A)) {
				face = Face.WEST;
				for(int i = 0; i < playerSpeed; i++)
					if (newIsWalkable(x - 1, y))
						x -= 1;
				isMoving = true;
			}}
		if(!Canvas.keyboardKeyState(KeyEvent.VK_A) && !Canvas.keyboardKeyState(KeyEvent.VK_S) && !Canvas.keyboardKeyState(KeyEvent.VK_D) && !Canvas.keyboardKeyState(KeyEvent.VK_W)){
			isMoving = false;
				faceChange = true;
		}

		}

		if (Canvas.keyboardKeyState(KeyEvent.VK_R)) {
			weapon.reload();
		}

		if (Canvas.keyboardKeyState(KeyEvent.VK_ESCAPE)) {
			Framework.createMainMenu();
		}



	}

}
