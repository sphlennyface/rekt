package pl.sphgames.rpg10;



	import java.awt.Graphics2D;
	import java.awt.Graphics;
	import java.awt.AlphaComposite;
	import java.awt.RenderingHints;
import java.awt.Point;
import java.io.File;
import java.awt.Color;
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
		public static boolean fade;
		private static float alpha;
		private static boolean paused;
		private EventHandler eventHandler;
		private LevelManager levelManager;
		private static ArrayList<Bullet> bulletsList;
		private static ArrayList<Object> objectsList;
		private static ArrayList<SplashScreen> splash;
		private BufferedImage holder;
		public ObjectHandler objectHandler;
		private float tempAlpha;
		private Crosshair crosshair;
		public enum CHARACTER {LEGOLAS, GANDALF};
		public static CHARACTER charChosen;
		public static ArrayList<Timer> timerList;
		public boolean[][] pathsPossible;
		private static boolean fading_;

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
	    	alpha = 1.0f;
	    	paused = false;
	    	
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
	    	alpha = 0.0f;
	    	bulletsList = new ArrayList<Bullet>();
	    	splash = new ArrayList<SplashScreen>();
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
	    
	    public static void pause() {
	    	paused = true;
	    }
	    
	    public static void resume() {
	    	paused = false;
	    }
	    
	    public static void getNewList(ArrayList<Object> objectsList_) {
	    	objectsList = objectsList_;
	    }
	    
	    public static void setAlpha(float newAlpha) {
	    	alpha = newAlpha;
	    }
	    
	    public static void fadeScreen(Graphics2D g2d, boolean fading) {
	    	
	    	
	    	g2d.setComposite(AlphaComposite.getInstance(
	                AlphaComposite.SRC_OVER, alpha));
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

	    	
	    	g2d.setColor(Color.black);
	    	g2d.fillRect(0, 0, Framework.frameWidth, Framework.frameHeight);
	    	
	    	if (fading)
	    		alpha += 0.05f;
	    	else
	    		alpha -= 0.05f;
	    	
	        if (alpha >= 1.0f) {
	            alpha = 1.0f;
	        }
	        else if (alpha <= 0.0f)
	        	alpha = 0.0f;
	        
	        
	        try {
	            Thread.sleep(40);
	        } catch (InterruptedException e) {

	            e.printStackTrace();
	        }
	    	
	    	
	    }
	    
	    public void UpdateGame(long gameTime, Point mousePosition)
	    {
	    	if (!paused)
	    	{
	       world.update();
	       ai.update();
	       actionHandler.handleActions();
	       eventHandler.handleEvents();
	       player.update();
	       updateBullets();
	       didPlayerShoot(gameTime);
	    	}
	    	updateFuckingSplash();
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
	    	
	    	
	    	drawFuckingSplashScreen(g2d);
	    
	    	 
	    }
	    
	    private void drawFuckingSplashScreen(Graphics2D g2d) {
	    	for (int i = 0; i < splash.size(); i++)
	    			splash.get(i).draw(g2d);
	    }
	    
	    public static void addFuckingSplash(SplashScreen screen) {
	    	splash.add(screen);
	   }
	    
	    private void updateFuckingSplash() {
	    	for (int i = 0; i < splash.size(); i++)
	    	{
    			splash.get(i).update();
    			if (splash.get(i).getFrames() > 40)
    			{
    				splash.remove(i);
    				resume();
    				continue;
    			}
	    	}
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
	            
	            Bullet b = new Bullet(player.x+35,player.y+35,weapon.damage, crosshair._mousePosition);
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
