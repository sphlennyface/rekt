package pl.sphgames.rpg10;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class LevelEncoder {
	
	private Terrain loadedTerrain;
	
	public BufferedImage terrainImg;
	public BufferedImage grassImg, waterImg;
	private int wallsId_;
	
	
	public enum TEXTURE {
		WALL,
		DIRT,
		WATER,
		SURFACE;
		
		public static String getTexture(TEXTURE text ) {
			
			switch(text) {
				case WALL:
					return "wall";
				case DIRT:
					return "dirt";
				case WATER:
					return "water";
				case SURFACE:
					return "surface";
				default:
					return "dirt";
			}
		}
	}
	
	public enum POSITION {
		NORTH,
		NORTHWEST,
		WEST,
		SOUTHWEST,
		SOUTH,
		SOUTHEAST,
		EAST,
		NORTHEAST,
		WHOLE;
		
		public static String getPos(POSITION pos) {
			switch (pos) {
				case NORTH:
					return "N";
				case NORTHWEST:				
					return "NW";
				case WEST:
					return "W";
				case SOUTHWEST:
					return "SW";
				case SOUTH:
					return "S";
				case SOUTHEAST:
					return "SE";
				case EAST:
					return "E";
				case NORTHEAST:
					return "NE";
				case WHOLE:
					return "WHOLE";
				default:	
					return "N";
			}
			
		}
	}
	
	public LevelEncoder() {
		
	}

	public void createWorld(int wallsId, int[] doors, int surfaceId) {
		wallsId_ = wallsId;
		createWalls(wallsId);
		createSurface(surfaceId);
		createDoors(doors);
	}
	
	private void createWalls(int wallsId) {
			//przepraszam ¿e ¿yjê
		
			//north walls
			loadImg(wallsId,TEXTURE.WALL,POSITION.NORTH);
			loadedTerrain = new Terrain(1, false, false, Action.NONE, -1, TerrainType.WALLN, terrainImg);
			
			for (int i = 1; i < 7; i++) 				
				World.setBackgroundTile(i,0,loadedTerrain);
			for (int i = 9; i < 15; i++) 				
				World.setBackgroundTile(i,0,loadedTerrain);
			
			//south walls
			loadImg(wallsId,TEXTURE.WALL,POSITION.SOUTH);
			loadedTerrain = new Terrain(1, false, false, Action.NONE, -1, TerrainType.WALLS, terrainImg);
			
			for (int i = 1; i < 7; i++) 				
				World.setBackgroundTile(i,11,loadedTerrain);
			for (int i = 9; i < 15; i++) 				
				World.setBackgroundTile(i,11,loadedTerrain);
			
			//west walls
			loadImg(wallsId,TEXTURE.WALL,POSITION.WEST);
			loadedTerrain = new Terrain(1, false, false, Action.NONE, -1, TerrainType.WALLW, terrainImg);
						
			for (int y = 1; y < 5; y++) 				
				World.setBackgroundTile(0,y,loadedTerrain);
			for (int y = 7; y < 11; y++) 				
				World.setBackgroundTile(0,y,loadedTerrain);
			
			//east walls
			loadImg(wallsId,TEXTURE.WALL,POSITION.EAST);
			loadedTerrain = new Terrain(1, false, false, Action.NONE, -1, TerrainType.WALLE, terrainImg);
						
			for (int y = 1; y < 5; y++) 				
				World.setBackgroundTile(15,y,loadedTerrain);
			for (int y = 7; y < 11; y++) 				
				World.setBackgroundTile(15,y,loadedTerrain);
			
			//north west corner
			loadImg(wallsId,TEXTURE.WALL,POSITION.NORTHWEST);
			loadedTerrain = new Terrain(1, false, false, Action.NONE, -1, TerrainType.WALLNW, terrainImg);
				World.setBackgroundTile(0,0,loadedTerrain);
		
			//north east corner
			loadImg(wallsId,TEXTURE.WALL,POSITION.NORTHEAST);
			loadedTerrain = new Terrain(1, false, false, Action.NONE, -1, TerrainType.WALLNE, terrainImg);
				World.setBackgroundTile(15,0,loadedTerrain);
				
			//south west corner
			loadImg(wallsId,TEXTURE.WALL,POSITION.SOUTHWEST);
			loadedTerrain = new Terrain(1, false, false, Action.NONE, -1, TerrainType.WALLSW, terrainImg);
				World.setBackgroundTile(0,11,loadedTerrain);
			
			//south east corner
			loadImg(wallsId,TEXTURE.WALL,POSITION.SOUTHEAST);
			loadedTerrain = new Terrain(1, false, false, Action.NONE, -1, TerrainType.WALLSE, terrainImg);
				World.setBackgroundTile(15,11,loadedTerrain);				
	
	}
	
	private void createDoors(int[] doors) {
			//west doors
			if (doors[0] != 0) {
				loadImg(wallsId_,TEXTURE.SURFACE,POSITION.WHOLE); 
				loadedTerrain = new Terrain(1, true, true, Action.CHANGELEVEL, doors[0], TerrainType.DIRT1, terrainImg);
				World.setBackgroundTile(0,5,loadedTerrain);	
				World.setBackgroundTile(0,6,loadedTerrain);	
			}
			else {
				loadImg(wallsId_,TEXTURE.WALL,POSITION.WEST);
				loadedTerrain = new Terrain(1, false, false, Action.NONE, -1, TerrainType.WALLW, terrainImg);
				World.setBackgroundTile(0,5,loadedTerrain);	
				World.setBackgroundTile(0,6,loadedTerrain);	
			}
			// north doors
			if (doors[1] != 0) {
				loadImg(wallsId_,TEXTURE.SURFACE,POSITION.WHOLE); 
				loadedTerrain = new Terrain(1, true, true, Action.CHANGELEVEL, doors[1], TerrainType.DIRT1, terrainImg);
				World.setBackgroundTile(7,0,loadedTerrain);	
				World.setBackgroundTile(8,0,loadedTerrain);	
			}
			else {
				loadImg(wallsId_,TEXTURE.WALL,POSITION.NORTH);
				loadedTerrain = new Terrain(1, false, false, Action.NONE, -1, TerrainType.WALLN, terrainImg);
				World.setBackgroundTile(7,0,loadedTerrain);	
				World.setBackgroundTile(8,0,loadedTerrain);	
			}
			//east doors
			if (doors[2] != 0) {
				loadImg(wallsId_,TEXTURE.SURFACE,POSITION.WHOLE); 
				loadedTerrain = new Terrain(1, true, true, Action.CHANGELEVEL, doors[2], TerrainType.DIRT1, terrainImg);
				World.setBackgroundTile(15,5,loadedTerrain);	
				World.setBackgroundTile(15,6,loadedTerrain);	
			}
			else {
				loadImg(wallsId_,TEXTURE.WALL,POSITION.EAST);
				loadedTerrain = new Terrain(1, false, false, Action.NONE, -1, TerrainType.WALLE, terrainImg);
				World.setBackgroundTile(15,5,loadedTerrain);	
				World.setBackgroundTile(15,6,loadedTerrain);	
			}
			//south doors
			if (doors[3] != 0) {
				loadImg(wallsId_,TEXTURE.SURFACE,POSITION.WHOLE); 
				loadedTerrain = new Terrain(1, true, true, Action.CHANGELEVEL, doors[3], TerrainType.DIRT1, terrainImg);
				World.setBackgroundTile(7,11,loadedTerrain);	
				World.setBackgroundTile(8,11,loadedTerrain);	
			}
			else {
				loadImg(wallsId_,TEXTURE.WALL,POSITION.SOUTH);
				loadedTerrain = new Terrain(1, false, false, Action.NONE, -1, TerrainType.WALLS, terrainImg);
				World.setBackgroundTile(7,11,loadedTerrain);	
				World.setBackgroundTile(8,11,loadedTerrain);	
			}
	}
	public void createTerrain(int x, int y, int width, int height, boolean passable, boolean walkable, Action action, int actionHelper, int surfaceId, TEXTURE texture) {
		loadImg(surfaceId,texture,POSITION.WHOLE);
		loadedTerrain = new Terrain(1, passable, walkable, action, actionHelper, TerrainType.WATER, terrainImg);
		for (int i = x; i < x + width; i++)
			for (int j = y; j < y + height; j++)
				World.setBackgroundTile(i, j, loadedTerrain);
	}
	
	
	private void createSurface(int surfaceId) {
		
		for (int i = 1; i <= 14; i++)
			for (int j = 1; j <= 10; j++) {
				loadImg(surfaceId,TEXTURE.SURFACE,POSITION.WHOLE); 
				loadedTerrain = new Terrain(1, true, true, Action.NONE, -1, TerrainType.DIRT1, terrainImg);			
				World.setBackgroundTile(i,j,loadedTerrain);
	
			}
	}
	
	
	private void loadImg(int id, TEXTURE text, POSITION pos) {
		try {
			    terrainImg = ImageIO.read(new File(TEXTURE.getTexture(text) + id + POSITION.getPos(pos) + ".png"));
	     	}
			catch (IOException ex) {
				Logger.getLogger(LevelEncoder.class.getName()).log(Level.SEVERE, null, ex);
			}

	}
	

	
}

