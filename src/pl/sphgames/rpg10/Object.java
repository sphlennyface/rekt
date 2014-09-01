package pl.sphgames.rpg10;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import pl.sphgames.rpg10.LevelEncoder.POSITION;
import pl.sphgames.rpg10.LevelEncoder.TEXTURE;

public class Object extends ObjectEncoder{
	
	private boolean isWalkable_, isPassable_;
	private BufferedImage objectImg;
	private Terrain ter;
	private OBJECTTYPE objectType_;
	public int x_, y_;
	private Action action_;
	private int actionHelper_;
	private int imageId_;
	private Terrain terrainHelper;
	
	public Object(int x, int y, boolean isPassable, boolean isWalkable, Action action,
			int actionHelper, OBJECTTYPE objectType, int imageId) {
		
		x_ = x;
		y_ = y;
		isPassable_ = isPassable;
		isWalkable_ = isWalkable;
		actionHelper_ = actionHelper;
		action_ = action;
		objectType_ = objectType;
		
		imageId_ = imageId;
		loadImages(objectType_, imageId_);
		getMyselfInTheArray();
		if (!isPassable_)
			setUnpassable(x_,y_, objectImg);
		
	}
	
	public void setUnpassable(int x, int y, BufferedImage objectImg) {
		x_ = x;
		y_ = y;
		terrainHelper = World.background[x_][y_].getTerrain();
		terrainHelper.setUnpassable();
		
		World.setBackgroundTile(x_, y_, terrainHelper);
	}

	private void loadImages(OBJECTTYPE objectType, int imageId) {
		try {
			objectImg = ImageIO.read(new File(OBJECTTYPE.getType(objectType) + imageId + ".png"));
     	}
		catch (IOException ex) {
			Logger.getLogger(Object.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	private void getMyselfInTheArray() {
		ObjectHandler.createNewObject(this);
	}
	
	public void Draw(Graphics2D g2d)
    {
        g2d.drawImage(objectImg, (int)x_ * 64, (int)y_ * 64, null);
    }
}
