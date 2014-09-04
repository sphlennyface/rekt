package pl.sphgames.rpg10;



	import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


	public class Game {
		
		private World world;
		private Player player;
		private AI ai;
		public ActionHandler actionHandler;
		private Weapon weapon;
		private EventHandler eventHandler;
		private LevelManager levelManager;
		private static ArrayList<Bullet> bulletsList;
		private static ArrayList<Object> objectsList;
		private BufferedImage holder;
		public ObjectHandler objectHandler;
		private Crosshair crosshair;
		public enum CHARACTER {LEGOLAS, GANDALF};
		public static CHARACTER charChosen;
		public static ArrayList<Timer> timerList;

	    public Game()
	    {
	    	
	        Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
	        
	        Thread threadForInitGame = new Thread() {
	            @Override
	            public void run(){
	                Initialize();
	                LoadContent();
	                
	                Framework.gameState = Framework.GameState.PLAYING;
	            }
	        };
	        threadForInitGame.start();
	    }
	    
	
	    private void Initialize()
	    {
	    	if(Game.charChosen == CHARACTER.LEGOLAS)
	    		player = new Legolas();
	    	else
	    		player = new Gandalf();
	    	weapon = player.weapon;
	    	crosshair = new Crosshair();
	    	ai = new AI();
	    	objectHandler = new ObjectHandler();
	    	actionHandler = new ActionHandler();
	    	actionHandler.passPlayer(player);
	    	levelManager = new LevelManager();
	    	actionHandler.passObjectHandler(objectHandler);
	    	actionHandler.passLevelManager(levelManager);
	    	eventHandler = new EventHandler();
	    	eventHandler.passPlayer(player);
	    	player.passEventHandler(eventHandler);
	    	world = new World();
	    	
	    	bulletsList = new ArrayList<Bullet>();
	    	timerList = new ArrayList<Timer>();
	    	objectsList = new ArrayList<Object>();
	    	levelManager.switchLevel(1);
	    	Game.getNewList(ObjectHandler.getList());
	    }
	    
	
	    private void LoadContent()
	    {
	    	try {
	           holder = ImageIO.read(new File("bulletS.png"));
	           Bullet.bulletImgS = holder;
	           holder = ImageIO.read(new File("bulletN.png"));
	           Bullet.bulletImgN = holder;
	           holder = ImageIO.read(new File("bulletE.png"));
	           Bullet.bulletImgE = holder;
	           holder = ImageIO.read(new File("fireball.png"));
	           Bullet.fireball = holder;

	          
			}
			catch (IOException ex) {
				Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
			}
	    }    

	    public void RestartGame()
	    {
	       
	    }
	    
	    public static void getNewList(ArrayList<Object> objectsList_) {
	    	objectsList = objectsList_;
	    }
	    
	    public void UpdateGame(long gameTime, Point mousePosition)
	    {
	       world.update();
	       ai.update();
	       actionHandler.handleActions();
	       eventHandler.handleEvents();
	       player.update();
	       updateBullets();
	       didPlayerShoot(gameTime);
	    }
	    

	    public void Draw(Graphics2D g2d, Point mousePosition)
	    {
	    	world.draw(g2d);
	    	ai.Draw(g2d);
	    	crosshair.draw(g2d, mousePosition);
	    	drawBullets(g2d);
	    	drawObjects(g2d);
	    	drawTimers(g2d);
	    	player.draw(g2d);
		    
	    	 
	    }
	    public void drawTimers(Graphics2D g2d) {
	    	for (int i = 0; i < timerList.size(); i++) {
	    		timerList.get(i).draw(g2d);
	    	}
	    }
	    
	    public void drawObjects(Graphics2D g2d) {
	    	for (int i = 0; i < objectsList.size(); i++) {
	    		objectsList.get(i).Draw(g2d);
	    	}
	    }
	    
	    
	    public void drawBullets(Graphics2D g2d) {
	    	for(int i = 0; i < bulletsList.size(); i++)
	         {
	             bulletsList.get(i).Draw(g2d);
	         }
	    }
	    
	    private void didPlayerShoot(long gameTime)
	    {
	        if(player.hasShot(gameTime))
	        {
	            Bullet.timeOfLastCreatedBullet = gameTime;
	            weapon.currentAmmo--;
	            
	            Bullet b = new Bullet(player.x+7,player.y+7,weapon.damage, crosshair._mousePosition);
	            bulletsList.add(b);
	        }
	    }
	    
	    public static void clearBulletsArray() {
	    	bulletsList.clear();
	    }
	    
	    
	    private void updateBullets() {
	    	
	    	for(int i = 0; i < bulletsList.size(); i++)
	        {
	            Bullet bullet = bulletsList.get(i);
	            
	            bullet.update();
	            
	            if(bullet.isItLeftScreen() ){
	                bulletsList.remove(i);
	               continue;
	            }
	            
	            if (bullet.hitUnpassable(bullet.x, bullet.y)) {
	            	bulletsList.remove(i);
	            	continue;
	            }
	    }
	    
	}
	}
