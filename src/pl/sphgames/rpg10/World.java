package pl.sphgames.rpg10;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;


public class World {
	public static BufferedImage img;
	private Terrain dirt;
	private Terrain water;
	private Terrain tree;
	private Terrain wall;
	private static Whatever whatever;
	private Event event_;
	
	
	
	private int[] neighbourLevels;
	private int currentLevel;

	private ArrayList<Terrain> terrainList;
	public static Terrain[][] background;
	
	public BufferedImage dirtImg;
	public BufferedImage grassImg, waterImg;

	public World() {

		initWorld();
		// newLevel();
		loadTextures();				
		createBackground();
	}
	
	public static void whateverWhatever() {
		
	}
	
	private void initWorld() {
		background = new Terrain[16][13];
		
		
	
	}
	
	public static void clearBackgroundTile(int x, int y) {
		background[x][y] = null;
	}
	
	public static void setBackgroundTile(int x, int y, Terrain tile) {
		background[x][y] = tile;
	}
	
	public static void putEvent(int x, int y, Event event_) {
		background[x][y].putEvent(event_);
	}
	
	private void createBackground() {
		/*for (int i = 0; i < 15; i++)
			for (int y = 0; y < 12; y++) {
				if (i == 0 || y == 0 || i == 14 || y == 11)
					background[i][y] = new Terrain(1, false, false, Action.NONE, TerrainType.GRASS1, grassImg);
				else if ( (y >= 4 && y <= 7) && ( i >= 3 && i <= 7) )
					background[i][y] = new Terrain(1, false, true, Action.NONE, TerrainType.WATER, waterImg);
				else
					background[i][y] = new Terrain(1, true, true, Action.NONE, TerrainType.DIRT1, dirtImg);
			}*/
	}

	
	public void loadTextures() {
	/*	try {
			   dirtImg = ImageIO.read(new File("dirt.jpg"));
			   grassImg = ImageIO.read(new File("grass.jpg"));
			   waterImg = ImageIO.read(new File("water.jpg"));
	       
			}
			catch (IOException ex) {
				Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
			}*/
		
	}
	
	
	public void update() {
		
	}
	
	public void draw(Graphics2D g2d) {
		for (int i = 0; i < 16; i++) 
			for (int y = 0; y < 12; y++) {
				if (background[i][y] == null)
					continue;
				g2d.drawImage(background[i][y].getTexture(), i * 64, y * 64, null);
			}

	}
	

}
